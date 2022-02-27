package com.gec.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/*")
public class EncodingFilter implements Filter {
	@Override
	public void destroy() {
		System.out.println("对象销毁时执行的方法");
	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain chain)
			throws IOException, ServletException {
		//过滤的执行方法,获取到请求链,处理完后需要使用chain执行方法,将请求链重新放回servlet
		//将非http的request转换为http协议的request
		HttpServletRequest request = (HttpServletRequest) arg0;
		HttpServletResponse response = (HttpServletResponse) arg1;
		//设置请求编码格式为utf-8
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		//将请求链放入servlet
		String requestURI = request.getRequestURI();
		if (request.getSession().getAttribute("user_session") == null) {
			if (!requestURI.contains("login") && !requestURI.contains("css") && !requestURI.contains("js") && !requestURI.contains("images") && !requestURI.contains("page") && !requestURI.contains("static")) {
				request.getRequestDispatcher("WEB-INF/jsp/loginForm.jsp").forward(request, response);
			}
		}
		chain.doFilter(request, response);

	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		System.out.println("过滤器初始化时执行的方法");
	}

}
