<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%@ include file="../taglib.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title>人事管理系统——添加供应商</title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta http-equiv="pragma" content="no-cache" />
	<meta http-equiv="cache-control" content="no-cache" />
	<meta http-equiv="expires" content="0" />
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3" />
	<meta http-equiv="description" content="This is my page" />
	<link href="${ctx}/css/css.css" type="text/css" rel="stylesheet" />
	<link rel="stylesheet" type="text/css" href="${ctx}/js/ligerUI/skins/Aqua/css/ligerui-dialog.css"/>
	<link href="${ctx}/js/ligerUI/skins/ligerui-icons.css" rel="stylesheet" type="text/css" />
	<link href="${ctx}/js/metronic/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet">
	<script type="text/javascript" src="${ctx }/js/jquery-1.11.0.js"></script>
	<script type="text/javascript" src="${ctx }/js/jquery-migrate-1.2.1.js"></script>
	<script src="${ctx}/js/ligerUI/js/core/base.js" type="text/javascript"></script>
	<script src="${ctx}/js/ligerUI/js/plugins/ligerDrag.js" type="text/javascript"></script>
	<script src="${ctx}/js/ligerUI/js/plugins/ligerDialog.js" type="text/javascript"></script>
	<script src="${ctx}/js/ligerUI/js/plugins/ligerResizable.jss" type="text/javascript"></script>
	<link href="${ctx}/css/pager.css" type="text/css" rel="stylesheet" />
	<script type="text/javascript">

		$(function(){
			/** 员工表单提交 */
			$("#supplierForm").submit(function(){
				var number = $("#number");
				var name = $("#name");
				var rmark = $("#rmark");
				var msg = "";
				if ($.trim(number.val()) == ""){
					msg = "number不能为空！";
					number.focus();
				}
				if ($.trim(name.val()) == ""){
					msg = "name不能为空！";
					name.focus();
				}
				if (msg != ""){
					$.ligerDialog.error(msg);
					return false;
				}else{
					return true;
				}
				$("#supplierForm").submit();
			});
		});


	</script>
</head>
<body>
<table width="100%" border="0" cellpadding="0" cellspacing="0">
	<tr><td height="10"></td></tr>
	<tr>
		<td width="15" height="32"><img src="${ctx}/images/main_locleft.gif" width="15" height="32"></td>
		<td class="main_locbg font2"><img src="${ctx}/images/pointer.gif">&nbsp;&nbsp;&nbsp;当前位置：供应商管理  &gt; 添加供应商</td>
		<td width="15" height="32"><img src="${ctx}/images/main_locright.gif" width="15" height="32"></td>
	</tr>
</table>
<form action="${ctx}/supplierAddOrUpdate.do" method="post">
	<table class="table  table-condensed">
		<tr>
			<th colspan="2">
				<c:choose>
					<c:when test="${info=='add' }">
						增加供应商
					</c:when>
					<c:otherwise>
						供应商编辑
					</c:otherwise>
				</c:choose>
			</th>
			<th><input type="hidden" name="sid" value="${supplier.sid }"></th>
		</tr>
		<tr>
			<td align="right">名称:</td>
			<td>
				<c:choose>
					<c:when test="${info=='add' }">
						<input id="number" name="number">
					</c:when>
					<c:otherwise>
						<input name="number" value="${supplier.number }" readonly="readonly">
					</c:otherwise>
				</c:choose>
			</td>
		</tr>
		<tr>
			<td align="right">供应商名称:</td>
			<td><input name="name" value="${supplier.name }"></td>
			<td id="name"></td>
		</tr>
		<tr>
			<td align="right">供应商描述:</td>
			<td colspan="2"><input name="remark" value="${supplier.remark }"></td>
		</tr>
		<tr>
			<td colspan="2">
				<c:choose>
					<c:when test="${info=='add' }">
						<input type="submit" value="提 交">&nbsp;
					</c:when>
					<c:otherwise>
						<input type="submit" value="修 改">&nbsp;
					</c:otherwise>
				</c:choose>

				<input type="reset" value="重 置"/>
			</td>
		</tr>
	</table>
</form>
</body>
</html>