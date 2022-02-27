<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%@ include file="../taglib.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title>人事管理系统——添加订单</title>
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
			$("#userForm").submit(function(){
				var username = $("#username");
				var status = $("#status");
				var loginname = $("#loginname");
				var password = $("#password");
				var msg = "";
				if ($.trim(username.val()) == ""){
					msg = "姓名不能为空！";
					username.focus();
				}else if ($.trim(status.val()) == ""){
					msg = "状态不能为空！";
					status.focus();
				}else if ($.trim(loginname.val()) == ""){
					msg = "登录名不能为空！";
					loginname.focus();
				}else if ($.trim(password.val()) == ""){
					msg = "密码不能为空！";
					password.focus();
				}
				if (msg != ""){
					$.ligerDialog.error(msg);
					return false;
				}else{
					return true;
				}
				$("#userForm").submit();
			});
			$("#loginname").blur(function(){
				$.ajax({
					url:"${ctx}/checkName.action",
					type:"get",
					data:{"loginname":"admin"},
					dataType:"json",
					success:function(data){
						alert(data.pageIndex);
					},
					error:function(){
						alert("执行异常!");
					}
				})
			})
		});


	</script>
</head>
<body>
<table width="100%" border="0" cellpadding="0" cellspacing="0">
	<tr><td height="10"></td></tr>
	<tr>
		<td width="15" height="32"><img src="${ctx}/images/main_locleft.gif" width="15" height="32"></td>
		<td class="main_locbg font2"><img src="${ctx}/images/pointer.gif">&nbsp;&nbsp;&nbsp;当前位置：用户管理  &gt; 添加用户</td>
		<td width="15" height="32"><img src="${ctx}/images/main_locright.gif" width="15" height="32"></td>
	</tr>
</table>
<form action="addOrUpdate.do" method="post">
	<table class="table  table-condensed">
		<tr>
			<th colspan="2">
				<c:choose>
					<c:when test="${info=='add' }">
						用户注册
					</c:when>
					<c:otherwise>
						用户编辑
					</c:otherwise>
				</c:choose>
			</th>
			<th><input type="hidden" name="uid" value="${user.uid }"></th>
		</tr>
		<tr>
			<td align="right">账户名:</td>
			<td>
				<c:choose>
					<c:when test="${info=='add' }">
						<input id="number" name="number">
					</c:when>
					<c:otherwise>
						<input name="number" value="${user.number }" readonly="readonly">
					</c:otherwise>
				</c:choose>
			</td>
			<td id="zh"></td>
		</tr>
		<tr>
			<td align="right">用户名称:</td>
			<td><input name="userName" value="${user.userName }"></td>
			<td id="name"></td>
		</tr>
		<tr>
			<td align="right">密码:</td>
			<td><input type="password" name="pwd" value="${user.password }"></td>
			<td id="lpwd"></td>
		</tr>
		<tr>
			<td align="right">性别:</td>
			<td>
				<c:choose>
					<c:when test="${user.sex=='男' }">
						<input type="radio" name="sex" checked="checked" value="男">男
						<input type="radio" name="sex" value="女">女
					</c:when>
					<c:otherwise>
						<input type="radio" name="sex" value="男">男
						<input type="radio" name="sex" value="女" checked="checked">女
					</c:otherwise>
				</c:choose>
			</td>
			<td id="sex"></td>
		</tr>
		<tr>
			<td align="right">手机号:</td>
			<td><input type="number" name="phone" value="${user.phone }"></td>
			<td id="phone"></td>
		</tr>
		<tr>
			<td align="right">角色:</td>
			<td colspan="2">
				<select name="rid">
					<option value="0">请选择</option>
					<c:forEach items="${roleList }" var="role">
						<c:choose>
							<c:when test="${role.id==user.role.id }">
								<option value="${role.id }" selected="selected">${role.r_name }</option>
							</c:when>
							<c:otherwise>
								<option value="${role.id }">${role.r_name }</option>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</select>
			</td>
		</tr>
		<tr>
			<td align="right">用户描述:</td>
			<td colspan="2"><input name="remark" value="${user.remark }"></td>
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
			<td id="phone">&nbsp;</td>
		</tr>
	</table>
</form>
</body>
</html>