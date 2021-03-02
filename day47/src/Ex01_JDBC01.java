import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Ex01_JDBC01 {
	//JDBC Java DataBase Connectivity
	String url = "jdbc:oracle:thin:@localhost:1521:xe";
	String id = "scott";
	String pw = "0000";
	
	//Connection 객체 : DB와의 연결을 돕는 객체
	Connection conn = null;
	//PreparedStatement 객체 : 상태정보객체
	//Query문 (sql문)을 DB에 전달함
	PreparedStatement pstmt = null;
	//ResultSet 컬렉션 : 
	//Query문 (sql문) 결과를 받아와 저장하는 객체
	ResultSet rs = null;
	
	// 생성자
	public Ex01_JDBC01() {	
		
		try {
			// 1) 드라이버(Oracle JDBC(ojdbc6.jar)) 등록하기
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			// 2) Connection 객체 생성하기 - DB에 연결
			conn = DriverManager.getConnection(url, id, pw);
			System.out.println("DB 연결 성공 !!");
			
			// 3) Prepared(Statement) 객체 생성하기 - sql문을 db에 전달
			// PreparedStatement - sql 문을 가지고 객체가 생성됨 - 와일드카드 문자 (?)
			String sql = "SELECT * FROM EMP WHERE DEPTNO = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, 30); // 첫번째 ? 에 30을 할당함
			// 1) sql문을  DBMS에 전달함
			// 2) select 문의 결과를 받음 
			// 3) ResultSet 객체를 생성해 받아온 결과를 저장함 
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int empno = rs.getInt(1);
				String ename = rs.getString(2);
				String job = rs.getString(3);
				int mgr = rs.getInt(4);
				int sal = rs.getInt(6);
				int deptno = rs.getInt(8);
				
				System.out.println(empno + " " + ename + " " + job + " " + mgr + " " + sal + " " + deptno);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			try {
				if(rs != null) {
					rs.close();
				}
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
		new Ex01_JDBC01();
	}
}


