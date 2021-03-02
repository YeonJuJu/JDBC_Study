import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Ex07_JDBC07 {
	String url = "jdbc:oracle:thin:@localhost:1521:xe";
	String id = "scott";
	String pw = "0000";
	String driver = "oracle.jdbc.driver.OracleDriver";
	
	Connection conn = null;
	PreparedStatement pstmt = null;
	
	public Ex07_JDBC07() {
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, id, pw);
			System.out.println("DB Connected");
			
			String sql = "insert into dept(deptno, dname, loc) values ( ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, 70);
			pstmt.setString(2, "RESEARCH");
			pstmt.setString(3, "SOKCHO");
			
			int count = pstmt.executeUpdate();
			if(count > 0) {
				System.out.print(count + "개의 record가 삽입되었습니다.");
			}
			else {
				System.out.print("record 삽입 실패");
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
		new Ex07_JDBC07();
	}
}
