package com.jinw0909.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.protocol.Resultset;

public class MySqlService {
	private String url;
	private String id;
	private String password;
	private static MySqlService mysqlService = null;
	
	// 접속 관리 객체
	private Connection conn;
	private Statement statement;
	
	// 싱글턴
	public static MySqlService getInstance() {
		if (mysqlService == null) {
			mysqlService = new MySqlService();
		}
		return mysqlService;
	}
	
	
	public MySqlService() {
		this.url = "jdbc:mysql://localhost:3306/JAVA";
		this.id = "root";
		this.password = "worldcup1989";
	}
	
	public void connection() {
		try { DriverManager.registerDriver(new com.mysql.jdbc.Driver());
		
			// mysql 접속 url
			// mysql 접 id
			// mysql 접속 비밀번호
			this.conn = DriverManager.getConnection(
					this.url,
					this.id,
					this.password
					);
			
			// 쿼리를 수행하기 위한 객체
			this.statement = conn.createStatement();
			
		
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		
	//쿼리 수행 메소드
		public int update(String query) throws SQLException {
			return this.statement.executeUpdate(query);
		}
		
		public Resultset select(String query) throws SQLException {
			return (Resultset) this.statement.executeQuery(query);
		}
		
		public void disconnect() throws SQLException {
			this.statement.close();
			this.conn.close();
		}
		
}
