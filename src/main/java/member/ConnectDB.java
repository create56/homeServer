package member;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConnectDB {
	private static ConnectDB instance = new ConnectDB();
	
	public static ConnectDB getInstance() {
		return instance;
	}
	// DB연동
	public ConnectDB() {}
	
	String jdbcUrl = "jdbc:mariadb://localhost:3306/myapplicationdb?";
	String userId = "root";
	String userPw = "koreait";
	
	Connection conn = null;
	PreparedStatement pstmt = null;
	PreparedStatement pstmt2 = null;
	ResultSet rs = null;
	
	String sql="";
	String sql2 = "";
	String returns = "a";
	
	public String connectionDB(String id,String pwd) {
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection(jdbcUrl,userId,userPw);
			
			sql = "SELECT * FROM WHERE id ? AND pw ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, pwd);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				returns = "이미 존재하는 아이디입니다";
			} else {
				sql2 = "INSERT INTO user VALUES(?,?)";
				pstmt2 = conn.prepareStatement(sql2);
				pstmt2.setString(1, id);
				pstmt2.setString(2, pwd);
				pstmt2.executeUpdate();
				returns = "회원가입성공";
			}
		} catch(Exception e) {
			
		} finally {
			if(pstmt2 != null)try {pstmt2.close();} catch(SQLException e) {}
			if(pstmt != null)try {pstmt.close();} catch(SQLException e) {}
			if(conn != null)try {conn.close();} catch(SQLException e) {}
				
			}
			return returns;
		}
	}
