package com.gec.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gec.bean.*;
import com.gec.service.GoodsService;
import com.gec.service.GoodsTypeService;
import com.gec.service.SupplierService;
import com.gec.service.impl.GoodsServiceImpl;
import com.gec.service.impl.GoodsTypeServiceImpl;
import com.gec.service.impl.SupplierServiceImpl;

@WebServlet(value = { "/goodslist.do","/goodsadd.do","/goodsedit.do","/goodsAddOrUpdate.do" })
public class GoodsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	GoodsService gs = new GoodsServiceImpl();
	GoodsTypeService gts = new GoodsTypeServiceImpl();
	SupplierService ss = new SupplierServiceImpl();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 获取到请求相对路径
		String path = request.getRequestURI();
		// 截取出请求名
		path = path.substring(path.lastIndexOf("/") + 1, path.lastIndexOf(".do"));
		String name = request.getParameter("name");
		String tid = request.getParameter("tid");
		String unit = request.getParameter("unit");
		String sid = request.getParameter("sid");
		if(path.equals("goodslist")){
			//设置默认当前页为1
			int pageIndex = 1;
			//获取到当前页
			String index = request.getParameter("pageIndex");
			if(index!=null)
				pageIndex = Integer.parseInt(index);
			Goods goods = new Goods();
			goods.setName(name);
			if(tid!=null&&!tid.equals(""))
				goods.setTid(Integer.parseInt(tid));
			goods.setUnit(unit);
			if(sid!=null&&!sid.equals(""))
				goods.setSid(Integer.parseInt(sid));
			PageBean<Goods> pb = gs.findPage(goods,pageIndex);
			//查询出所有的类型
			List<GoodsType> gtList = gts.findAll();
			//查询出所有的供应商
			List<Supplier> supList = ss.findAll();
			request.setAttribute("goods", goods);
			request.setAttribute("typeList", gtList);
			request.setAttribute("supList", supList);
			request.setAttribute("pageModel", pb);
			request.getRequestDispatcher("WEB-INF/jsp/goods/goodslist.jsp").forward(request, response);
		}else if(path.equals("goodsadd")){
			//查询出所有的类型
			List<GoodsType> gtList = gts.findAll();
			//查询出所有的供应商
			List<Supplier> supList = ss.findAll();
			request.setAttribute("gtList", gtList);
			request.setAttribute("supList", supList);
			request.setAttribute("info", "add");
			request.getRequestDispatcher("WEB-INF/jsp/goods/goodsadd.jsp").forward(request, response);
		}else if(path.equals("goodsedit")){
			int gid = Integer.parseInt(request.getParameter("gid"));
			Goods goods = gs.findById(gid);
			//查询出所有的类型
			List<GoodsType> gtList = gts.findAll();
			//查询出所有的供应商
			List<Supplier> supList = ss.findAll();
			request.setAttribute("goods", goods);
			request.setAttribute("gtList", gtList);
			request.setAttribute("supList", supList);
			request.getRequestDispatcher("WEB-INF/jsp/goods/goodsadd.jsp").forward(request, response);
		}else if(path.equals("goodsAddOrUpdate")){
			String gid = request.getParameter("gid");
			String bcode = request.getParameter("b_code");
			String price = request.getParameter("price");
			String num = request.getParameter("num");
			String vprice = request.getParameter("v_price");
			Goods goods = new Goods();
			goods.setName(name);
			goods.setB_code(bcode);
			goods.setUnit(unit);
			if(tid!=null&&!tid.equals("0"))
				goods.setTid(Integer.parseInt(tid));
			if(price!=null)
				goods.setPrice(Double.parseDouble(price));
			if(sid!=null&&!sid.equals("0"))
				goods.setSid(Integer.parseInt(sid));
			if(num!=null)
				goods.setNum(Integer.parseInt(num));
			if(vprice!=null)
				goods.setV_price(Double.parseDouble(vprice));
			if(gid!=null&&!gid.equals("")){
				goods.setGid(Integer.parseInt(gid));
				gs.update(goods);
			}else{
				gs.save(goods);
			}
			request.getRequestDispatcher("goodslist.do").forward(request, response);
		}else if (path.equals("goodsdel")) {
			String[] ids = request.getParameterValues("gids");
			gs.delete(ids);
			response.sendRedirect("goodslist.do");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
