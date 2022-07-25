package com.kh.user.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.catalina.Session;

import com.kh.user.dto.UserDto;
@SuppressWarnings("serial")
@WebServlet(urlPatterns = "/user/login")
public class UserLoginServlet extends HttpServlet{
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			//1. data 꺼내기
			String userId = req.getParameter("userId");
			String userPwd = req.getParameter("userPwd");
			//2. 서비스 로직(아이디, 비번 맞는지)
			
			//3. DB 조회 (아이디, 패스워드 전달받아서 조회)
			
				//1) 커넥션 얻기
				// 드라이버 준비
				String driver = "oracle.jdbc.driver.OracleDriver";
				// url, id, pwd 
				String url = "jdbc:oracle:thin:@localhost:1521:xe";
				String dbId= "C##KH";
				String dbPwd = "KH";
				Class.forName(driver);				
				Connection conn = DriverManager.getConnection(url, dbId, dbPwd);
				//2) SQL 준비
				String sql = "SELECT NO, ID, NICK, ENROLL_DATE FROM MEMBER WHERE ID = ? AND PWD = ? AND QUIT_YN = 'N'";
				//3) SQL 담을 객체 준비
				PreparedStatement pstmt = conn.prepareStatement(sql);
				//sql 완성 시키기
				 pstmt.setString(1, userId);
				 pstmt.setString(2, userPwd);
				 //4) 실행 및 결과 저장
				 ResultSet rs = pstmt.executeQuery();
				 //조회 결과 저장할 객체 생성
				 UserDto dto = null;
				 if(rs.next()) {
					 int no = rs.getInt("NO");
					 String id = rs.getString("ID");
					 String nick = rs.getString("NICK");
					 Timestamp enrollDate = rs.getTimestamp("ENROLL_DATE");
					 
					 System.out.println("no : " + no);
					 System.out.println("id : " + id);
					 System.out.println("nick : " + nick);
					 System.out.println("enrolldate : " + enrollDate); 
				 
					 dto = new UserDto();
					 dto.setNo(no);
					 dto.setId(id);
					 dto.setNick(nick);
					 dto.setEnrollDate(enrollDate);
					 dto.toString();
					 
				 
				 }
				 //5) 뒷정리(commit || 롤백, 자원반납)
				 
				 //4. 결과에 따른, 화면출력(view에 넘기기) => (redirect)
				 if(dto != null) {
					 System.out.println(dto.getNo()+"번 회원 로그인 성공");  
					 
					 HttpSession session = req.getSession();
					 session.setAttribute("loginUser", dto);
					 resp.sendRedirect("/webPrj14/index.jsp"); //새로운 요청이기 때문에 데이터값이 전달되지 않음.
					
					 //req.setAttribute("loginUser", dto);
					 //req.getRequestDispatcher("/index.jsp").forward(req, resp);
				 }else {
					 System.out.println(userId + " 계정으로 로그인 시도 했으나 실패");
					 req.getRequestDispatcher("/index.jsp").forward(req, resp);
				 }
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("로그인 실패");
		}
		
	}
}
