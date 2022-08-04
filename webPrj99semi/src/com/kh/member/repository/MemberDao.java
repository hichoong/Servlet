package com.kh.member.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import static com.kh.common.JDBCTemplate.*;
import com.kh.member.vo.MemberVo;

public class MemberDao {
	
	
	
	
	//회원가입
	public int join(MemberVo vo, Connection conn) throws Exception{
		//insert
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			//sql 준비
			String sql = "INSERT INTO MEMBER(NO, ID, PWD, NAME, PHONE, EMAIL, ADDR, INTEREST) VALUES(SEQ_MEMBER_NO.NEXTVAL, ?, ?, ?, ?, ?, ?, ?)";
			//sql 객체에 담고, sql 완성하기
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getId() );
			pstmt.setString(2, vo.getPwd());
			pstmt.setString(3, vo.getName());
			pstmt.setString(4, vo.getPhone());
			pstmt.setString(5, vo.getEmail());
			pstmt.setString(6, vo.getAddr());
			pstmt.setString(7, vo.getInterest());
			
			//sql 실행 및 실행결과 받기
			result = pstmt.executeUpdate(); 
			
		} catch (Exception e) {
			throw e;
		} finally {
			//다 쓴 자원 정리
			close(pstmt);
		}
		
		return result;
	}

	//로그인 처리
	public MemberVo login(MemberVo vo, Connection conn) throws Exception {
		//지역변수 선언 미리 해주기
		MemberVo loginMember =null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		//sql준비
		String sql = "SELECT * FROM MEMBER WHERE ID = ? AND PWD = ? AND STATUS = 'N'";
		try {
			//sql 객체에 담고, 물음표 채우기
			pstmt =  conn.prepareStatement(sql);
			pstmt.setString(1, vo.getId());
			pstmt.setString(2, vo.getPwd());
			//sql 실행 및 결과저장
			rs = pstmt.executeQuery();
			//rs에서 데이터 꺼내서 객체로 만들기
			
			if(rs.next()) {
				int no = rs.getInt("NO");
				String id = rs.getString("ID");
				String name = rs.getString("NAME");
				String phone = rs.getString("PHONE");
				String email = rs.getString("EMAIL");
				String addr = rs.getString("ADDR");
				String interest = rs.getString("INTEREST");
				Timestamp enrollDate = rs.getTimestamp("ENROLL_DATE");
				Timestamp modifyDate = rs.getTimestamp("MODIFY_DATE");
				
				loginMember = new MemberVo();
				loginMember.setNo(no);
				loginMember.setId(id);
				loginMember.setName(name);
				loginMember.setPhone(phone);
				loginMember.setEmail(email);
				loginMember.setAddr(addr);
				loginMember.setInterest(interest);
				loginMember.setEnrollDate(enrollDate);
				loginMember.setModifyDate(modifyDate);
			}		
		}
		//pstmt와 rs 자원 반납
		finally {
			close(pstmt);
			close(rs);
		}
		
		
		//만들어진 객체 리턴
		return loginMember;
	}
	
	//회원정보 처리
	public int edit(MemberVo vo, Connection conn) throws Exception  {
		//커넥션준비 
		PreparedStatement pstmt = null;
		//SQL 준비
		String sql = "UPDATE MEMBER SET NAME = ?, EMAIL = ?, PHONE = ?, ADDR = ?, MODIFY_DATE = SYSDATE, INTEREST = ? WHERE NO = ?";
		//SQL 객체에 담고, 완성하기
		int result = 0; 
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getEmail());
			pstmt.setString(3, vo.getPhone());
			pstmt.setString(4, vo.getAddr());
			pstmt.setString(5, vo.getInterest());
			pstmt.setInt(6, vo.getNo());
			//SQL 실행 및 결과 저장
			result = pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
			conn.rollback();
			throw e;
		}finally {
			close(pstmt);
		}
		//SQL 실행결과 리턴
		return result;
	}

	//회원정보 조회 (회원번호)
	public MemberVo seletOneByNo(int num, Connection conn) {
		//커넥션 준비
		
		//SQL 준비
		String sql = "SELECT * FROM MEMBER WHERE NO = ? AND STATUS = 'N'";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MemberVo vo = null;
		try {
			//SQL 객체에 담기 및 쿼리 완성
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			
			//SQL 실행 및 결과저장
			rs = pstmt.executeQuery();
			
			//ResultSet -> 자바객체(MeberVo)
			if(rs.next()) {
				int no = rs.getInt("NO");
				String id = rs.getString("ID");
				String name = rs.getString("NAME");
				String phone = rs.getString("PHONE");
				String email = rs.getString("EMAIL");
				String addr = rs.getString("ADDR");
				String interest = rs.getString("INTEREST");
				Timestamp enrollDate = rs.getTimestamp("ENROLL_DATE");
				Timestamp modifyDate = rs.getTimestamp("MODIFY_DATE");
				
				vo = new MemberVo();
				vo.setNo(no);
				vo.setId(id);
				vo.setName(name);
				vo.setPhone(phone);
				vo.setEmail(email);
				vo.setAddr(addr);
				vo.setInterest(interest);
				vo.setEnrollDate(enrollDate);
				vo.setModifyDate(modifyDate);
				
				
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
			close(rs);
			
		}
		return vo;
	}
	
	
	//회원탈퇴
	public int quit(int no, Connection conn) {
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;		
		MemberVo quitMember = null;
		int result = 0;
		String sql = "UPDATE MEMBER SET STATUS = 'Y', MODIFY_DATE = SYSDATE WHERE NO = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			result = pstmt.executeUpdate();
			if(result == 1 ) {
				System.out.println("회원탈퇴 성공");
			}else {
				System.out.println("회원탈퇴 실패");
			} 
				
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		
		return result;
	}

	public int changePwd(String memberId, String memberPwd, String memberPwdNew, Connection conn) throws Exception {
		//커넥션준비(파라미터로 받아옴)
		
		//sql준비
		String sql = "UPDATE MEMBER SET PWD = ? WHERE ID = ? AND PWD = ?";
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			//sql 객체에 담기 및 sql 완성
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberPwdNew);
			pstmt.setString(2, memberId);
			pstmt.setString(3, memberPwd);
			
			//sql 실행 및 결과 저장
			result = pstmt.executeUpdate();
			//실행결과 리턴
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}finally {
			close(pstmt);
		}
		
		return result;
	}
	
	
	
}
