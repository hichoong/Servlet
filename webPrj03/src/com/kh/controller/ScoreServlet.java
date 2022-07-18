package com.kh.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet(urlPatterns = "/score")
public class ScoreServlet extends HttpServlet{

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
		resp.setContentType("text/plain;charset=utf-8");
		//resp.setCharacterEncoding("utf-8"); 
		int kor = Integer.parseInt(req.getParameter("kor")) ;
		int math = Integer.parseInt(req.getParameter("math"));
		int eng = Integer.parseInt(req.getParameter("eng"));
		int x = (kor+math+eng)/3 ;
		System.out.println(x);
		//클라이언트 측에 보여주기
		PrintWriter pw = resp.getWriter();
		pw.println("<h1>three object avg : " + x + "</h1>");
		pw.println("<h2>세과목의 합 : " + x + "</h2>");
		
		//resp.getWriter().println(x);
		
		
		
	}
	
	
}
