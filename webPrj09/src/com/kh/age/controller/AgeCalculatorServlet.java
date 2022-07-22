package com.kh.age.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet(urlPatterns = "/calcAge")
public class AgeCalculatorServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	//데이터 꺼내기
		String age_ = req.getParameter("age");
		int age = Integer.parseInt(age_);
	//복잡한, 어려운, 자바코드로 처리해야하는, 연산하기			
		int result = age+10;		
	//연산 결과 보여주기
		//resp.getWriter().println("<h1>"+result+"</h1>");
	//연산 결고 넘겨주기
		req.setAttribute("result", result);
		req.getRequestDispatcher("views/result.jsp").forward(req, resp);
		
		
		
		
	}
}
