import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Ex05_JDBC05 {
	String url = "jdbc:oracle:thin:@localhost:1521:xe";
	String id = "scott";
	String pw = "0000";
	String driver = "oracle.jdbc.driver.OracleDriver";
	
	Connection conn = null;
	PreparedStatement pstmt = null;
	
	public Ex05_JDBC05() {
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, id, pw);
			System.out.println("DB Connected");
			
			String sql = "delete from dept where deptno = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, 60);
			
			int count = pstmt.executeUpdate();
			if(count > 0) {
				System.out.print(count + "개의 record가 삭제되었습니다.");
			}
			else {
				System.out.print("record 삭제 실패");
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
		new Ex05_JDBC05();
	}
}
