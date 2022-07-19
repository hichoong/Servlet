<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
	String color = request.getParameter("color");
%>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	h1{
		color: <%= color%>;
	}
</style>
</head>
<body>
<%@ include file="/views/common/header.jsp" %>
	<h1>입력받은 글자색 : <%=color%></h1>
<%@ include file="/views/common/footer.jsp" %>
</body>

</html>