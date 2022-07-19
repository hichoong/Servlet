<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%@ include file="/views/common/header.jsp" %>

	<h1>JSP</h1>
	<a href="views/first.jsp">이동하기(div색 바꾸기)</a> 
	<br>
	<a href="views/second.jsp">이동하기(글자색 바꾸기)</a>
	<br>
	<a href="views/third.jsp?num=0">이동하기(정수 판단)</a>
	<br>
	<a href="views/fourth.jsp">이동하기(반복문)</a>
	
	<%@ include file="/views/common/footer.jsp" %>
</body>
</html>