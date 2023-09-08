package bookmall.dao.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bookmall.vo.MemberVo;
import bookmall.vo.OrdersBookVo;
import bookmall.vo.OrdersVo;

public class OrdersDao {
    public void insertOrders(Long member_no, String address, String ordernumber) {
        MemberVo member = new MemberDao().findNameAndEmail(member_no);
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
        	conn = getConnection();

            String sql = "INSERT INTO orders (name, email, total_price,address,member_no,ordernumber)\n" +
                    "VALUES (?,?,?,?,?,?);";
            pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, member.getName());
            pstmt.setString(2, member.getEmail());
            pstmt.setInt(3, 0);
            pstmt.setString(4, address);
            pstmt.setLong(5, member_no);
            pstmt.setString(6, ordernumber);
            pstmt.executeQuery();

        }catch (SQLException e) {
            System.out.println("error:" + e);
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void insertOrdersBook(Long order_no, Long book_no, int count) {
        BookDao bookDao = new BookDao();
        int price = bookDao.findBookPrice(book_no);
        String title = bookDao.findBookTitle(book_no);

        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
        	conn = getConnection();

            String sql = "INSERT INTO orders_book (title, count, price,orders_no,book_no)\n" +
                    "VALUES (?,?,?,?,?);";
            pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, title);
            pstmt.setInt(2, count);
            pstmt.setInt(3, price * count);
            pstmt.setLong(4, order_no);
            pstmt.setLong(5, book_no);
            pstmt.executeQuery();

        }catch (SQLException e) {
            System.out.println("error:" + e);
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public List<OrdersVo> showOrders() {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<OrdersVo> ordersList = new ArrayList<>();
        try {
        	conn = getConnection();
            String sql = "select * from orders";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                Long no = rs.getLong(1);
                int total_price = sumOrdersPrice(no);
                ordersList.add(new OrdersVo(rs.getString(2), rs.getString(3), total_price, rs.getString(5), rs.getString(7)));
            }

        } catch (SQLException e) {
            System.out.println("error:" + e);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return ordersList;
    }

  
    public int sumOrdersPrice(Long no) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int total_price = 0;
        try {
        	conn = getConnection();
            String sql = "select sum(price) from orders_book where orders_no = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, no);

            rs = pstmt.executeQuery();
            while (rs.next()) {
                total_price = rs.getInt(1);
                sumUpdateOrders(no, total_price);
            }

        } catch (SQLException e) {
            System.out.println("error:" + e);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return total_price;
    }

    public void sumUpdateOrders(Long no, int total_price) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
        	conn = getConnection();
            String sql = "update orders set total_price = ? where no = ?;";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, total_price);
            pstmt.setLong(2, no);
            pstmt.executeQuery();
        } catch (SQLException e) {
            System.out.println("error:" + e);
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public List<OrdersBookVo> showOrdersBook() {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<OrdersBookVo> ordersBookList = new ArrayList<>();
        try {
        	conn = getConnection();
            String sql = "select * from orders_book";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                ordersBookList.add(new OrdersBookVo(rs.getLong(6),rs.getString(2),rs.getInt(3)));
            }

        }catch (SQLException e) {
            System.out.println("error:" + e);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return ordersBookList;
    }


private Connection getConnection() throws SQLException {
	Connection conn = null;
	
	try {
		Class.forName("org.mariadb.jdbc.Driver");
		String url = "jdbc:mariadb://192.168.64.2:3307/bookmall?charset=utf8";
		conn = DriverManager.getConnection(url, "bookmall", "bookmall");
	} catch (ClassNotFoundException e) {
		System.out.println("드라이버 로딩 실패:" + e);
	} 
	
	return conn;
}
}