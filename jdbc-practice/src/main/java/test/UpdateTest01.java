package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UpdateTest01 {

	public static void main(String[] args) {
		boolean result = updateDepartment(2L, "기획팀");
		System.out.println(result? "성공":"실패");

	}

	private static boolean updateDepartment(long no, String name) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		boolean result = false;
		try {
			//1. JDBC Driver Class 로딩
			Class.forName("org.mariadb.jdbc.Driver");
			
			//2. 연결하기
			String url = "jdbc:mariadb://localhost:3306/webdb?charset=utf8";
			conn = DriverManager.getConnection(url, "root", "P@ssw0rD1");

			//3. Statement 객체 생성
			stmt = conn.createStatement();
			
			//4. SQL 실행
			String sql =
					" update dept" +
				    "    set name='" + name + "'" + 
					"  where no=" + no;
				
				int count = stmt.executeUpdate(sql);
			
			//5. 결과 처리
			result = count == 1;
			
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패:" + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			try {
				// 6. 자원정리
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
		
		return result;
	
	}

}
