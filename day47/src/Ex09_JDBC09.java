import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Ex09_JDBC09 {
	String url = "jdbc:oracle:thin:@localhost:1521:xe";
	String id = "tjoeun";
	String pw = "0000";
	String driver = "oracle.jdbc.driver.OracleDriver";
	
	public Ex09_JDBC09() {}
	
	public static void main(String[] args) {
		Ex09_JDBC09 ex = new Ex09_JDBC09();
//		ex.select();
		
//		ex.insert();
//		ex.select();
		
//		ex.delete();
//		ex.select();
		
//		ex.update();
//		ex.select();
	}
	
	public void select(){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, id, pw);
			System.out.println("DB Connected");
			
			String sql = "select * from member";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				String id = rs.getString(1);
				String author = rs.getString(2);
				String title = rs.getString(3);
				String content = rs.getString(4);
				
				System.out.println(id + " == " + author+ " == " + title+ " == " +content);
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
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
	
	public void insert() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, id, pw);
			System.out.println("DB Connected");
			
			String sql = "insert into member values(?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, "tjoeun4");
			pstmt.setString(2, "홍길동");
			pstmt.setString(3, "홍길동전");
			pstmt.setString(4, "홍길동전");
			
			int count = pstmt.executeUpdate();
			if(count > 0) {
				System.out.print(count + "개의 record가 삽입되었습니다.");
			}
			else {
				System.out.print("record 삽입 실패");
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
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
	
	public void delete() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, id, pw);
			System.out.println("DB Connected");
			
			String sql = "delete from member where id = ?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, "tjoeun4");
			
			int count = pstmt.executeUpdate();
			if(count > 0) {
				System.out.print(count + "개의 record가 삭제되었습니다.");
			}
			else {
				System.out.print("record 삭제 실패");
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
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
	
	public void update() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, id, pw);
			System.out.println("DB Connected");
			
			String sql = "update member set author=?, title=?, content=? where id=?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, "홍길동동");
			pstmt.setString(2, "홍길동전");
			pstmt.setString(3, "홍길동전");
			pstmt.setString(4, "tjoeun4");
			
			
			int count = pstmt.executeUpdate();
			if(count > 0 ) {
				System.out.println(count + " 개의  record 를 수정했습니다.");
			}
			else {
				System.out.println("record 실패");
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
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
	
}
	
