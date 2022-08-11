<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="com.kh.notice.vo.NoticeVo" %>
<%
	NoticeVo vo = (NoticeVo)request.getAttribute("vo");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항 수정하기</title>
<style>
    #outer{
        border: 1px dashed white;
        background-color: black;
        color: white;
        width: 60%;
        height: 70vh;
        margin: auto;
      
    }
    #outer h1{
        padding-top: 50px;
        text-align: center;
    }
    #section{
        width: 90%;
        height: 60%;
        margin: auto;
        border: 1px solid green;
       
    }
    #section *{
        width: 100%;
    }
    #div-btn-area{
        width: 30%;
        margin: auto;
        margin-top: 30px;
    }
</style>
</head>
<body>
	<%@include file="/views/common/header.jsp" %>
    <div id="outer">
        <br>
        <h1>공지사항 수정하기</h1>
        <form action="<%=contextPath%>/notice/edit" method="post">
        	<input type="hidden" name="num" value="<%=vo.getNo()%>">
            <input type="hidden" name="writerNo" value="<%=loginMember.getNo()%>">
            <div id="section">
                제목<input type="text" name="title" required size="101" value="<%=vo.getTitle()%>">
                <br>
                내용
                <br>
                <textarea name="content" id="" cols="107" rows="8" style="resize: none;" required><%=vo.getContent()%></textarea>
            </div>
            <div id="div-btn-area">
                <input type="submit" value="수정하기" >
                <input type="reset" value="초기화">
                <input type="button" value="뒤로가기" onclick="history.go(-1)">
            </div>
        </form>
    </div>
</body>
</html>