package com.newlecture.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
@WebServlet(urlPatterns = "/cnt")
public class Cnt extends HttpServlet {
@Override
public void service(ServletRequest req, ServletResponse resp) throws ServletException, IOException {
	
	resp.setCharacterEncoding("UTF-8");
	resp.setContentType("text/html; charset=UTF-8");
	PrintWriter out = resp.getWriter();
	
	String cnt_ = req.getParameter("cnt");
	int cnt = 100;
	if(cnt_ != null && !cnt_.equals("")) 
		cnt = Integer.parseInt(cnt_);	
	
	for(int i = 0; i<cnt; i++) {
		out.println((i+1)+ "  : 안녕 Hello Servlet!! <br>");
	}

}
}
