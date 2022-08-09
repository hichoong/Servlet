<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@page import="com.kh.notice.vo.NoticeVo"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	ArrayList<NoticeVo> voList =  (ArrayList<NoticeVo>)request.getAttribute("voList");
	
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항</title>
<style>
	#outer{
		border: 1px dashed white;
		
		background-color: black;
		color: white;
		width: 60%;
		margin: 0 auto;
		padding-top: 50px;
		padding-bottom: 50px;
		display: flex;
		justify-content: center;
		align-items: center;
		flex-wrap: wrap;
		text-align: center;

		flex-direction: column;
	}
	#write{
		align-self: flex-end;
		margin-right: 2.5%;
		
	}
	#table-main{
		border: 1px solid white;
		width: 95%;	
	}
	#table-main>tbody tr:hover{
		background-color: gray;	
		cursor: pointer;
	}
</style>
</head>
<body>
	<%@include file="/views/common/header.jsp" %>
	<div id="outer">
		<h1>공지사항 리스트</h1>
		
			
			<%if(loginMember != null && loginMember.getId().equals("admin")) {%>
			<button id="write" class="btn btn-outline-success" onclick="location.href='/semi/notice/insert'">공지사항 작성</button>
			<%}else {%>
			<%}%>
		<table id="table-main">
			<thead>
				<tr>
					<td>글번호</td>
					<td>글제목</td>
					<td>작성자</td>
					<td>조회수</td>
					<td>작성일</td>
				</tr>
			</thead>
			<tbody>
				<%for(int i = 0; i < voList.size(); i++) {%>
					<tr>
						<td><%=voList.get(i).getNo()%></td>
						<td><%=voList.get(i).getTitle()%></td>
						<td><%=voList.get(i).getWriter()%></td>
						<td><%=voList.get(i).getCnt()%></td>
						<td><%=voList.get(i).getEnrollDate()%></td>
					</tr>
					<%}%>
			</tbody>
		</table>
	</div>
	<!--스크립트  -->
	<script>
		$(function(){
			$('#table-main>tbody>tr').click(function(){
				//행 클릭 되었을 때, 동작할 내용
				
				//글 번호 가져오기
				const num = $(this).children().eq(0).text();
				
				//해당 번호 이용해서 요청 보내기
				location.href='/semi/notice/detail?num='+num;
			});
		})
	</script>
</body>
</html>