package com.kh.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet(urlPatterns = "/member/join")
public class MemberJoinServlet extends HttpServlet{
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		//전달받은 데이터들 모두 꺼내서 출력하기
		
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; charset=UTF-8");
		
		
		String memberName = req.getParameter("memberName");
		String gender = req.getParameter("gender");
		String age = req.getParameter("age");
		//value가 여러개인 것은 배열로 받아줄 수 있음.
		String[] foods = req.getParameterValues("foods");
		
		//웹 브라우저에 확인하기
		PrintWriter out =  resp.getWriter();
		out.println(memberName);
		out.println(gender);
		out.println(age);
		
		if(foods != null ) {
			for(String x : foods){
				out.println("foods : " + x);
			}
		}
		
		//서블릿에 확인하기
		System.out.println(memberName);
		System.out.println(gender);
		System.out.println(age);
		
		
		if(foods != null ) {
			for(String x : foods){
				System.out.println("foods : " + x);
			}
		}
	}
	
	
}
