package com.gec.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.gec.bean.PageBean;
import com.gec.bean.Role;
import com.gec.bean.User;
import com.gec.service.RoleService;
import com.gec.service.UserService;
import com.gec.service.impl.RoleServiceImpl;
import com.gec.service.impl.UserServiceImpl;

//1.请求不能同名  2.请求前面必须有斜线
@WebServlet(value = { "/login.do", "/findUser.do", "/addUser.do", "/addOrUpdate.do", "/checkUserName.do",
		"/editUser.do", "/userdel.do","/logout.do" })
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	UserService es = new UserServiceImpl();
	RoleService rs = new RoleServiceImpl();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 获取到请求相对路径
		String path = request.getRequestURI();
		// 截取出请求名
		path = path.substring(path.lastIndexOf("/") + 1, path.lastIndexOf(".do"));
		// 通过name属性获取到对应的value值
		String number = request.getParameter("number");
		String name = request.getParameter("userName");
		String password = request.getParameter("pwd");
		String sex = request.getParameter("sex");
		String phone = request.getParameter("phone");

		if (path.equals("login")) {
			// 获取用户名和密码
			User user = es.login(number, password);
			if (user != null) {
				// 保存到会话中,一次会话包含多次请求
				HttpSession session = request.getSession();
				// 可以在一次会话中共享登录的用户对象
				session.setAttribute("user_session", user);
				request.getRequestDispatcher("main.do").forward(request, response);
			} else
				request.setAttribute("message", "用户名或密码错误!");
				request.setAttribute("number", number);
				request.setAttribute("password", password);
				request.getRequestDispatcher("WEB-INF/jsp/loginForm.jsp").forward(request, response);
		} else if (path.equals("findUser")) { // 带条件查询
			// 设置默认当前页为1
			int pageIndex = 1;
			// 获取到当前页
			String index = request.getParameter("pageIndex");
			if (index != null)
				pageIndex = Integer.parseInt(index);
			// 创建对象将内容存储起来
			User user = new User();
			user.setNumber(number);
			user.setUserName(name);
			user.setSex(sex);
			user.setPhone(phone);
			PageBean<User> pb = es.findEntity(user, pageIndex);
			request.setAttribute("use", user);
			request.setAttribute("pageModel", pb);
			request.getRequestDispatcher("WEB-INF/jsp/user/userlist.jsp").forward(request, response);
		} else if (path.equals("addUser")) {

			// 进入添加页面之前,需要查询出所有的角色
			List<Role> roleList = rs.findAll();
			request.setAttribute("info", "add");
			request.setAttribute("roleList", roleList);
			request.getRequestDispatcher("WEB-INF/jsp/user/adduser.jsp").forward(request, response);
		} else if (path.equals("addOrUpdate")) {
			User tuser = (User)request.getSession().getAttribute("user_session");
			if( tuser == null || !tuser.getRole().getR_name().equals("超级管理员"))
			{
				request.setAttribute("message","敏感操作，请使用高权限账号操作");
				request.getRequestDispatcher("WEB-INF/jsp/user/adduser.jsp").forward(request, response);
			}else {
				// 获取到角色ID
				Integer rid = Integer.parseInt(request.getParameter("rid"));
				String remark = request.getParameter("remark");
				String id = request.getParameter("uid");
				User user = new User();
				user.setNumber(number);
				user.setUserName(name);
				user.setPassword(password);
				user.setSex(sex);
				user.setPhone(phone);
				user.setRid(rid);
				user.setRemark(remark);
				if (id!= null&&!id.equals("")) {
					user.setUid(Integer.parseInt(id));
					es.update(user);
				} else {
					es.add(user);
				}
				response.sendRedirect("findUser.do");
			}

		} else if (path.equals("checkUserName")) {
			// Ajax实现异步刷新,不能使用跳转,以输出的形式输出内容,由回调默认读取输出内容
			PrintWriter out = response.getWriter();
			User user = es.findByNumber(number);
			if (user != null) {
				out.print("0");
			} else {
				out.print("1");
			}
		} else if (path.equals("editUser")) {
			User tuser = (User)request.getSession().getAttribute("user_session");
			if( tuser == null || !tuser.getRole().getR_name().equals("超级管理员"))
			{
				request.setAttribute("message","敏感操作，请使用高权限账号操作");
				request.getRequestDispatcher("findUser.do").forward(request, response);
			}else {
				String id = request.getParameter("uid");
				// 修改页面需要当前修改的对象以及所有对应的下拉框集合
				User user = es.findById(Integer.parseInt(id));
				List<Role> roleList = rs.findAll();
				request.setAttribute("user", user);
				request.setAttribute("roleList", roleList);
				request.getRequestDispatcher("WEB-INF/jsp/user/adduser.jsp").forward(request, response);
			}

		} else if (path.equals("userdel")) {

			User tuser = (User)request.getSession().getAttribute("user_session");
			if( tuser == null || !tuser.getRole().getR_name().equals("超级管理员"))
			{
				request.setAttribute("message","敏感操作，请使用高权限账号操作");
				request.getRequestDispatcher("findUser.do").forward(request, response);
			}
			else {
				String[] ids = request.getParameterValues("uids");
				String id = ((User)request.getSession().getAttribute("user_session")).getUid()+"";
				// 删除,只需要获取到number,调用删除功能
				es.delete(id,ids);
				response.sendRedirect("findUser.do");
			}

		}else if(path.equals("logout")){
			//获取到session
			HttpSession session = request.getSession();
			//去掉session属性
			//session.removeAttribute("user_session");
			//干掉session
			session.invalidate();
			request.getRequestDispatcher("WEB-INF/jsp/loginForm.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// post请求的内容,也交给doGet方法处理
		doGet(request, response);
	}

}
