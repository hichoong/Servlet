<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<h1>main.jsp </h1>
	
	<h2>회원가입</h2>
	
	<form action="/mybatis/member/insert" method="post">
	
		아이디 : <input type="text" name="userId" class="form-control">
		<br>
		
		패스워드 :  <input type="password" name="userPwd" class="form-control">
		<br>
		<input type="submit" value="회원가입" class="btn btn-danger"> 
	
	</form>
	
	<hr>
	<hr>
	<hr>
	
	<h2>로그인</h2>
	
	<form action="/mybatis/member/select" method="post">
	
		아이디 : <input type="text" name="userId" class="form-control">
		<br>
		
		패스워드 :  <input type="password" name="userPwd" class="form-control">
		<br>
		<input type="submit" value="로그인" class="btn btn-info"> 
	
	</form>
</body>
</html>