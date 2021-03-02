import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Ex06_JDBC06 {
	String url = "jdbc:oracle:thin:@localhost:1521:xe";
	String id = "scott";
	String pw = "0000";
	String driver = "oracle.jdbc.driver.OracleDriver";
	
	Connection conn = null;
	Statement stmt = null;
	ResultSet rs = null;
	
	public Ex06_JDBC06(){
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, id, pw);
			System.out.println("DB 접속 성공 !");
			
			stmt = conn.createStatement();
			String sql = "insert into dept(deptno, dname, loc) values ( 60, 'SALES', 'BUSAN')";
			
			rs = stmt.executeQuery(sql);
			
			while(rs.next()){
				int deptno = rs.getInt(1);
				String dname = rs.getString(2);
				String loc = rs.getString(3);
				
				System.out.println(deptno + " " + dname + " " + loc);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) {
					rs.close();
				}
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
		new Ex06_JDBC06();
	}
}
