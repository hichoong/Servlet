package com.kh.member.dao;

import org.apache.ibatis.session.SqlSession;

import com.kh.member.vo.MemberVo;

public class MemberDao {
	/**
	 * 회원가입
	 */
	public int insertMember(SqlSession ss, MemberVo vo) {
		//sql 준비
		 //sql을 객체에 담기
		 //sql 완성
		 //sql 실행 및 결과 저장
		 //결과 리턴
		 //ss.insert("네임스페이스명.인설트아이디", 담을객체);
		
		return ss.insert("memberMapper.join", vo);
	}
	/**
	 * 로그인
	 */
	public MemberVo login(SqlSession ss, MemberVo vo) {
		
		return ss.selectOne("memberMapper.login", vo); //아무거나 하나의 select를 원할때 사용
	}
}
