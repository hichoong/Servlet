<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%
	//클라이언트 요청한 숫자 가져오기
 	String game_ = request.getParameter("game");
	int game = Integer.parseInt(game_);
	
	ArrayList lottoList = new ArrayList();
	
	for(int i =0; i<game; i++) {
		//배열에 숫자 넣기
		ArrayList lotto = new ArrayList();
		lotto.add(1);
		lotto.add(2);
		lotto.add(3);
		lotto.add(4);
		lotto.add(5);
		lotto.add(6);
		
		lottoList.add(lotto);
	}
	
	
	
 %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	
		<%for(int i=0; i<game; i++){%>
			<h1><%=lottoList.get(i)%></h1>
		<%}%>
	
	<h1></h1>
</body>
</html>