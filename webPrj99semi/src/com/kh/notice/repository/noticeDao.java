package com.kh.notice.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import static com.kh.common.JDBCTemplate.*;
import com.kh.notice.vo.NoticeVo;

public class noticeDao {

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
	
}
