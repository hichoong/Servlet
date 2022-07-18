package com.kh.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/join")
public class MemberJoin extends HttpServlet {

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		//사용자가 보낼 parameter 및 value 얻기
		String name = req.getParameter("name");
		String pwd = req.getParameter("pwd");
		String nick = req.getParameter("nick");
		//콘솔에 출력하기
		System.out.println("사용자가 보낸 이름 : " + name);
		System.out.println("사용자가 보낸 비밀번호 : " + pwd);
		System.out.println("사용자가 보낸 닉네임 : " + nick);
		//요청자에게 확인하기
		resp.getWriter().println("user write name : "+ name + " aright?");
		resp.getWriter().println("user wirte pwd : "+ pwd + " aright?");
		resp.getWriter().println("uwer wirte nick : "+ nick + " aright?");
	
		
		
	}
}
