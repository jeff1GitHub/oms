package com.og.oms.ueditor;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.baidu.ueditor.ActionEnter;

//@WebServlet(name = "UEditorServlet", urlPatterns = "/UEditor")  
public class UEditorController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	/**
	 * 最后存放文件的静态目录
	 */
	final private String nasHome;

	public UEditorController(String nasHome) {
		this.nasHome = nasHome;
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setHeader("Content-Type", "text/html");
		PrintWriter out = response.getWriter();

//		 ServletContext application = this.getServletContext();
//		 String rootPath = application.getRealPath("/");
		String rootPath = nasHome;

		String action = request.getParameter("action");
		String result = new ActionEnter(request, rootPath).exec();
		if (action != null) {
			if("listfile".equals(action)) {
				rootPath = rootPath.replace("\\", "/");
				//将绝对路径替换为相对路径，动态获取项目路径映射
				result = result.replaceAll(rootPath, "/").replace("//file", "/file").replace("/file", request.getContextPath()+"/file");
			} else if("listimage".equals(action)) {
				rootPath = rootPath.replace("\\", "/");
				//将绝对路径替换为相对路径，动态获取项目路径映射
				result = result.replaceAll(rootPath, "/").replace("//image", "/image").replace("/image", request.getContextPath()+"/image");
			}
		}
		out.write(result);
	}

}