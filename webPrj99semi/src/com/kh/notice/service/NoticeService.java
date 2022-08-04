package com.kh.notice.service;

import java.sql.Connection;
import java.util.ArrayList;

import static com.kh.common.JDBCTemplate.*;
import com.kh.notice.repository.noticeDao;
import com.kh.notice.vo.NoticeVo;

public class NoticeService {
	//공지사항 조회
	public ArrayList<NoticeVo> selectList() {
		Connection conn = null;
		ArrayList<NoticeVo> voList= new ArrayList<NoticeVo>();
		
		try {
			conn = getConnection();
			//DAO 호출
			voList = new noticeDao().selectList(conn);			
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
			result = new noticeDao().insertNotice(conn, vo);
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

}
