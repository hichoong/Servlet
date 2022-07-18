package com.kh.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/hello")
public class TestServlet extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("서비스 메소드 호출됨...!!!");
		//웹 페이지 head에 넣을 변수 설정
		String name = req.getParameter("name");
		//body 만들
		resp.getWriter().println("<h1>hello</h1>");
		//웹 페이지 head 부분에서 작성된 변수의 값을 body에 작성
		resp.getWriter().println(name);
		
	}
	
}
