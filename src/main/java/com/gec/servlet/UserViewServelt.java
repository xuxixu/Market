package com.gec.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value = { "/loginForm.do","/main.do","/top.do","/left.do","/right.do" })
public class UserViewServelt extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 获取到请求相对路径
		String path = request.getRequestURI();
		// 截取出请求名
		path = path.substring(path.lastIndexOf("/") + 1, path.lastIndexOf(".do"));
		if(path.equals("loginForm")){
			request.getRequestDispatcher("WEB-INF/jsp/loginForm.jsp").forward(request, response);
		}else if(path.equals("main")){
			request.getRequestDispatcher("WEB-INF/jsp/main.jsp").forward(request, response);			
		}else if(path.equals("top")){
			request.getRequestDispatcher("WEB-INF/jsp/top.jsp").forward(request, response);			
		}else if(path.equals("left")){
			request.getRequestDispatcher("WEB-INF/jsp/left.jsp").forward(request, response);			
		}else if(path.equals("right")){
			request.getRequestDispatcher("WEB-INF/jsp/right.jsp").forward(request, response);			
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
