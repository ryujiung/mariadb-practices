package bookshop.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bookshop.vo.BookVo;
import hr.dao.vo.EmployeesVo;

public class BookDao {


	public boolean updateRent(BookVo vo) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		boolean result = false;
		
		try {
			//1. JDBC Driver Class 로딩
			Class.forName("org.mariadb.jdbc.Driver");
			
			//2. 연결하기
			String url = "jdbc:mariadb://192.168.64.2:3307/webdb?charset=utf8";
			conn = DriverManager.getConnection(url, "webdb", "webdb");

			//3. SQL 준비
			String sql =
					" update book" +
					"   set rent=?" + 
					"  where no =?" +
					"  and rent = ?";
			pstmt = conn.prepareStatement(sql);
			
			//4. binding
			pstmt.setString(1, vo.getRent());
			pstmt.setInt(2,vo.getNo());
			if(vo.getRent() == "Y") {
				pstmt.setString(3, "N");
			}else {
				pstmt.setString(3, "Y");
			}
			
			//5. SQL 실행
			result = pstmt.executeUpdate() == 1;
			
			//6. 결과 처리
//			if(vo.getRent() == "Y") {
//				pstmt.setString(3, "N");
//			}else {
//				pstmt.setString(3, "Y");
//			}
			
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패:" + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			try {
				// 6. 자원정리
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
		return result;

}

	public List<BookVo> findAll() {
		{
			//JDBC
			List<BookVo> result = new ArrayList<>();
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			try {
				//1. JDBC Driver Class 로딩
				Class.forName("org.mariadb.jdbc.Driver");
				
				//2. 연결하기
				String url = "jdbc:mariadb://192.168.64.2:3307/webdb?charset=utf8";
				conn = DriverManager.getConnection(url, "webdb", "webdb");
				
				//3. Statement 객체 생성
				String sql = "select b.no, b.title, b.rent, a.name"+
						 " from book b, author a"+
						" where b.author_no=a.no;";
				pstmt = conn.prepareStatement(sql);
				
				//4. 값 binding
				// None
				
				//5. SQL 실행
				rs =  pstmt.executeQuery();

				//6. 결과 처리
				while (rs.next()) {
					int no = rs.getInt(1);
					String title = rs.getString(2);
					String rent = rs.getString(3);
					String author = rs.getString(4);
					BookVo vo = new BookVo();
					vo.setNo(no);
					vo.setTitle(title);
					vo.setRent(rent);
					vo.setAuthor(author);
					
					result.add(vo);
				}
				
			} catch (ClassNotFoundException e) {
				System.out.println("드라이버 로딩 실패 : " + e);
			} catch(SQLException e) {
				System.out.println("error:" + e);
			} finally{
				try {
					//6. 자원 정리
					if(rs != null) {
						rs.close();
					}
					if(pstmt != null) {
						pstmt.close();
					}
					if (conn != null && !conn.isClosed())
					{
						conn.close();
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			
			}
			
			return result;
		}
	}

	}
