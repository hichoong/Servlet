package com.kh.controller;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet(urlPatterns = "/subway")
public class SubWayServlet extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		
	resp.setContentType("text/plain;charset=utf-8");	
	//생년 8자리의 숫자 요청
	String birth = req.getParameter("birth");
	//생년 8자리에서 년도수 추출
	String year_ = birth.substring(0,4);
	int year = Integer.parseInt(year_);
	
	int todayYear = LocalDate.now().getYear();
	
	int age =  todayYear - year + 1;
	if(age >= 65) {
		System.out.println(age + " 무료");
		resp.getWriter().println(age + " 무료");
	}else if (age < 65 && age >= 20) {
		System.out.println(age+ " 1250");
		resp.getWriter().println(age+ " 1250");
	}else if (age >=14 && age < 20) {
		System.out.println(age+ " 720");
		resp.getWriter().println(age+ " 720");
	}
	else if (age >=8 && 14> age) {
		System.out.println(age+ " 450");
		resp.getWriter().println(age + " 450");
	}else {
		System.out.println(age+ " 무료");
		resp.getWriter().println(age+" 무료");
	}
	
	}
}
