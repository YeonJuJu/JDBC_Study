import java.io.BufferedReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Ex02_JDBC13 {
	
	public static void main(String[] args) {
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String id = "TJOEUN";
		String pw = "0000";
		String driver = "oracle.jdbc.driver.OracleDriver";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		String sql = "";
		
		try {
			Class.forName(driver);
			System.out.println("1. JDBC Driver Loaded.");
			
			conn = DriverManager.getConnection(url, id, pw);
			System.out.println("2. DB Connected.");
			
			System.out.println("3. SQL 문 작성");
			sql = "insert into memberlist values(?, ?, ?, ?)";
			
//			sql = "insert into memberlist values(3, '이순신', '010-1111-1111', '서울시 마포구 신수동')";
			
//			System.out.println("4. Statement 객체 생성");
//			stmt = conn.createStatement();
			
			System.out.println("4. PreparedStatement 객체 생성 (?) 사용");
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(args[0]));
			pstmt.setString(2, args[1]);
			pstmt.setString(3, args[2]);
			pstmt.setString(4, args[3]);
			
			System.out.println("5. SQL문 DBMS에게 전달");
//			System.out.println("6. ResultSet 객체 생성");
//			System.out.println("5. DBMS에서 받아온 Record 저장");
//			int count = stmt.executeUpdate(sql);
			
			int count = pstmt.executeUpdate();
			
			if(count > 0) {
				System.out.println(count +" 개의 record가 수정되었습니다.");
			}
			else {
				System.out.println("record 수정 실패");
			}
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			dbClose(null, stmt, conn);
		}
	}
	
	public static void dbClose(ResultSet rs, Statement stmt, Connection conn) {
		try {
			if(rs != null) { rs.close();}
			if(stmt != null) { stmt.close();}
			if(conn != null) { conn.close(); System.out.println("DB disconnected");}
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
