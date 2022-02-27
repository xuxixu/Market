<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="f"%>
<%@ include file="../taglib.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title>便利店销售管理系统 ——部门管理</title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta http-equiv="pragma" content="no-cache" />
	<meta http-equiv="cache-control" content="no-cache" />
	<meta http-equiv="expires" content="0" />
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3" />
	<meta http-equiv="description" content="This is my page" />
	<link href="${ctx}/js/metronic/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css">
	<link href="${ctx}/css/css.css" type="text/css" rel="stylesheet" />
	<link rel="stylesheet" type="text/css"
		  href="${ctx}/js/ligerUI/skins/Aqua/css/ligerui-dialog.css" />
	<link href="${ctx}/js/ligerUI/skins/ligerui-icons.css" rel="stylesheet"
		  type="text/css" />
	<script type="text/javascript" src="${ctx }/js/jquery-1.11.0.js"></script>
	<script type="text/javascript" src="${ctx }/js/metronic/plugins/bootstrap/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="${ctx }/js/jquery-migrate-1.2.1.js"></script>
	<script src="${ctx}/js/ligerUI/js/core/base.js" type="text/javascript"></script>
	<script src="${ctx}/js/ligerUI/js/plugins/ligerDrag.js"
			type="text/javascript"></script>
	<script src="${ctx}/js/ligerUI/js/plugins/ligerDialog.js"
			type="text/javascript"></script>
	<script src="${ctx}/js/ligerUI/js/plugins/ligerResizable.js"
			type="text/javascript"></script>
	<link href="${ctx}/css/pager.css" type="text/css" rel="stylesheet" />

	<script type="text/javascript">

		function toPage(pageIndex)
		{
			$("#pageIndex").attr("value",pageIndex);
			$("#deptform").attr("action", "${ctx}/sales.do");
			$("#deptform").submit();
		}

		function pagerJump()
		{
			var page_size=$('#pager_jump_page_size').val();

			var regu = /^[1-9]\d*$/;

			if (!regu.test(page_size)||page_size < 1 || page_size >${pageModel.pageCount})
			{
				alert('请输入[1- ${pageModel.pageCount}]之间的页码！');
			}else
			{
				$("#pageIndex").attr("value",page_size);
				$("#deptform").attr("action", "${ctx}/sales.do");
				$("#deptform").submit();
			}
		}

		$(function(){
			/** 给数据行绑定鼠标覆盖以及鼠标移开事件  */
			$("tr[id^='data_']").hover(function(){
				$(this).css("backgroundColor","#eeccff");
			},function(){
				$(this).css("backgroundColor","#ffffff");
			})

			$("#query").click(function(){
				$("#pageIndex").attr("value",1);
				$("#deptform").attr("action", "${ctx}/sales.do");
				$("#deptform").submit();
			});
			$(".main_tab a").click(function(){
				$("#pageIndex").attr("value",1);
				var time = $(this).text();
				$("#checkDate").attr("value",time);
				$("#deptform").attr("action", "${ctx}/sales.do");
				$("#deptform").submit();
			});
		})
	</script>
</head>
<body>
<!-- 导航 -->
<table width="100%" border="0" cellpadding="0" cellspacing="0">
	<tr>
		<td height="10"></td>
	</tr>
	<tr>
		<td width="15" height="32"><img
				src="${ctx}/images/main_locleft.gif" width="15" height="32"></td>
		<td class="main_locbg font2"><img
				src="${ctx}/images/pointer.gif">&nbsp;&nbsp;&nbsp;当前位置：首页
			&gt; 报表管理   &gt; 销售报表</td>
		<td width="15" height="32"><img
				src="${ctx}/images/main_locright.gif" width="15" height="32"></td>
	</tr>
</table>
<form name="deptform" method="post" id="deptform">

	<!-- 配置pageIndex的隐藏域 -->
	<input type="hidden" name="pageIndex" value="${pageModel.pageIndex}" id="pageIndex">
	<input type="hidden" id="checkDate" name="checkDate">
	<table width="100%" height="90%" border="0" cellpadding="5"
		   cellspacing="0" class="main_tabbor table">
		<!-- 查询区  -->
		<tr valign="top">
			<td height="30">
				<table width="100%" border="0" cellpadding="0" cellspacing="10"
					   class="main_tab">
					<tr>
						<td>
							<ul class="nav nav-tabs">
								<li role="presentation"><a href="#" id="year">年度</a></li>
								<li role="presentation"><a href="#" id="now">今日</a></li>
								<li role="presentation"><a href="#" id="distory">昨日</a></li>
								<li role="presentation"><a href="#" id="nowMonth">本月</a></li>
								<li role="presentation"><a href="#" id="disMonth">上月</a></li>
								<li role="presentation"><a href="#">其它时间</a></li>
							</ul>
						</td>
					</tr>
					<tr>
						<td class="fftd" class="font3">
							数据时间:<span>${checkDate }</span>&nbsp;&nbsp;&nbsp;
							便利店：<select name="sid">
							<option value="0">请选择</option>
							<c:forEach items="${storeList }" var="store">
								<c:choose>
									<c:when test="${store.id==sid }">
										<option value="${store.id }" selected="selected">${store.name }</option>
									</c:when>
									<c:otherwise>
										<option value="${store.id }">${store.name }</option>
									</c:otherwise>
								</c:choose>
							</c:forEach>
						</select>
							<input class="btn" type="button" value="查询" id="query" />
						</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr valign="top">
			<td height="30">
				<table width="100%" border="0" cellpadding="0" cellspacing="10"
					   class="main_tab">
					<tr>
						<td class="fftd">
							营业额
							<p style="color: red;font-size: 20px">${totalSales }</p>
						</td>
						<td class="fftd">
							销售量最高的商品
							<p style="color: red;font-size: 20px">${goodsName }</p>
						</td>
					</tr>
				</table>
			</td>
		</tr>
		<!-- 数据展示区 -->
		<tr valign="top">
			<td height="20">
				<table width="100%" border="1" cellpadding="5" cellspacing="0"
					   style="border: #c2c6cc 1px solid; border-collapse: collapse;">
					<tr class="main_trbg_tit" align="center">
						<td>序号</td>
						<td>商品编号</td>
						<td>商品名称</td>
						<td>商品规格</td>
						<td>销售量</td>
						<td>销售额(元)</td>
					</tr>
					<c:forEach items="${pageModel.list}" var="ou" varStatus="stat">
						<tr id="data_${stat }" align="center" class="main_trbg">
							<td>${stat.index+1}</td>
							<td>${ou.goods.gid }</td>
							<td>${ou.goods.name }</td>
							<td>${ou.goods.unit}</td>
							<td>${ou.salesNum }</td>
							<td>${ou.salesMoney }</td>
						</tr>
					</c:forEach>
				</table>
			</td>
		</tr>
		<!-- 分页标签 -->
		<tr valign="top">
			<td align="center" class="font3">
				<%@include file="/page/page.jsp"%>
			</td>
		</tr>
	</table>
</form>
</body>
</html>