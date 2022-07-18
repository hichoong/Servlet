package com.kh.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/login")
public class TestServlet extends HttpServlet {
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("서비스 메소드 호출됨...");
		//사용자로부터 이름 얻기
		String name = req.getParameter("name");
		System.out.println("사용자로부터 받은 name : " + name);
		//사용자가 request 보낸 것을 받기
		
		
		
		
	}
	
	
}
