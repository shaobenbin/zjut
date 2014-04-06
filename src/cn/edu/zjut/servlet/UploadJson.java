/**
 * Copyright ? 2009 www.debug.cn
 * All Rights Reserved
 */
package cn.edu.zjut.servlet;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.DiskFileUpload;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.lang.StringUtils;
import org.json.JSONException;
import org.json.JSONObject;

//kindeditor文件上传的Java - Servlet实现.
//@ author ben.s
//@ refer to ben.s
//@ version 2
//@ description 增加html5上传功能的支持
@SuppressWarnings({ "serial", "deprecation" })
public class UploadJson extends HttpServlet {

	private static final long serialVersionUID = 1541334866883495283L;

	private static String baseDir = "/uploadfile/"; // 上传文件存储目录
	private static String fileExt = "jpg,jpeg,bmp,gif,png,xls,xlsx,doc,docx";
	private static Long maxSize = 0l;
	private static String dirType = "1"; // 0:不建目录 1:按天存入目录 2:按月存入目录 3:按扩展名存目录
											// 建议使用按天存

	public void init() throws ServletException {
		baseDir = this.getInitParameter("baseDir"); // 获取web.xml中servlet的配置文件目录参数
		if (StringUtils.isEmpty(baseDir))
			baseDir = "/uploadfile/"; // 获取文件上传存储的相当路径

		String realBaseDir = this.getServletConfig().getServletContext().getRealPath(baseDir);
		File baseFile = new File(realBaseDir);
		if (!baseFile.exists()) {
			baseFile.mkdir();
		}

		String maxSize_str = this.getInitParameter("maxSize"); // 获取文件大小参数
		if (StringUtils.isNotEmpty(maxSize_str)) {
			maxSize = new Long(maxSize_str);
		} else {
			maxSize = Long.valueOf("5242880"); // 5M
		}

		dirType = this.getInitParameter("dirType"); // 获取文件目录类型参数

		if (StringUtils.isEmpty(dirType))
			dirType = "1";
		if (",0,1,2,3,".indexOf("," + dirType + ",") < 0)
			dirType = "1";
	}

	// 上传文件数据处理过程

	@SuppressWarnings({ "unchecked" })
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		response.setHeader("Cache-Control", "no-cache");

		String err = "";
		String newFileName = "";

		if ("application/octet-stream".equals(request.getContentType())) { // HTML
																			// 5
																			// 上传
			try {
				String dispoString = request.getHeader("Content-Disposition");
				int iFindStart = dispoString.indexOf("name=\"") + 6;
				int iFindEnd = dispoString.indexOf("\"", iFindStart);

				iFindStart = dispoString.indexOf("filename=\"") + 10;
				iFindEnd = dispoString.indexOf("\"", iFindStart);
				String sFileName = dispoString.substring(iFindStart, iFindEnd);

				int i = request.getContentLength();
				byte buffer[] = new byte[i];
				int j = 0;
				while (j < i) { // 获取表单的上传文件
					int k = request.getInputStream().read(buffer, j, i - j);
					j += k;
				}

				if (buffer.length == 0) { // 文件是否为空
					printInfo(response, "上传文件不能为空", "");
					return;
				}
				if (maxSize > 0 && buffer.length > maxSize) { // 检查文件大小
					printInfo(response, "上传文件的大小超出限制", "");
					return;
				}

				String filepathString = getSaveFilePath(sFileName, response);
				if ("不允许上传此类型的文件".equals(filepathString))
					return; // 检查文件类型

				OutputStream out = new BufferedOutputStream(new FileOutputStream(this.getServletConfig()
						.getServletContext().getRealPath("")
						+ filepathString, true));
				out.write(buffer);
				out.close();

				newFileName = request.getContextPath() + filepathString;
			} catch (Exception ex) {
				System.out.println(ex.getMessage());
				newFileName = "";
				err = "错误: " + ex.getMessage();
			}
		} else {
			DiskFileUpload upload = new DiskFileUpload();

			try {
				List<FileItem> items = upload.parseRequest(request);
				Map<String, Serializable> fields = new HashMap<String, Serializable>();
				Iterator<FileItem> iter = items.iterator();
				while (iter.hasNext()) {
					FileItem item = (FileItem) iter.next();
					String fileName = item.getName();
					long fileSize = item.getSize();
					if (!item.isFormField()) {
						// 检查文件大小
						if (item.getSize() > maxSize) {
							printInfo(response, "上传文件的大小超出限制", "");
							return;
						}
						// 检查扩展名
						String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
						if (!Arrays.<String> asList(fileExt.split(",")).contains(fileExt)) {
							printInfo(response, "上传文件格式不正确", "");
							return;
						}

						SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
						String filepathString = getSaveFilePath(fileName, response);
						File uploadedFile = new File(this.getServletConfig().getServletContext().getRealPath("")
								+ filepathString);
						item.write(uploadedFile);
						printInfo(response, "", request.getContextPath() + filepathString);
						return;
					}
				}

			} catch (Exception ex) {
				System.out.println(ex.getMessage());
				newFileName = "";
				err = "错误: " + ex.getMessage();
			}
		}
		printInfo(response, err, newFileName);
	}

	public String getSaveFilePath(String sFileName, HttpServletResponse response) throws IOException {
		String extensionName = sFileName.substring(sFileName.lastIndexOf(".") + 1); // 获取文件扩展名

		if (("," + fileExt.toLowerCase() + ",").indexOf("," + extensionName.toLowerCase() + ",") < 0) { // 检查文件类型
			printInfo(response, "不允许上传此类型的文件", "");
			return "不允许上传此类型的文件";
		}

		String fileFolder = ""; // 0:不建目录, 1:按天存入目录, 2:按月存入目录,
								// 3:按扩展名存目录.建议使用按天存。
		if (dirType.equalsIgnoreCase("1"))
			fileFolder = new SimpleDateFormat("yyyy/MM/dd").format(new Date());
		if (dirType.equalsIgnoreCase("2"))
			fileFolder = new SimpleDateFormat("yyyy/MM").format(new Date());
		if (dirType.equalsIgnoreCase("3"))
			fileFolder = extensionName.toLowerCase();

		String saveDirPath = baseDir + extensionName + "/" + fileFolder + "/"; // 文件存储的相对路径
		String saveFilePath = this.getServletConfig().getServletContext().getRealPath("") + saveDirPath; // 文件存储在容器中的绝对路径

		File fileDir = new File(saveFilePath); // 构建文件目录以及目录文件
		if (!fileDir.exists()) {
			fileDir.mkdirs();
		}
		String filename = UUID.randomUUID().toString(); // 重命名文件

		return saveDirPath + filename + "." + extensionName;
	}

	// 使用I/O流输出 json格式的数据

	public void printInfo(HttpServletResponse response, String err, String newFileName) throws IOException {
		JSONObject obj = new JSONObject();
		try {
			obj.put("error", (err == null || "".equals(err)) ? 0 : err);
			obj.put("url", newFileName);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		response.setContentType("text/plain");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		// out.println("{\"error\":\"" + err + "\",\"url\":\"" + newFileName +
		// "\"}");
		out.println(obj.toString());
		out.flush();
		out.close();
	}
}
