package com.kh.notice.service;

import java.sql.Connection;
import java.util.ArrayList;

import static com.kh.common.JDBCTemplate.*;
import com.kh.notice.repository.NoticeDao;
import com.kh.notice.vo.NoticeVo;

public class NoticeService {
	
	private final NoticeDao dao = new NoticeDao();
	
	//공지사항 조회
	public ArrayList<NoticeVo> selectList() {
		Connection conn = null;
		ArrayList<NoticeVo> voList= new ArrayList<NoticeVo>();
		
		try {
			conn = getConnection();
			//DAO 호출
			voList = new NoticeDao().selectList(conn);			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(conn);
		}
		//결과리턴
		return voList;
	}
	
	//공지사항 작성
	public int insertNotice(NoticeVo vo) {
		Connection conn = null;
		int result = 0;
		try {
			conn = getConnection();			
			//DAO 호출 -> 결과 리턴
			result = new NoticeDao().insertNotice(conn, vo);
			if(result == 1 ) {
				commit(conn);
			}else {
				rollback(conn);
			}
		} catch (Exception e) {
			e.printStackTrace();
			rollback(conn);
		} finally {
			close(conn);
		}
		return result;
	}
	
	//조회수 증가
	public int increaseNotice(String num) {
		
		Connection conn = null;
		int result = 0;
		try {
			conn = getConnection();
			//DAO 호출
			result = new NoticeDao().increaseNotice(conn, num);
			
			if(result == 1 ) {
				commit(conn);
			}else {
				rollback(conn);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(conn);
		}
		return result;
	}
	//공지사항 조회
	public NoticeVo selectOne(String num) {
		
		Connection conn = null;
		NoticeVo vo = null;
		
		try {
			conn = getConnection();
			//DAO 호출
			vo = new NoticeDao().selectOne(conn, num);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			close(conn);
		}
		return vo;
	}
	//공지사항 삭제
	public int delete(String num) {
		Connection conn = null;
		int result = 0;
		try {
			conn = getConnection();
			//DAO 호출	
			result = new NoticeDao().delete(conn, num);
			if(result == 1) {
				commit(conn);
			}else {
				rollback(conn);
			}
		} catch (Exception e) {
			e.printStackTrace();
			rollback(conn);
		} finally {
			close(conn);
		}
		return result;
	}
	//공지사항 수정
	public int edit(NoticeVo vo) {
		//데이터 검사
		if(vo.getTitle().length() < 1) {
			return -1;
		}
		if(vo.getContent().length() < 1) {
			return -2;
		}
		//커넥션 가져오기
		Connection conn = null;
		int result = 0;
		conn = getConnection();
		try {
			//dao호출
			result = dao.edit(conn, vo);
			if(result == 1 ) {
				commit(conn);
			}else {
				rollback(conn);
			}
		} catch (Exception e) {
			e.printStackTrace();
			rollback(conn);
		} finally {
			close(conn);
		}
		//결과값 리턴
		return result;
	}
}
