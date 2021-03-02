//sql 문 삽입할 때 세미콜론을 넣지 않게 주의 !!!!

import java.sql.Statement;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Ex10_JDBC10 {
	String url = "jdbc:oracle:thin:@localhost:1521:xe";
	String id = "tjoeun";
	String pw = "0000";
	String driver = "oracle.jdbc.driver.OracleDriver";
	
	Connection conn = null;
	Statement stmt = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	BufferedReader br = null;
	
	public Ex10_JDBC10() {}
	
	//DB 접속하기 
	public void dbConn(){
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, id, pw);
			System.out.println("DB Connected !");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void executeDQL(){
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, id, pw);
			System.out.println("DB Connected");
			
			br = new BufferedReader(new InputStreamReader(System.in));
			String sql = "";
			System.out.print("sql(DQL) 문을 입력하세요>>");
		
			sql = br.readLine();
	
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Integer mno = rs.getInt(1);
				String name = rs.getString(2);
				String phone = rs.getString(3);
				String addr = rs.getString(4);
				
				System.out.println(mno + " == " + name+ " == " + phone+ " == " +addr);
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			dbClose(br, rs, pstmt, conn);
		}
	}
	
	public void executeDML() {
		br = new BufferedReader(new InputStreamReader(System.in));
		String sql = "";
		System.out.print("sql(DML) 문을 입력하세요>>");
		
		try {
			stmt = conn.createStatement();
			
			if((sql = br.readLine()) != null) {
				int count = stmt.executeUpdate(sql);
				
				if(count > 0)
					System.out.println(count + "개의 record가 수정되었습니다.");
				else
					System.out.println("record 수정 실패");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			dbClose(br, stmt, conn);
		}
		
	}
	
	public void dbClose(BufferedReader br, Statement stmt, Connection conn) {
		try {
			if(br != null) {
				br.close();
			}
			if(stmt != null) {
				stmt.close();
			}
			if(conn != null) {
				conn.close();
				System.out.println("DB Disconnected");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void dbClose(BufferedReader br, ResultSet rs, PreparedStatement pstmt, Connection conn) {
		try {
			if(br != null) {
				br.close();
			}
			if(rs != null) {
				rs.close();
			}
			if(stmt != null) {
				stmt.close();
			}
			if(conn != null) {
				conn.close();
				System.out.println("DB Disconnected");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		Ex10_JDBC10 ex = new Ex10_JDBC10();
		
		ex.dbConn();

		ex.executeDQL();
	}
}
