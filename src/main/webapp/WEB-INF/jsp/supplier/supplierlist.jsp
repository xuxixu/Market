<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="f"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath }" />
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>便利店销售管理系统 ——供应商管理</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="pragma" content="no-cache" />
<meta http-equiv="cache-control" content="no-cache" />
<meta http-equiv="expires" content="0" />
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3" />
<meta http-equiv="description" content="This is my page" />
<link href="${ctx}/css/css.css" type="text/css" rel="stylesheet" />
<link rel="stylesheet" type="text/css"
	href="${ctx}/js/ligerUI/skins/Aqua/css/ligerui-dialog.css" />
<link href="${ctx}/js/ligerUI/skins/ligerui-icons.css" rel="stylesheet"
	type="text/css" />
<link href="${ctx}/js/metronic/plugins/bootstrap/css/bootstrap.min.css"
	rel="stylesheet" type="text/css">
<script type="text/javascript" src="${ctx }/js/jquery-1.11.0.js"></script>
<script type="text/javascript" src="${ctx }/js/jquery-migrate-1.2.1.js"></script>
<script type="text/javascript"></script>
<script src="${ctx}/js/ligerUI/js/core/base.js" type="text/javascript"></script>
<script src="${ctx}/js/ligerUI/js/plugins/ligerDrag.js"
	type="text/javascript"></script>
<script src="${ctx}/js/ligerUI/js/plugins/ligerDialog.js"
	type="text/javascript"></script>
<script src="${ctx}/js/ligerUI/js/plugins/ligerResizable.js"
	type="text/javascript"></script>
<link href="${ctx}/css/pager.css" type="text/css" rel="stylesheet" />
<script type="text/javascript"
	src="${ctx}/js/metronic/plugins/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript">

	function toPage(pageIndex)
	{
		$("#pageIndex").attr("value",pageIndex);
	    $("#supplierform").attr("action", "${ctx}/findSupplier.do");
		$("#supplierform").submit();
	}
	
	function pagerJump()
	{
		var page_size=$('#pager_jump_page_size').val();
			
			var regu = /^[1-9]\d*$/;
				
			if (!regu.test(page_size)||page_size < 1 || page_size >${pageModel.pageCount})
			{
				alert('请输入[1-'+ ${pageModel.pageCount} +']之间的页码！');	
			}else
			{
	 		    $("#pageIndex").attr("value",page_size);
		        $("#supplierform").attr("action", "${ctx}/findSupplier.do");
		    	$("#supplierform").submit();
			}
	}
	
	$(function(){
 	   /** 获取上一次选中的部门数据 */
 	   var boxs  = $("input[type='checkbox'][id^='box_']");
 	   
 	   /** 给全选按钮绑定点击事件  */
	   $("#checkAll").click(function(){
	       // this是checkAll  this.checked是true
	       // 所有数据行的选中状态与全选的状态一致
	       boxs.attr("checked",this.checked);
	   })
	   
	    /** 给数据行绑定鼠标覆盖以及鼠标移开事件  */
	    $("tr[id^='data_']").hover(function(){
	    	$(this).css("backgroundColor","#eeccff");
	    },function(){
	    	$(this).css("backgroundColor","#ffffff");
	    })

	    	
 	   /** 删除员工绑定点击事件 */
 	   $("#delete").click(function(){
 		   /** 获取到用户选中的复选框  */
 		   var checkedBoxs = boxs.filter(":checked");
 		   if(checkedBoxs.length < 1){
 			   alert("请选择一个需要删除的用户！");
 		   }else{
 			    $("#supplierform").attr("action", "${ctx}/deleteSupplier.do");
 		 		$("#supplierform").submit();
 		   }
 	   })
 	   
 	   $("#query").click(function(){
 		   	//设置隐藏域中的页面编号为1
 		   	$("#pageIndex").attr("value",1);
 		 	$("#supplierform").submit();
 	   })
 	   
 	   
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
				src="${ctx}/images/pointer.gif">&nbsp;&nbsp;&nbsp;当前位置：供应商管理
				&gt; 供应商查询</td>
			<td width="15" height="32"><img
				src="${ctx}/images/main_locright.gif" width="15" height="32"></td>
		</tr>
	</table>
	<form name="supplierform" class="form-inline" method="post" id="supplierform"
		action="${ctx}/findSupplier.do">

		<!-- 配置pageIndex的隐藏域 -->
		<input type="hidden" name="pageIndex" value="${pageModel.pageIndex}"
			id="pageIndex">
		<table width="100%" height="90%" border="0" cellpadding="5"
			cellspacing="0" class="main_tabbor">
			<!-- 查询区  -->
			<tr valign="top">
				<td height="30">
					<table width="100%" border="0" cellpadding="0" cellspacing="10"
						class="main_tab">
						<tr>
							<td class="fftd">
								账号:<input type="text" class="form-control"
										  value="${supplier.number }" name="number">
								供应商名:<input type="text" class="form-control"
										   value="${supplier.name }" name="name">
								<button type="submit" class="btn btn-default" id="query">搜索</button>
								<input type="button" class="btn btn-default" value="删除" id="delete" />
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
							<td><input type="checkbox" name="checkAll" id="checkAll"></td>
							<td>账号</td>
							<td>供应商名</td>
							<td>描述</td>
							<td>操作</td>
						</tr>
						<c:forEach items="${pageModel.list }" var="user" varStatus="stat">
							<tr id="data_${stat.index}" align="center" class="main_trbg">
								<td><input type="checkbox" id="box_${stat.index}"
									value="${user.sid}" name="sids" /></td>
								<td>${user.number }</td>
								<td>${user.name }</td>
								<td>${user.remark }</td>
								<td align="center" width="40px;"><a
									href="${ctx}/editSupplier.do?sid=${user.sid}"> <img title="修改"
										src="${ctx}/images/update.gif" /></a></td>
							</tr>
						</c:forEach>
					</table>
				</td>
			</tr>
			<!-- 分页标签 -->
			<tr valign="top">
				<td align="center" class="font3"><%@include
						file="/page/page.jsp"%></td>
			</tr>
		</table>

	</form>
</body>
</html>