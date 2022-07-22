<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인 페이지</title>
</head>
<body>
	<h1>로그인</h1>
	<form action="/loginPrj/login" method="post">
		아이디 : <input type="text" name="userId">
		<br>
		비밀번호 : <input type="password" name="userPwd">
		<input type="submit" value="로그인">
	</form>
</body>
</html>