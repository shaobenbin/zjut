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

//xhEditor文件上传的Java - Servlet实现.
//@ author WeiMiao
//@ refer to easinchu
//@ version 2
//@ description 增加html5上传功能的支持
@SuppressWarnings( { "serial", "deprecation" })
public class ResourceUploadServlet extends HttpServlet {

	private static final long serialVersionUID = 1541334866883495283L;

	private static String baseDir = "/resourcefile/"; // 上传文件存储目录
	private static String fileExt = "jpg,jpeg,bmp,gif,png,xls,xlsx,doc,docx,pdf,ppt,flv";
	private static Long maxSize = 0l;
	private static String dirType = "1"; // 0:不建目录 1:按天存入目录 2:按月存入目录 3:按扩展名存目录

	// 建议使用按天存

	public void init() throws ServletException {
		baseDir = this.getInitParameter("baseDir"); // 获取web.xml中servlet的配置文件目录参数
		if (StringUtils.isEmpty(baseDir))
			baseDir = "/resourcefile/"; // 获取文件上传存储的相当路径

		String realBaseDir = this.getServletConfig().getServletContext()
				.getRealPath(baseDir);
		File baseFile = new File(realBaseDir);
		if (!baseFile.exists()) {
			baseFile.mkdir();
		}

		// fileExt = this.getInitParameter("fileExt"); // 获取文件类型参数
		// if (StringUtils.isEmpty(fileExt))
		// fileExt = "jpg,jpeg,gif,bmp,png";

		String maxSize_str = this.getInitParameter("maxSize"); // 获取文件大小参数
		if (StringUtils.isNotEmpty(maxSize_str)) {
			maxSize = new Long(maxSize_str);
		} else {
			maxSize = Long.valueOf("52428800"); // 5M
		}

		dirType = this.getInitParameter("dirType"); // 获取文件目录类型参数

		if (StringUtils.isEmpty(dirType))
			dirType = "1";
		if (",0,1,2,3,".indexOf("," + dirType + ",") < 0)
			dirType = "1";
	}

	// 上传文件数据处理过程

	@SuppressWarnings( { "unchecked" })
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
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
				
				String filepathString = getSaveFilePath(null,sFileName, response);
				if ("不允许上传此类型的文件".equals(filepathString))
					return; // 检查文件类型

				OutputStream out = new BufferedOutputStream(
						new FileOutputStream(this.getServletConfig()
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
					if (item.isFormField())
						fields.put(item.getFieldName(), item.getString());
					else
						fields.put(item.getFieldName(), item);
				}
				FileItem uploadFile = (FileItem) fields.get("filedata"); // 获取表单的上传文件
				String source = (String) fields.get("source");
				String fileNameLong = uploadFile.getName(); // 获取文件上传路径名称

				if (uploadFile.getSize() == 0) { // 文件是否为空
					printInfo(response, "上传文件不能为空", "");
					return;
				}
				if (maxSize > 0 && uploadFile.getSize() > maxSize) { // 检查文件大小
					printInfo(response, "上传文件的大小超出限制", "");
					return;
				}

				String filepathString = getSaveFilePath(source, fileNameLong,
						response);
				if ("不允许上传此类型的文件".equals(filepathString)){
					printInfo(response, "上传文件类型不正确", "");
					return; // 检查文件类型
				}

				File savefile = new File(this.getServletConfig()
						.getServletContext().getRealPath("")
						+ filepathString);
				uploadFile.write(savefile); // 存储上传文件

				newFileName = request.getContextPath() + filepathString;
			} catch (Exception ex) {
				System.out.println(ex.getMessage());
				newFileName = "";
				err = "错误: " + ex.getMessage();
			}
		}
		printInfo(response, err, newFileName);
	}

	public String getSaveFilePath(String source, String sFileName,
			HttpServletResponse response) throws IOException {
		String extensionName = sFileName
				.substring(sFileName.lastIndexOf(".") + 1); // 获取文件扩展名

		if (("," + fileExt.toLowerCase() + ",").indexOf(","
				+ extensionName.toLowerCase() + ",") < 0) { // 检查文件类型
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

		String saveDirPath = baseDir + (source == null ? "" : source + "/")
				+ extensionName + "/" + fileFolder + "/"; // 文件存储的相对路径
		String saveFilePath = this.getServletConfig().getServletContext()
				.getRealPath("")
				+ saveDirPath; // 文件存储在容器中的绝对路径

		File fileDir = new File(saveFilePath); // 构建文件目录以及目录文件
		if (!fileDir.exists()) {
			fileDir.mkdirs();
		}
		String filename = UUID.randomUUID().toString(); // 重命名文件

		return saveDirPath + filename + "." + extensionName;
	}

	// 使用I/O流输出 json格式的数据

	public void printInfo(HttpServletResponse response, String err,
			String newFileName) throws IOException {
		response.setContentType("text/plain");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		if(err!=null && !"".equals(err)){
			out.println(err+"&fail");
		}else{
			out.println(newFileName+"&success");
		}
		out.flush();
		out.close();
	}
}
