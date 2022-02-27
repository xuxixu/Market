package com.gec.servlet;

import com.gec.bean.PageBean;
import com.gec.bean.Store;
import com.gec.bean.User;
import com.gec.service.StoreService;
import com.gec.service.impl.StoreServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(value = { "/findStore.do","/addStore.do","/storeAddOrUpdate.do","/editStore.do","/deleteStore.do" })
public class StoreServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	StoreService rs = new StoreServiceImpl();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 获取到请求相对路径
		String path = request.getRequestURI();
		// 截取出请求名
		path = path.substring(path.lastIndexOf("/") + 1, path.lastIndexOf(".do"));
		String number = request.getParameter("number");
		String name = request.getParameter("name");
		String loc = request.getParameter("loc");
		String remark = request.getParameter("remark");
		if("findStore".equals(path)){
			//设置默认当前页为1
			int pageIndex = 1;
			//获取到当前页
			String index = request.getParameter("pageIndex");
			if(index!=null)
				pageIndex = Integer.parseInt(index);
			Store store = new Store();
			store.setNumber(number);
			store.setName(name);
			store.setLoc(loc);
			store.setRemark(remark);
			PageBean<Store> pb = rs.findPage(store,pageIndex);
			List<Store> storeList = rs.findAll();
			request.setAttribute("storeList", storeList);
			request.setAttribute("store", store);
			request.setAttribute("pageModel", pb);
			request.getRequestDispatcher("WEB-INF/jsp/store/storelist.jsp").forward(request, response);
		}else if(path.equals("addStore")){
			request.setAttribute("info", "add");
			request.getRequestDispatcher("WEB-INF/jsp/store/addstore.jsp").forward(request, response);
		}else if(path.equals("storeAddOrUpdate")){
			String id = request.getParameter("id");
			Store store = new Store();
			store.setNumber(number);
			store.setName(name);
			store.setLoc(loc);
			store.setRemark(remark);
			//id存在,则表示执行的编辑操作
			if(id!=null&&!"".equals(id)){
				store.setId(Integer.parseInt(id));
				rs.update(store);
			}else{  //不存在,则表示执行添加操作
				rs.add(store);
			}
			response.sendRedirect("findStore.do");
		}else if(path.equals("editStore")){
			Integer id = Integer.parseInt(request.getParameter("id"));
			Store store = rs.findById(id);
			request.setAttribute("store", store);
			request.getRequestDispatcher("WEB-INF/jsp/store/addstore.jsp").forward(request, response);
		}else if(path.equals("deleteStore")){
				String[] ids = request.getParameterValues("ids");

				rs.delete(ids);
				response.sendRedirect("findStore.do");
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
