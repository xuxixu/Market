package com.gec.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gec.bean.Chart;
import com.gec.bean.OrderUtil;
import com.gec.bean.PageBean;
import com.gec.bean.Store;
import com.gec.service.ChartService;
import com.gec.service.StoreService;
import com.gec.service.impl.ChartServiceImpl;
import com.gec.service.impl.StoreServiceImpl;

@WebServlet(value={"/countchart.do","/sales.do"})
public class ChartsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	StoreService stores = new StoreServiceImpl();
	ChartService cs = new ChartServiceImpl();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getRequestURI();
		// 截取出请求名
		path = path.substring(path.lastIndexOf("/") + 1, path.lastIndexOf(".do"));
		if(path.equals("countchart")){
			//查询到所有便利店
			List<Store> list = stores.findAll();
			//查询出所有便利店及对应的营业额
			List<Chart> totals = cs.getYearTotal(2022, list);
			ObjectMapper mapper = new ObjectMapper();
			String json = mapper.writeValueAsString(totals);
			request.setAttribute("totals", json);
			request.getRequestDispatcher("WEB-INF/jsp/chart/turnoverchart.jsp").forward(request, response);
		}else if(path.equals("sales")){
			int pageIndex = 1;
			//后续进入,获取当前页面值
			String index = request.getParameter("pageIndex");
			//获取门店id
			int sid = 0;
			String cd = null;
			if(index!=null){
				pageIndex = Integer.valueOf(index);
				sid = Integer.valueOf(request.getParameter("sid"));
				cd = request.getParameter("checkDate");
			}
			Calendar date = Calendar.getInstance();
			int year=0, month = 0, day = 0;
			if("本月".equals(cd)){
 		    	month = date.get(Calendar.MONTH)+1;
 		    }else if("上月".equals(cd)){
 		    	month = date.get(Calendar.MONTH);
 		    	if(month==0){
 		    		month=12;
 		    		year = date.get(Calendar.YEAR)-1;
 		    	}
 		    }else if("今日".equals(cd)){
 		    	day = date.get(Calendar.DAY_OF_MONTH);
 		    }else if("昨日".equals(cd)){
 		    	day = date.get(Calendar.DAY_OF_MONTH)-1;
 		    }else {
				cd = "年度";
			}
			PageBean<OrderUtil> pb = cs.findPage(sid,year, month, day, pageIndex);
			List<Store> s = stores.findAll();
			if(pb.getList().size()!=0){
				String goodsName = cs.getMaxName(year, month, day, sid);
				request.setAttribute("goodsName", goodsName);
			}
			int total = cs.getTotalSales(year, month, day, sid);
			request.setAttribute("storeList", s);
			request.setAttribute("pageModel", pb);
			request.setAttribute("totalSales", total);
			request.setAttribute("sid", sid);
			request.setAttribute("checkDate", cd);
			request.getRequestDispatcher("WEB-INF/jsp/chart/saleschart.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
