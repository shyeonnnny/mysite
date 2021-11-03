<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% pageContext.setAttribute("newline", "\n"); %>
<!DOCTYPE html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.request.contextPath }/assets/css/guestbook.css" rel="stylesheet" type="text/css">

<script src="${pageContext.request.contextPath }/assets/js/jquery/jquery-1.9.0.js"></script>
<script>
$(function(){
	${"#btn-check-eamail"}.click(function(){
		var email = $("email").val();
		// var를 써서 email은 블락안에서만 쓸 수 있음
		//console.log(email);
		
		if(email == ''){
			return;
		}
		console.log(email);
		$ajax({
			url:"${pageContext.request.contextPath }/user/api/checkemail?email="+email,
			type: "get",
			dataType:"json",
			success: function(response){
				console.log(response);
			}
		});
		// 확인할때는 페이지에서 F11눌러서 봐가면서해야함
	});
	
	
};
setTimetout(function()){
	// ajax 
	//
	$.ajax({
		url : "/mysite03/hello",
		type : "get",
		dataType:"json",
//		data : Json.stringify({})
		success : function(response){
			console.log(response);
			p = $("#test");
			p.html("<strong>" + response.message + "</strong>");
		}
	});
	
	o = {
			message: "Hello World"
	};
	$p = $("#test");// #id를 넣어줘야함
	$p.html("<strong>" + o.message + ""</strong>");
//	e = document.getElementById("test");
//	e.innerHTML = "<strong>Hello World</string>";
},3000); // 타임아웃이 3초후에 코드 실행
								// 이게 비동기 방식
for(i=0; i<5; i++){
	console.log("Helloworld : " + i);
	
}
</script>
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/header.jsp" />
		<div id="content">
			<div id="guestbook">
				<form action="${pageContext.request.contextPath }/guestbook" method="post">
					<input type="hidden" name="ga" value="add">
					<table>
						<tr>
							<td>이름</td><td><input type="text" name="name"></td>
							<td>비밀번호</td><td><input type="password" name="password"></td>
						</tr>
						<tr>
							<td colspan=4><textarea name="message" id="content"></textarea></td>
						</tr>
						<tr>
							<td colspan=4 align=right><input type="submit" VALUE=" 확인 "></td>
						</tr>
					</table>
				</form>
				<ul>
					<c:set var='count' value='${fn:length(list) }' />
					<c:forEach items='${list }' var='vo' varStatus='status'>
						<li>
							<table>
								<tr>
									<td>[${count-status.index }]</td>
									<td>${vo.name }</td>
									<td>${vo.regDate }</td>
									<td><a href="${pageContext.request.contextPath }/guestbook?ga=deleteform&no=${vo.no }">삭제</a></td>
								</tr>
								<tr>
									<td colspan=4>
										${fn:replace(vo.message, newline, "<br/>") }	
									</td>
								</tr>
							</table>
							<br>
						</li>
					</c:forEach>
				</ul>
			</div>
		</div>
		<p>
		<p id="test">
		</p>
		<c:import url="/WEB-INF/views/includes/navigation.jsp" />
		<c:import url="/WEB-INF/views/includes/footer.jsp" />
	</div>
</body>
</html>