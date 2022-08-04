package com.kh.common;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class JDBCTemplate {
	
	public static Connection getConnection () throws Exception {
		
		//프로퍼티 객체에 LOAD 해오면 KEY 값으로 꺼낼 수 있음
		Properties prop =new Properties();
		String filePath = JDBCTemplate.class.getResource("/db/setup/data.properties").getPath();
		prop.load(new FileInputStream(filePath));
		 
		String driver = prop.getProperty("driver");
		String url = prop.getProperty("url");
		String id = prop.getProperty("id");
		String pwd = prop.getProperty("pwd");
		Class.forName(driver);				
		Connection conn = DriverManager.getConnection(url, id, pwd);
		//자동 저장 끄기
		conn.setAutoCommit(false);
		return conn;
	}
	//커밋
	public static void commit(Connection conn) {
		try {
			if(conn != null) conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	//롤백
	public static void rollback(Connection conn) {
		try {
			if(conn != null) conn.rollback();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//close 할 것들
	public static void close(Statement stmt) {
		try {
			if(stmt != null) stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public static void close(ResultSet rs) {
		try {
			if(rs != null) rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public static void close(Connection conn) {
		try {
			if(conn != null) conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}


