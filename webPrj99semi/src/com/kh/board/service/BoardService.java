package com.kh.board.service;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.kh.board.repository.BoardDao;
import com.kh.board.vo.BoardVo;
import com.kh.category.vo.CategoryVo;
import com.kh.common.JDBCTemplate;
import com.kh.common.PageVo;

import static com.kh.common.JDBCTemplate.*;

public class BoardService {
	//BoardDao 객체 생성을 위한 준비
	private final BoardDao dao = new BoardDao();
	/*
	 Board 테이블의 게시글 총 갯수 
	 */
	public int getCount() {
		Connection conn = null;
		int result =0;
		
		try {
			conn = getConnection();
			result = dao.getCount(conn); //select count 쿼리
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(conn);
		}
		return result;
	}
	
	/*
	 * 게시글 리스트 조회
	 * (현재 페이지에 보여질)
	 */
	public List<BoardVo> selectList(PageVo pageVo) {
		Connection conn = null;
		List<BoardVo> voList = null;
		//DAO 호출
		conn = getConnection();
		voList = dao.selectList(conn, pageVo);
		close(conn);
		return voList;
	}
	/*
	 * 카테고리 정보(리스트) 조회
	 */
	public List<CategoryVo> selectCategoryList() {
		Connection conn = getConnection();
		List<CategoryVo> list = dao.selectCategoryList(conn);
		close(conn);
		return list;
	}	
}
		
		
	
		
		
