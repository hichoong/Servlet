package com.kh.user.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
@WebServlet(urlPatterns = "/user/logout")
public class UserLogoutServlet extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//로그아웃 처리하기 == 세션만료
		//세션 얻어오기
		HttpSession session = req.getSession();
		//session.invalidate(); //세션 만료 메소드
		//인덱스 페이지 보여주기
		System.out.println("로그아웃 완료");
		session.setAttribute("msg", "로그아웃");
		resp.sendRedirect("/webPrj14/index.jsp");
		
	}
}
