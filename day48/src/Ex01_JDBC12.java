import java.sql.Statement;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Ex01_JDBC12 {
	String url = "jdbc:oracle:thin:@localhost:1521:xe";
	String id = "scott";
	String pw = "0000";
	String driver = "oracle.jdbc.driver.OracleDriver";
	
	Connection conn = null;
	Statement stmt = null;
	ResultSet rs = null;
	BufferedReader br = null;
	
	String sql = "";
	
	public Ex01_JDBC12() {
		try {
			Class.forName(driver);
			System.out.println("1.JDBC Driver Loaded.");
			
			conn = DriverManager.getConnection(url, id, pw);
			System.out.println("2.DB Connected");
			
			br = new BufferedReader(new InputStreamReader(System.in));
			
			System.out.print("sql문을 입력하세요 >> ");
			sql = br.readLine();
			
			stmt = conn.createStatement();
			System.out.println("3.Statement 객체  생성");
			
			//select : executeQuery();
			//insert, update, delete (DML) : executeUpdate()
			//execute() : sql문 실행한 후 ResultSet을 생성하면  true 반환 아니면 false 반환
 			
			boolean result = stmt.execute(sql);
			System.out.println("4.sql문을 DBMS에 전송");
			int count = 0;
			
			//select 문을 사용한 경우 
			//result -> true 인 경우 ResultSet 객체 생성됨
			if(result) {
				rs = stmt.getResultSet();
				System.out.println("5. select 문의 결과를 저장한 ResultSet 객체 얻어오기");
			
				System.out.println("6. ResultSet에 저장된 record들을 꺼내옴");
				while(rs.next()) {
					int empno = rs.getInt(1);
					String ename = rs.getString(2);
					String job = rs.getString(3);
					int mgr = rs.getInt(4);
					String hiredate = rs.getString(5);
					int sal = rs.getInt(6);
					int comm = rs.getInt(7);
					int deptno = rs.getInt(8);
					
					System.out.println(empno + " == " + ename + " == " + job + " == " + mgr + " == " + hiredate + " == " + sal + " == " + comm + " == " + deptno);
				}
			}
			else { // DML(insert, update, delete 인 경우)
				count = stmt.getUpdateCount(); // 수정된 객체의 개수를 얻어옴
				if(count > 0)
					System.out.println(count + "개의 record가 수정되었습니다.");
				else
					System.out.println("record 수정 실패");
			}
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			dbClose(br, rs, stmt, conn);	
		}
	}
	
	public void dbClose(BufferedReader br, ResultSet rs, Statement stmt, Connection conn) {
		try {
			if(br != null) { br.close();}
			if(rs != null) { rs.close();}
			if(stmt != null) { stmt.close();}
			if(conn != null) { conn.close(); System.out.println("DB disconnected");}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		new Ex01_JDBC12();
	}
}
