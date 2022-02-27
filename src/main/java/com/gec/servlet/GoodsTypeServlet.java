package com.gec.servlet;

import com.gec.bean.PageBean;
import com.gec.bean.GoodsType;
import com.gec.service.GoodsTypeService;
import com.gec.service.impl.GoodsTypeServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(value = { "/findGoodsType.do","/addGoodsType.do","/goodstypeAddOrUpdate.do","/editGoodsType.do","/deleteGoodsType.do" })
public class GoodsTypeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	GoodsTypeService rs = new GoodsTypeServiceImpl();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 获取到请求相对路径
		String path = request.getRequestURI();
		// 截取出请求名
		path = path.substring(path.lastIndexOf("/") + 1, path.lastIndexOf(".do"));
		String rname = request.getParameter("tname");
		String remark = request.getParameter("remark");
		if("findGoodsType".equals(path)){
			//设置默认当前页为1
			int pageIndex = 1;
			//获取到当前页
			String index = request.getParameter("pageIndex");
			if(index!=null)
				pageIndex = Integer.parseInt(index);
			GoodsType goodstype = new GoodsType();
			goodstype.setTname(rname);
			goodstype.setRemark(remark);
			PageBean<GoodsType> pb = rs.findPage(goodstype,pageIndex);
			List<GoodsType> goodstypeList = rs.findAll();
			request.setAttribute("goodstypeList", goodstypeList);
			request.setAttribute("goodstype", goodstype);
			request.setAttribute("pageModel", pb);
			request.getRequestDispatcher("WEB-INF/jsp/goodstype/goodstypelist.jsp").forward(request, response);
		}else if(path.equals("addGoodsType")){
			request.setAttribute("info", "add");
			request.getRequestDispatcher("WEB-INF/jsp/goodstype/addgoodstype.jsp").forward(request, response);
		}else if(path.equals("goodstypeAddOrUpdate")){
			String id = request.getParameter("id");
			GoodsType goodstype = new GoodsType();
			goodstype.setTname(rname);
			goodstype.setRemark(remark);
			//id存在,则表示执行的编辑操作
			if(id!=null&&!"".equals(id)){
				goodstype.setTid(Integer.parseInt(id));
				rs.update(goodstype);
			}else{  //不存在,则表示执行添加操作
				rs.add(goodstype);
			}
			response.sendRedirect("findGoodsType.do");
		}else if(path.equals("editGoodsType")){
			Integer id = Integer.parseInt(request.getParameter("id"));
			GoodsType goodstype = rs.findById(id);
			request.setAttribute("goodstype", goodstype);
			request.getRequestDispatcher("WEB-INF/jsp/goodstype/addgoodstype.jsp").forward(request, response);
		}else if(path.equals("deleteGoodsType")){
			String[] ids = request.getParameterValues("uids");
			rs.delete(ids);
			response.sendRedirect("findGoodsType.do");
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
