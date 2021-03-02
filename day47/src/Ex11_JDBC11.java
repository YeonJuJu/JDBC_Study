import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

// DQL : Data Query Language : select

public class Ex11_JDBC11 {
  String url = "jdbc:oracle:thin:@localhost:1521:xe";
  String  id = "tjoeun";
  String  pw = "1111";
  String driver = "oracle.jdbc.driver.OracleDriver";
  
  Connection conn = null;
  Statement  stmt = null;
  ResultSet    rs = null;
  BufferedReader br = null;
  
  String sql = "";

  public Ex11_JDBC11() { }
  
  // DB 접속하기
  public void dbConn() {
  	try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, id, pw);
			System.out.println("DB Connected !!");
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
  } // dbConn
  
  public void executeDQL() {
  	br = new BufferedReader(new InputStreamReader(System.in));
  	System.out.println("select 문을 입력하세요");
  	
  	try {
			stmt = conn.createStatement();
			if((sql = br.readLine()) != null) {
				rs = stmt.executeQuery(sql);
				
				while(rs.next()) {
					int mno      = rs.getInt("mno");
					String name  = rs.getString("name");
					String phone = rs.getString("phone");
					String addr  = rs.getString("addr");
					System.out.println(mno+" -- " +name+" -- " +phone+" -- " +addr);
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			dbClose(br, rs, stmt, conn);
		}
  	
  }
  
  public void dbClose(BufferedReader br, ResultSet rs, Statement stmt, Connection conn) {
  	try {
			if(br != null) {br.close();}
			if(rs != null) {rs.close();}
			if(stmt != null) {stmt.close();}
			if(conn != null) {conn.close(); System.out.println("DB Closed.");}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
  	
  }
  public static void main(String[] args) {
  	Ex11_JDBC11 ex11 = new Ex11_JDBC11();
  	ex11.dbConn();
  	ex11.executeDQL();
  	
  }
  
}



