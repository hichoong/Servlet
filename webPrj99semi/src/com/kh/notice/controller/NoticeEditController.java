package com.kh.notice.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.notice.service.NoticeService;
import com.kh.notice.vo.NoticeVo;
@WebServlet(urlPatterns = "/notice/edit")
public class NoticeEditController extends HttpServlet{
	
	//공지사항 수정하기 화면 보여주기
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//데이터 꺼내기
		String num = req.getParameter("num");
		NoticeVo vo = new NoticeService().selectOne(num);
		//결과에 따라 화면선택
		if(vo != null) {
			//성공
			req.setAttribute("vo", vo);
			req.getRequestDispatcher("/views/notice/noticeEdit.jsp").forward(req, resp);
		}else {
			//실패
			req.setAttribute("errorMsg", "공지사항 에러발생!");
			req.getRequestDispatcher("/views/error/errorPage.jsp").forward(req, resp);
		}
	}
	//공지사항 수정하기 작성
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//데이터 꺼내기 
		String title = req.getParameter("title");
		String content = req.getParameter("content");
		String num = req.getParameter("num");
		//데이터 뭉치기
		
		//서비스 호출
		
		//결과에 따라 화면 선택
	}
}
