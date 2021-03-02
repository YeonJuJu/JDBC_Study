import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Ex03_JDBC03 {
	String url = "jdbc:oracle:thin:@localhost:1521:xe";
	String id = "scott";
	String pw = "0000";
	String driver = "oracle.jdbc.driver.OracleDriver";

	// insert (DML) : ResultSet 이 필요 없음
	Connection conn = null;
	Statement stmt = null;
	
	public Ex03_JDBC03() {
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, id, pw);
			System.out.print("DB 접속 성공 ~ ");
			
			String sql = "insert into dept values(60, 'HR', 'SEOUL')";
			stmt = conn.createStatement();
			stmt.executeUpdate(sql);
			System.out.println("insert 성공 ");
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				if(stmt != null) {
					stmt.close();
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
		new Ex03_JDBC03();
	}
}
