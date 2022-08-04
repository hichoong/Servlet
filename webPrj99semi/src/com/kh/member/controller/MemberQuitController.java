package com.kh.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.member.service.MemberService;
import com.kh.member.vo.MemberVo;
@WebServlet(urlPatterns = "/member/quit")
public class MemberQuitController extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		int no = ((MemberVo)req.getSession().getAttribute("loginMember")).getNo();
		 
		 int quit = new MemberService().quit(no);
		 
		 if(quit == 1) {
			 //회원탈퇴
			 req.getSession().removeAttribute("loginMember");
			 req.getSession().setAttribute("alertMsg", "회원탈퇴가 완료되었습니다.");
			 resp.sendRedirect("/semi");
			 
			
		 }else {
			 req.setAttribute("errorMsg", "회원탈퇴 실패");
			 req.getRequestDispatcher("/views/error/errorPage.jsp").forward(req, resp);
		 }
	}

	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	}
}
