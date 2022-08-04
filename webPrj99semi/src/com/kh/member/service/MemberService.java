package com.kh.member.service;

import java.sql.Connection;
import static com.kh.common.JDBCTemplate.*;
import com.kh.member.repository.MemberDao;
import com.kh.member.vo.MemberVo;

public class MemberService {
	//회원가입 메소드
	
	public int join(MemberVo vo) {	
		System.out.println(vo);
		//id 유효성 검사
		if (vo.getId().length() < 4) {
			//회원가입 불가. 다음 진행 X
			return -1;
		} 
		//pwd 검사
		if (vo.getPwd().length() < 4) {
			//회원가입 불가. 다음 진행 X
			return -2;
		} 
		//pwd == pwd2 검사
		if (vo.getPwd().equals(vo.getPwd2()) == false) {
			//회원가입 불가. 다음 진행 X
			return -3;
		} 
		//아이디 중복 검사
		
		//vo를 DB에 insert
		//커넥션 가져오기
		Connection conn = null;
		int result = 0;
		try {
			conn = getConnection();
			result = new MemberDao().join(vo, conn);
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
	
	//로그인 메소드
	public MemberVo login(MemberVo vo) {
		//지역변수 미리 선언해주기
		Connection conn = null;
		MemberVo loginMember = null;
		try {
			//sql 실행을 위해 커넥션 준비
			conn = getConnection();
			//sql 샐행결과 리턴
			loginMember = new MemberDao().login(vo, conn);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return loginMember;
	}
	
	//회원정보 처리하기
	public MemberVo edit(MemberVo vo)  {
		//데이터 유효성 검사
		if (vo.getName().length() > 6) {
			System.out.println("한글은 3글자, 영어는 6글자 까지만 가능");
			return null;
		} 		
		//비지니스 로직 처리(자바 || SQL)
		Connection conn = null;
		int result = 0;
		MemberVo updateVo = null;
		
		try {
			conn = getConnection();
			result = new MemberDao().edit(vo, conn); 
			
			//결과에 따라 트랜잭션 처리(commit || rollback)
			if(result == 1 ) {
				commit(conn);	
				//다시하번 회원정보 조회 (회원번호)
				updateVo = selectOneByNo(vo.getNo());
			}else {
				rollback(conn);
			}
		} catch (Exception e) {
			e.printStackTrace();
			rollback(conn);
		}  finally {
			close(conn);
		
		}
		//실행결과 리턴(controller)
		return updateVo;
	}

	
	//회원정보 조회하기 (회원번호 통해서)
	private MemberVo selectOneByNo(int no) {
		Connection conn = null;
		MemberVo vo = null;
		try {
			conn = getConnection();
			vo = new MemberDao().seletOneByNo(no, conn);
			
		}catch(Exception e) {
			e.printStackTrace();
			
		}finally {
			close(conn);
		}
		return vo;
	}

	
	//회원 탈퇴하기
	public int quit(int no) {
		Connection conn = null;
		int result = 0;
		try {
			conn = getConnection();
			result = new MemberDao().quit(no, conn);
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			close(conn);
		}
		
		return result;
	}
	
	//비밀번호 변경하기
	public int changePwd(String memberId, String memberPwd, String memberPwdNew, String memberPwdNew2) {
		//비지니스 로직
		if(memberPwdNew.equals(memberPwdNew2) == false) {
			System.out.println("신규 비밀번호 불일치 !!!");
			return -1;
		}
		if(memberPwdNew.length() < 4) {
			System.out.println("비밀번호 4자리 미만 !!!");
			return -2;
		}
		Connection conn = null;
		int result = 0;
		
		try {
			conn = getConnection();
			result = new MemberDao().changePwd(memberId, memberPwd, memberPwdNew, conn);
			if(result ==1) {
				commit(conn);
			}else {
				rollback(conn);
			}
		} catch (Exception e) {
				rollback(conn);
		}finally {
			close(conn);
		}
		
		//DAO 호출
		
		
		
		return result;
	}
	
	
}
