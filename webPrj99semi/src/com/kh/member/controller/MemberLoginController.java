package com.kh.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.member.service.MemberService;
import com.kh.member.vo.MemberVo;
@WebServlet(urlPatterns = "/member/login")
public class MemberLoginController extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//화면에서 입력한 데이터 얻어오기
		System.out.println("로그인 시도");
		String memberId = req.getParameter("memberId");
		String memberPwd = req.getParameter("memberPwd");
		
		//얻어온 데이터 객체에 담기
		MemberVo vo = new MemberVo();
		vo.setId(memberId);
		vo.setPwd(memberPwd);
		
		//서비스 로직 실행
		MemberVo loginMember = new MemberService().login(vo);
		
		//결과에 따라 화면 선택
		if(loginMember != null) {
			//로그인성공
			req.getSession().setAttribute("loginMember", loginMember);
			req.getSession().setAttribute("alertMsg", "로그인 성공");
			System.out.println(memberId+" 로그인 성공");
			resp.sendRedirect(req.getContextPath());
			//req.getRequestDispatcher("/semi").forward(req, resp);
		}else {
			//로그인실패
			req.setAttribute("errorMsg", "로그인 실패!");
			System.out.println(memberId + " 로그인 실패");
			req.getRequestDispatcher("/views/error/errorPage.jsp").forward(req, resp);
			
		}
		
	}//method
	
	
}//class
