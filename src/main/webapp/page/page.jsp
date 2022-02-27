<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- 分页处理 -->
<table width="100%" align='center' style='font-size: 13px;'
	class="yahoo">
	<tr>
		<td
			style='COLOR: #0061de; MARGIN-RIGHT: 3px; PADDING-TOP: 2px; TEXT-DECORATION: none'>
			总${pageModel.pageCount}页数&nbsp;&nbsp;总记录数${pageModel.totalCount}条&nbsp;&nbsp;
			<c:choose>
				<c:when test="${pageModel.pageIndex==1}">
					<span class='disabled'>上一页</span>
					<c:forEach begin="1" end="${pageModel.pageCount}" var="i">
						<c:choose>
							<c:when test="${pageModel.pageIndex==i}">
								<span class='current'>${i}</span>
							</c:when>
							<c:otherwise>
								<a href='#' onclick="toPage(${i})">${i}</a>
							</c:otherwise>
						</c:choose>
					</c:forEach>
					<c:choose>
						<c:when test="${pageModel.pageIndex==pageModel.pageCount}">
							<span class='disabled'>下一页</span>
						</c:when>
						<c:otherwise>
							<span class='current'> <a href='#'
								onclick="toPage(${pageModel.pageIndex+1})">下一页</a></span>
						</c:otherwise>
					</c:choose>
				</c:when>
				<c:when test="${pageModel.pageIndex==pageModel.pageCount}">
					<span class='current'> <a href='#'
						onclick="toPage(${pageModel.pageIndex-1})">上一页</a>
					</span>
					<c:forEach begin="1" end="${pageModel.pageCount}" var="i">
						<c:choose>
							<c:when test="${pageModel.pageIndex==i}">
								<span class='current'>${i}</span>
							</c:when>
							<c:otherwise>
								<a href='#' onclick="toPage(${i})">${i}</a>
							</c:otherwise>
						</c:choose>
					</c:forEach>
					<span class='disabled'>下一页</span>
				</c:when>
				<c:otherwise>
					<span class='current'> <a href='#'
						onclick="toPage(${pageModel.pageIndex-1})">上一页</a>
					</span>
					<c:forEach begin="1" end="${pageModel.pageCount}" var="i">
						<c:choose>
							<c:when test="${pageModel.pageIndex==i}">
								<span class='current'>${i}</span>
							</c:when>
							<c:otherwise>
								<a href='#' onclick="toPage(${i})">${i}</a>
							</c:otherwise>
						</c:choose>
					</c:forEach>
					<span class='current'><a href='#'
						onclick="toPage(${pageModel.pageIndex+1})">下一页</a></span>
				</c:otherwise>
			</c:choose> &nbsp;跳转到&nbsp;&nbsp;<input type='text' size='2' id='pager_jump_page_size' /> &nbsp;
			<input class="btn btn-default" type='button' value='确定' id='pager_jump_btn' onclick="pagerJump()" />
		</td>
	</tr>
</table>