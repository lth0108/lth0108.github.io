package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DB {
	public static Connection dbConn() {
		String dbDriver = "oracle.jdbc.driver.OracleDriver";
		String dbUrl = "jdbc:oracle:thin:@localhost:1521:xe";
		String dbUser = "local_jsp_pj";
		String dbPwd = "1234";
		
		Connection conn = null;
		try {
			Class.forName(dbDriver);
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			conn = DriverManager.getConnection(dbUrl, dbUser, dbPwd);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	public static void dbConnClose(ResultSet rs, PreparedStatement pstmt, Connection conn) {
		try {
			if(rs != null) { rs.close(); }
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			if(pstmt != null) { pstmt.close(); }
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			if(conn != null) { conn.close(); }
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
