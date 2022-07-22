package com.kh.lotto.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet(urlPatterns = "/sj")
public class LottoForwarding extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		//클라이언트 요청한 숫자 가져오기
	 	String game_ = req.getParameter("game");
		int game = Integer.parseInt(game_);
		//로또리스트 만들기
		ArrayList lottoList = new ArrayList();
		//로또6자리 번호 만들기
		for(int i =0; i<game; i++) {
			//배열에 숫자 넣기
			ArrayList lotto = new ArrayList();
			lotto.add(1);
			lotto.add(2);
			lotto.add(3);
			lotto.add(4);
			lotto.add(5);
			lotto.add(6);
			lottoList.add(lotto);
		}
		//데이터 담기
		req.setAttribute("abc", lottoList);
		//화면 작업 넘기기. 포워딩
		req.getRequestDispatcher("lottoView.jsp").forward(req, resp);
	}
}
