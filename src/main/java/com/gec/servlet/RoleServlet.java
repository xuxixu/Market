package com.gec.servlet;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gec.bean.PageBean;
import com.gec.bean.Role;
import com.gec.service.RoleService;
import com.gec.service.impl.RoleServiceImpl;

@WebServlet(value = { "/findRole.do","/addRole.do","/roleAddOrUpdate.do","/editRole.do","/deleteRole.do" })
public class RoleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	RoleService rs = new RoleServiceImpl();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 获取到请求相对路径
		String path = request.getRequestURI();
		// 截取出请求名
		path = path.substring(path.lastIndexOf("/") + 1, path.lastIndexOf(".do"));
		String rname = request.getParameter("r_name");
		String remark = request.getParameter("remark");
		if("findRole".equals(path)){
			//设置默认当前页为1
			int pageIndex = 1;
			//获取到当前页
			String index = request.getParameter("pageIndex");
			if(index!=null)
				pageIndex = Integer.parseInt(index);
			Role role = new Role();
			role.setR_name(rname);
			role.setRemark(remark);
			PageBean<Role> pb = rs.findPage(role,pageIndex);
			List<Role> roleList = rs.findAll();
			request.setAttribute("roleList", roleList);
			request.setAttribute("role", role);
			request.setAttribute("pageModel", pb);
			request.getRequestDispatcher("WEB-INF/jsp/role/rolelist.jsp").forward(request, response);
		}else if(path.equals("addRole")){
			request.setAttribute("info", "add");
			request.getRequestDispatcher("WEB-INF/jsp/role/addrole.jsp").forward(request, response);
		}else if(path.equals("roleAddOrUpdate")){
			String id = request.getParameter("id");
			Role role = new Role();
			role.setR_name(rname);
			role.setRemark(remark);
			//id存在,则表示执行的编辑操作
			if(id!=null&&!"".equals(id)){
				role.setId(Integer.parseInt(id));
				rs.update(role);
			}else{  //不存在,则表示执行添加操作
				rs.add(role);
			}
			response.sendRedirect("findRole.do");
		}else if(path.equals("editRole")){
			Integer id = Integer.parseInt(request.getParameter("id"));
			Role role = rs.findById(id);
			request.setAttribute("role", role);
			request.getRequestDispatcher("WEB-INF/jsp/role/addrole.jsp").forward(request, response);
		}else if(path.equals("deleteRole")){
			String[] ids = request.getParameterValues("uids");

			rs.delete(ids);
			response.sendRedirect("findRole.do");
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
