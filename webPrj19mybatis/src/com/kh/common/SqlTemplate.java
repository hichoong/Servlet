package com.kh.common;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class SqlTemplate {
	
	/**
	 * SqlSessionFactoryBuilder -> SqlSessionFactory -> SqlSession 객체 생성
	 * 
	 * 
	 */
	public static SqlSession getSqlSession() {
		SqlSession ss = null;
		try {
			String resource = "/mybatis-config.xml";
			InputStream inputStream = Resources.getResourceAsStream(resource);
			SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
			ss = sqlSessionFactory.openSession(false);
			//openSession(boolean flag) : 자동커밋 여부 || 기본값은 false이긴 하지만 명시적으로 작성
		} catch (IOException e) {
			e.printStackTrace();
		}
		return ss;
	}
}

