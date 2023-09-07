package bookmall.dao.test;

import bookmall.vo.OrdersBookVo;
import bookmall.vo.OrdersVo;

public class OrderDaoTest {
	public static void main(String[] args) {
		OrdersVo vo = new OrdersVo();
		vo.setName("홍길동");
		vo.setEmail("book@hanmial.net");
		vo.setAddress("광주광역시");
		vo.setOrdernumber("20230907");
		vo.setMember_no(1);
		insertTest(vo);
		
		OrdersBookVo vo1 = new OrdersBookVo();
		vo1.setOrders_no(1);
		vo1.setBook_no(1);
		vo1.setCount(3);
		insertTest2(vo1);
		
		
		findAll();

	}

	private static void insertTest2(OrdersBookVo vo1) {
		new OrdersDao().insertOrdersBook(vo1);
		
	}

	private static void insertTest(OrdersVo vo) {
		new OrdersDao().insertOrders(vo);
		
	}

	
	

}
