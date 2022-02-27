<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../taglib.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
	<title>便利店销售管理系统——添加商品</title>
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
	<script src="${ctx}/js/ligerUI/js/plugins/ligerResizable.js" type="text/javascript"></script>
	<link href="${ctx}/css/pager.css" type="text/css" rel="stylesheet" />
	<script type="text/javascript">
	
	$(function(){
    	/** 员工表单提交 */
		$("#goodsForm").submit(function(){
			var name = $("#name");
			var bcode = $("#b_code");
			var price = $("#price");
			var unit = $("#unit");
			var vprice = $("#v_price");
			var msg = "";
			if ($.trim(name.val()) == ""){
				msg = "商品名不能为空！";
				name.focus();
			}else if ($.trim(bcode.val()) == ""){
				msg = "条码不能为空！";
				bcode.focus();
			}else if ($.trim(price.val()) == ""){
				msg = "价格不能为空！";
				price.focus();
			}else if ($.trim(unit.val()) == ""){
				msg = "计量不能为空！";
				unit.focus();
			}else if ($.trim(vprice.val()) == ""){
				msg = "会员价不能为空！";
				vprice.focus();
			}
			if (msg != ""){
				$.ligerDialog.error(msg);
				return false;
			}else{
				return true;
			}
			$("#goodsForm").submit();
		});
    });
		
	
	</script>
</head>
<body>
<table width="100%" border="0" cellpadding="0" cellspacing="0">
  <tr><td height="10"></td></tr>
  <tr>
    <td width="15" height="32"><img src="${ctx}/images/main_locleft.gif" width="15" height="32"></td>
	<td class="main_locbg font2"><img src="${ctx}/images/pointer.gif">&nbsp;&nbsp;&nbsp;当前位置：商品管理  &gt; 添加/编辑商品</td>
	<td width="15" height="32"><img src="${ctx}/images/main_locright.gif" width="15" height="32"></td>
  </tr>
</table>
<form id="goodsForm" action="${ctx }/goodsAddOrUpdate.do" method="post">
	<table class="table  table-condensed">
		<tr>
			<th colspan="2">
			<c:choose>
				<c:when test="${info=='add' }">
					添加商品
				</c:when>
				<c:otherwise>
					商品编辑
				</c:otherwise>
			</c:choose>
			</th>
			<th><input type="hidden" name="gid" value="${goods.gid }"></th>
		</tr>
		<tr>
			<td align="right">商品名称:</td>
			<td>
				<input id="name" name="name" value="${goods.name }" >
			</td>
			<td id="zh"></td>
		</tr>
		<tr>
			<td align="right">商品条码号:</td>
			<td><input id="b_code" name="b_code" value="${goods.b_code }"></td>
			<td id="code"></td>
		</tr>
		<tr>
			<td align="right">商品类型:</td>
			<td><select name="tid">
					<option value="0">--请选择--</option>
					<c:forEach items="${gtList }" var="gtype">
						<c:choose>
							<c:when test="${gtype.tid==goods.goodsType.tid }">
								<option value="${gtype.tid }" selected="selected">${gtype.tname }</option>
							</c:when>
							<c:otherwise>
								<option value="${gtype.tid }">${gtype.tname }</option>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</select>
			</td>
			<td></td>
		</tr>
		<tr>
			<td align="right">商品价格:</td>
			<td><input id="price" type="number" name="price" value="${goods.price }"></td>
			<td id="lprice"></td>
		</tr>
		<tr>
			<td align="right">计量单位:</td>
			<td><input id="unit" name="unit" value="${goods.unit }"></td>
			<td id="lunit"></td>
		</tr>
		<tr>
			<td align="right">供应厂商:</td>
			<td>
				<select name="sid">
					<option value="0">--请选择--</option>
					<c:forEach items="${supList }" var="sup">
						<c:choose>
							<c:when test="${sup.sid==goods.sup.sid }">
								<option value="${sup.sid }" selected="selected">${sup.name }</option>
							</c:when>
							<c:otherwise>
								<option value="${sup.sid }">${sup.name }</option>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</select>
			</td>
			<td id="sup"></td>
		</tr>
		<tr>
			<td align="right">商品数量:</td>
			<td><input id="num" type="number" name="num" value="${goods.num }"></td>
			<td id="vprice"></td>
		</tr>
		<tr>
			<td align="right">会员价:</td>
			<td><input id="v_price" type="number" name="v_price" value="${goods.v_price }"></td>
			<td id="vprice"></td>
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
			<td>&nbsp;</td>
		</tr>
	</table>
	</form>
</body>
</html>