package com.kh.notice.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import static com.kh.common.JDBCTemplate.*;

import com.kh.common.JDBCTemplate;
import com.kh.notice.vo.NoticeVo;

public class NoticeDao {

	public ArrayList<NoticeVo> selectList(Connection conn) {
		ArrayList<NoticeVo> voList= new ArrayList<NoticeVo>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		//커넥션준비
		//SQL준비
		String sql = "SELECT N.NO, N.TITLE, N.CONTENT, N.CNT, TO_CHAR(N.ENROLL_DATE, 'YY/MM/DD HH:MI') AS ENROLL_DATE, N.STATUS , M.NAME AS WRITER FROM NOTICE N JOIN MEMBER M ON N.WRITER = M.NO WHERE N.STATUS = 'N' ORDER BY NO DESC";
		//SQL 객체에 담기 및 완성
		try {
			pstmt = conn.prepareStatement(sql);
			//SQL 실행
			rs = pstmt.executeQuery();
			//결과 반환 // ResultSet -> 자바객체(NoticeVo) 
			while(rs.next()) {
				//결과 리턴
				String no = rs.getString("NO");
				String title = rs.getString("TITLE");
				String content = rs.getString("CONTENT");
				String writer = rs.getString("WRITER");
				String cnt = rs.getString("CNT");
				String enrollDate = rs.getString("ENROLL_DATE");
				String status = rs.getString("STATUS");
				
				NoticeVo vo = new NoticeVo();
				vo.setNo(no);
				vo.setTitle(title);
				vo.setContent(content);
				vo.setWriter(writer);
				vo.setCnt(cnt);
				vo.setEnrollDate(enrollDate);
				vo.setStatus(status);
				//리스트에 객체 담기
				voList.add(vo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
			close(rs);
		}
		return voList;
		}

	//공지사항 작성
	public int insertNotice(Connection conn, NoticeVo vo) {
		PreparedStatement pstmt = null;
		int result = 0;
		//SQL 준비
		String sql = "INSERT INTO NOTICE ( NO, TITLE, CONTENT, WRITER ) VALUES ( SEQ_NOTICE_NO.NEXTVAL, ? , ? , ? )";
		try {
			pstmt = conn.prepareStatement(sql);
			//SQL 객체에 담기 및 물음표 채우기
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getContent());
			pstmt.setString(3, vo.getNo());
			
			//SQL 실행
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		//실행결과 리턴
		return result;
	}
	//공지사항 조회수 증가
	public int increaseNotice(Connection conn, String num) {
		//conn 준비
				//sql 준비
		String sql = "UPDATE NOTICE SET CNT = CNT+1 WHERE NO = ? AND STATUS = 'N'";
		PreparedStatement pstmt = null;
		int result = 0;

		try {
			//sql을 객체에 담기 및 물음표 채우기
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, num);
			//sql 실행 및 결과 저장
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		
		} finally {
			close(pstmt);
		}
		
		//결과 반환
		return result;
	}

	//공지사항 조회
	public NoticeVo selectOne(Connection conn, String num) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		NoticeVo vo = null;
		String sql = "SELECT N.NO , N.TITLE , N.CONTENT , M.NAME AS WRITER , N.CNT , N.ENROLL_DATE , N.STATUS FROM NOTICE N JOIN MEMBER M ON N.WRITER = M.NO WHERE N.STATUS = 'N' AND N.NO = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, num);
			rs = pstmt.executeQuery();
			
			//rs -> obj
			if(rs.next()) {
				String no = rs.getString("NO");
				String title = rs.getString("TITLE");
				String content = rs.getString("CONTENT");
				String writer = rs.getString("WRITER");
				String cnt = rs.getString("CNT");
				String enrollDate = rs.getString("ENROLL_DATE");
				String status = rs.getString("STATUS");
				
				vo = new NoticeVo();
				vo.setNo(no);
				vo.setTitle(title);
				vo.setContent(content);
				vo.setWriter(writer);
				vo.setCnt(cnt);
				vo.setEnrollDate(enrollDate);	
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return vo;
	}

	//공지사항 삭제
	public int delete(Connection conn, String num) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql ="UPDATE NOTICE SET STATUS = 'Y' WHERE NO = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, num);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
	//공지사항 수정
	public int edit(Connection conn, NoticeVo vo) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = "UPDATE NOTICE SET TITLE = ? , CONTENT = ? WHERE NO = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getContent());
			pstmt.setString(3, vo.getNo());
			
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			
		} finally {
			close(pstmt);
		}
		
		
		
		
		
		
		return result;
	}
}
