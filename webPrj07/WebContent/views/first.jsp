<%@page import="org.apache.catalina.connector.Request"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>	
	<%String color = request.getParameter("color");%>
<style>
	div{
		width: 100px;
		height: 100px;
		border: 1px solid black;
		background-color: <%=color%>; 
		
	}
</style>

<meta charset="UTF-8">
<title>Insert title here</title>
</head>

<body>
<%@ include file="/views/common/header.jsp" %>
	<%System.out.println(color); %>
	<h1>div 색 변경 (<%=color%>)</h1>
	
	<div></div>
	
	

	
	
<%@ include file="/views/common/footer.jsp" %>	
</body>
</html>