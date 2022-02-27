package com.gec.servlet;

import com.gec.bean.PageBean;
import com.gec.bean.Supplier;
import com.gec.service.SupplierService;
import com.gec.service.impl.SupplierServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(value = { "/findSupplier.do","/addSupplier.do","/supplierAddOrUpdate.do","/editSupplier.do","/deleteSupplier.do" })
public class SupplierServlet extends HttpServlet {
	private static final long serialVessionUID = 1L;
	SupplierService ss = new SupplierServiceImpl();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 获取到请求相对路径
		String path = request.getRequestURI();
		// 截取出请求名
		path = path.substring(path.lastIndexOf("/") + 1, path.lastIndexOf(".do"));
		String number = request.getParameter("number");
		String name = request.getParameter("name");
		String remark = request.getParameter("remark");
		if("findSupplier".equals(path)){
			//设置默认当前页为1
			int pageIndex = 1;
			//获取到当前页
			String index = request.getParameter("pageIndex");
			if(index!=null)
				pageIndex = Integer.parseInt(index);
			Supplier supplier = new Supplier();
			supplier.setNumber(number);
			supplier.setName(name);
			supplier.setRemark(remark);
			PageBean<Supplier> pb = ss.findPage(supplier,pageIndex);
			List<Supplier> supplierList = ss.findAll();
			request.setAttribute("supplierList", supplierList);
			request.setAttribute("supplier", supplier);
			request.setAttribute("pageModel", pb);
			request.getRequestDispatcher("WEB-INF/jsp/supplier/supplierlist.jsp").forward(request, response);
		}else if(path.equals("addSupplier")){
			request.setAttribute("info", "add");
			request.getRequestDispatcher("WEB-INF/jsp/supplier/addsupplier.jsp").forward(request, response);
		}else if(path.equals("supplierAddOrUpdate")){
			String id = request.getParameter("sid");
			Supplier supplier = new Supplier();
			supplier.setNumber(number);
			supplier.setName(name);
			supplier.setRemark(remark);
			//id存在,则表示执行的编辑操作
			if(id!=null&&!"".equals(id)){
				supplier.setSid(Integer.parseInt(id));
				ss.update(supplier);
			}else{  //不存在,则表示执行添加操作
				ss.add(supplier);
			}
			response.sendRedirect("findSupplier.do");
		}else if(path.equals("editSupplier")){
			Integer id = Integer.parseInt(request.getParameter("sid"));
			Supplier supplier = ss.findById(id);
			request.setAttribute("supplier", supplier);
			request.getRequestDispatcher("WEB-INF/jsp/supplier/addsupplier.jsp").forward(request, response);
		}else if(path.equals("deleteSupplier")){
			String[] ids = request.getParameterValues("sids");
			ss.delete(ids);
			response.sendRedirect("findSupplier.do");
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
