package cn.edu.zjut.servlet;

import java.io.BufferedOutputStream;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DownloadFileServlet extends HttpServlet {
	@SuppressWarnings({ "unchecked" })
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		java.io.BufferedInputStream bis = null;
		java.io.BufferedOutputStream bos = null;
		
		String agent = request.getHeader("User-Agent");
		boolean isMSIE = (agent!=null && agent.indexOf("MSIE")!=-1);

		String filePath = request.getParameter("filePath");
		filePath = filePath.replace(filePath.substring(0, filePath.indexOf("resourcefile")), "");
		if(!filePath.substring(0,1).equals("/")){
			filePath = "/"+filePath;
		}
		String fileName = request.getParameter("fileName");
		try {
			long fileLength = new File(this.getServletConfig().getServletContext().getRealPath("")+filePath).length();

			response.setContentType("application/x-msdownload;");
			if(isMSIE){
				response.setHeader("Content-disposition", "attachment; filename="
					+URLEncoder.encode(fileName+suffix(filePath),"UTF-8"));
			}else{
				response.setHeader("Content-disposition", "attachment; filename="
						+ new String((fileName+suffix(filePath)).getBytes("utf-8"), "ISO8859-1"));
			}
			response.setHeader("Content-Length", String.valueOf(fileLength));

			bis = new BufferedInputStream(new FileInputStream(this.getServletConfig().getServletContext().getRealPath("")+filePath));
			bos = new BufferedOutputStream(response.getOutputStream());
			byte[] buff = new byte[2048];
			int bytesRead;
			while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
				bos.write(buff, 0, bytesRead);
			}
		} catch (Exception e) {
//			e.printStackTrace();
		} finally {
			if (bis != null)
				bis.close();
			if (bos != null)
				bos.close();
		}
	}
	
	private String suffix(String path){
		return path.substring(path.lastIndexOf("."), path.length());
	}
}
