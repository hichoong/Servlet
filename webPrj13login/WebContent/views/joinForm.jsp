<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입 페이지</title>
</head>
<body>
	<h1>회원가입</h1>
	
	<form action="/loginPrj/join" method="post">
		아이디 : <input type="text" name="userId">
		<br>
		비밀번호 : <input type="password" name="userPwd">
		<br>
		닉네임 : <input type="text" name="userNick">
		<br>
		<input type="submit" value="회원가입">
	</form>
</body>
</html>