<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link
	href="${pageContext.servletContext.contextPath }/assets/css/board.css"
	rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/header.jsp" />
		<div id="content">
			<div id="board">
				<form id="search_form" action="${pageContext.servletContext.contextPath }/board?ba=search&p=1" method="get">
					<input type="hidden" id="ba" name="ba" value='search'>
					<input type="hidden" id="p" name="p" value='1'>
					<input type="text" id="kwd" name="kwd" value='${param.kwd}'> <input
						type="submit" value="찾기">
				</form>
				<table class="tbl-ex">
					<tr>
						<th>번호</th>
						<th style="text-align: left">제목</th>
						<th>글쓴이${maxBoardNum }</th>
						<th>조회수${startPageNum }</th>
						<th>작성일</th>
						<th>&nbsp;</th>
					</tr>

					<c:forEach items="${boardList }" var="vo" varStatus="status">
						<tr>
							<td>${(maxBoardNum-(1+(status.index-1))-(10*(p-1)))}</td>
							<td style="text-align:left; padding-left:${(vo.depth-1)*20}px;">
								<c:choose>
									<c:when test='${vo.depth >= 1 }'>
										<img
											src="${pageContext.request.contextPath }/assets/images/reply.png" />
									</c:when>
								</c:choose> <a
								href="${pageContext.request.contextPath }/board?ba=viewform&no=${vo.no }">${vo.title }</a>
							</td>
							<td>${vo.user_name }</td>
							<td>${vo.hit }</td>
							<td>${vo.reg_date }</td>

							<c:choose>
								<c:when test="${vo.user_no  eq authUser.no }">
									<td><a
										href="${pageContext.request.contextPath }/board?ba=deleteform&no=${vo.no }"
										class="del">삭제</a></td>
								</c:when>
								<c:otherwise>
								</c:otherwise>
							</c:choose>

						</tr>

					</c:forEach>

				</table>

				<!-- pager 추가 -->
				<div class="pager">
					<ul class="row">
						<c:choose>
							<c:when test="${startPageNum eq 1 }">
							</c:when>
							<c:otherwise>
								<li><a
									href="${pageContext.request.contextPath }/board?ba&p=${startPageNum -1 }&kwd=${param.kwd}">◀</a></li>
							</c:otherwise>
						</c:choose>

						<c:forEach var="count" begin="${startPageNum }"
							end="${endPageNum }">
							<c:choose>
								<c:when test="${count eq p }">
									<li class="selected">${count }</li>
								</c:when>
								<c:otherwise>
									<li><a
										href="${pageContext.request.contextPath }/board?ba&p=${count }&kwd=${param.kwd}">${count }</a></li>
								</c:otherwise>
							</c:choose>
						</c:forEach>

						<c:choose>
							<c:when test="${endPageNum eq maxPageNum }">
								<li><h5 class="silber">▶</h5></li>
							</c:when>
							<c:otherwise>
								<li><a
									href="${pageContext.request.contextPath }/board?ba&p=${endPageNum + 1 }&kwd=${param.kwd}">▶</a></li>
							</c:otherwise>
						</c:choose>
					</ul>
				</div>
				<!-- pager 추가 -->

				<div class="bottom">
					<a href="${pageContext.request.contextPath }/board?ba=writeform"
						id="new-book">글쓰기</a>
				</div>
			</div>
		</div>
		<c:import url="/WEB-INF/views/includes/navigation.jsp" />
		<c:import url="/WEB-INF/views/includes/footer.jsp" />
	</div>
</body>
</html>