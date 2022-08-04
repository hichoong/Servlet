package com.kh.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.member.service.MemberService;
import com.kh.member.vo.MemberVo;
@WebServlet(urlPatterns = "/member/myPage")
public class MemberMypageController extends HttpServlet {

	/*
	 * 마이페이지 화면 보여주기
	 * 
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		MemberVo loginMember =  (MemberVo)req.getSession().getAttribute("loginMember");
		if(loginMember != null) {
			req.getRequestDispatcher("/views/member/myPageForm.jsp").forward(req, resp);
		} else {
			req.getSession().setAttribute("alertMsg", "로그인 후 접근 가능합니다!");
			resp.sendRedirect(req.getContextPath());
		}	
	}

	/*
	 * 마이페이지 회원정보 수정하기
	 * 
	 * 컨트롤러
	 * -데이터 받기 -> 객체
	 * -서비스 호출(객체 전달) 
	 * -실행결과에 따라 화면 출력
	 * 
	 * 서비스
	 * -비지니스 로직 처리(자바 || SQL)
	 * -결과에 따라 트랜잭션 처리(commit || rollback)
	 * -실행결과 리턴(controller)
	 * 
	 * DAO
	 * -SQL 실행(서비스에서 전달받은 객체 활용) 
	 * 	--select쿼리 -> rs를 객체로 변환
	 * 	--DML쿼리 -> 추가작업 X (int로 리턴)
	 * -SQL 실행결과 리턴(service) 	 
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
			// 데이터 받기 -> 객체
			req.setCharacterEncoding("UTF-8");
			int no = ((MemberVo)req.getSession().getAttribute("loginMember")).getNo();
			String name = req.getParameter("memberName");
			String phone = req.getParameter("memberPhone");
			String email = req.getParameter("memberEmail");
			String addr = req.getParameter("memberAddr");
			String interest[] = req.getParameterValues("interest");
			//빈 배열에 대한 방어코드 
			if(interest == null) {
				interest = new String[]{""};
			}
			
			
			MemberVo vo = new MemberVo();
			vo.setNo(no);
			vo.setName(name);
			vo.setPhone(phone);
			vo.setEmail(email);
			vo.setAddr(addr);
			vo.setInterest(String.join(",", interest));
			
			
			// 서비스 호출(객체 전달) 
			MemberVo updateVo = new MemberService().edit(vo);
		
			// 실행결과에 따라 화면 출력
			if(updateVo != null) {
				//성공화면
				req.getSession().setAttribute("alertMsg", "회원정보 수정 성공!!");
				req.getSession().setAttribute("loginMember", updateVo);
				resp.sendRedirect("/semi/member/myPage");
			}else {
				//실패화면
				req.setAttribute("errorMsg", "회원 정보 수정 실패 !");
				req.getRequestDispatcher("/views/error/errorPage.jsp").forward(req, resp);
			}
	}
	
}
