package com.kh.board.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.kh.board.vo.BoardVo;
import com.kh.category.vo.CategoryVo;
import com.kh.common.PageVo;

import static com.kh.common.JDBCTemplate.*;

public class BoardDao {
	/*
	 * 게시글 총 갯수 조회
	 */
	public int getCount(Connection conn) {
		int count = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT COUNT(NO) AS COUNT FROM BOARD WHERE STATUS = 'N' AND TYPE = 1" ;
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			//실행결과 -> 자바 데이터
			if(rs.next()) {
				count = rs.getInt("COUNT");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}
		return count;
	}
	/*
	 * 게시글 목록 조회
	 * (현재 페이지에 해당하는)
	 */
	public List<BoardVo> selectList(Connection conn, PageVo pageVo) {
		//conn준비
		PreparedStatement pstmt = null;
		List<BoardVo> list = new ArrayList<BoardVo>();
		ResultSet rs = null;
		//sql 준비
		String sql = "SELECT * FROM ( SELECT ROWNUM RNUM, T.* FROM ( SELECT B.NO , B.TITLE , B.CONTENT , B.CNT , B.ENROLL_DATE , M.ID AS WRITER , C.CATEGORY_NAME FROM BOARD B JOIN MEMBER M ON B.WRITER = M.NO JOIN CATEGORY C  USING(CATEGORY_NO) WHERE TYPE = 1 AND B.STATUS = 'N' ORDER BY B.NO DESC ) T ) WHERE RNUM BETWEEN ? AND ?";
		//sql 을 객체에 담기 및 완성
		try {
			pstmt = conn.prepareStatement(sql);
			int start = (pageVo.getCurrentPage()-1) * pageVo.getBoardLimit() + 1;
			int end = start + pageVo.getBoardLimit() - 1;
			//sql 실행 및 결과 저장
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				//컬럼 값 뽑기
				String no = rs.getString("NO");
				String title = rs.getString("TITLE");
				String content = rs.getString("CONTENT");
				String cnt = rs.getString("CNT");
				String enrollDate = rs.getString("ENROLL_DATE");
				String writer = rs.getString("WRITER");
				String categoryName = rs.getString("CATEGORY_NAME");
				//컬럼 값 객체에 담기
				BoardVo vo = new BoardVo();
				vo.setNo(no);
				vo.setTitle(title);
				vo.setContent(content);
				vo.setCnt(cnt);
				vo.setEnrollDate(enrollDate);
				vo.setWriter(writer);
				vo.setCategory(categoryName);
				//배열에 담기
				list.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}
		return list;
	}
	/*
	 * 카테고리 정보(리스트) 조회
	 */
	public List<CategoryVo> selectCategoryList(Connection conn) {
		//커넥션 준비
		//sql 준비
		String sql = "SELECT CATEGORY_NO , CATEGORY_NAME FROM CATEGORY";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<CategoryVo> list = new ArrayList<CategoryVo>();
		try {
			//sql 을 객체에 담기 및 완성
			pstmt = conn.prepareStatement(sql);
			//sql 실행 및 결과 저장
			rs = pstmt.executeQuery();
			//rs -> java
			while(rs.next()) {
				list.add(new CategoryVo(rs.getString("CATEGORY_NO"), rs.getString("CATEGORY_NAME")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}
		//결과 리턴
		return list;
	}

}
