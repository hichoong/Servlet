package com.kh.member.service;

import org.apache.ibatis.session.SqlSession;

import static com.kh.common.SqlTemplate.*;

import com.kh.member.dao.MemberDao;
import com.kh.member.vo.MemberVo;

public class MemberService {

	private MemberDao dao = new MemberDao();
	
	/**
	 * 회원가입
	 */
	public int insertMember(MemberVo vo) {
		//커넥션 준비
		SqlSession ss = getSqlSession();
		//dao 호출
		int result = dao.insertMember(ss, vo);
		//트랜잭션 처리
		if(result == 1 ) {
			ss.commit();
		}
		//결과 return
		return result;
	}
	/**
	 * 로그인
	 */
	public MemberVo login(MemberVo vo) {

		//커넥션 준비
		SqlSession ss = getSqlSession();
		//dao  호출
		MemberVo loginMember = dao.login(ss, vo);
		//결과 반환
		return loginMember;
	}

}
