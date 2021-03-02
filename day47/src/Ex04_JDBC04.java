import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Ex04_JDBC04 {
	String url = "jdbc:oracle:thin:@localhost:1521:xe";
	String id = "scott";
	String pw = "0000";
	String driver = "oracle.jdbc.driver.OracleDriver";
	
	Connection conn = null;
	PreparedStatement pstmt = null;
	
	public Ex04_JDBC04() {
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, id, pw);
			System.out.println("DB 접속 성공 !!");
			
			
			String sql = "update dept set dname =?, loc=? where deptno=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "RESEARCH");
			pstmt.setString(2, "KWANGJOO");
			pstmt.setInt(3, 60);
			
			int count = pstmt.executeUpdate();
			
			if(count > 0 ) {
				System.out.println(count + " 개의  record 를 수정했습니다.");
			}
			else {
				System.out.println("record 실패");
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				if(pstmt != null) {
					pstmt.close();
				}
				if(conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	
	public static void main(String[] args) {
		new Ex04_JDBC04();
	}
}
