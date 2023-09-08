package bookmall.main;

import java.util.List;

import bookmall.dao.test.BookDao;
import bookmall.dao.test.CartDao;
import bookmall.dao.test.CategoryDao;
import bookmall.dao.test.MemberDao;
import bookmall.dao.test.OrdersDao;
import bookmall.vo.BookVo;
import bookmall.vo.CartVo;
import bookmall.vo.CategoryVo;
import bookmall.vo.MemberVo;
import bookmall.vo.OrdersBookVo;
import bookmall.vo.OrdersVo;

public class BookMall {

	public static void main(String[] args) {
		
		MemberVo vo1 = new MemberVo();
		vo1.setName("둘리");
		vo1.setPhone("9292-1294-1123");
		vo1.setEmail("dooly3@gmail.com");
		vo1.setPw("3252");
		new MemberDao().insert(vo1);
		
		MemberVo vo2 = new MemberVo();
		vo2.setName("지웅");
		vo2.setPhone("010-1294-1123");
		vo2.setEmail("rju1203@gmail.com");
		vo2.setPw("1234");
		new MemberDao().insert(vo2);
		
		
		System.out.println("##회원리스트");
		List<MemberVo> list = new MemberDao().findAll();
		for(MemberVo member : list) {
			System.out.println("이름 : " + member.getName() + ", 전화번호 : " + member.getPhone() + ", 이메일 : " + member.getEmail() + ", 비밀번호 : " + member.getPw());
		}
		
		CategoryVo vo3 = new CategoryVo();
		vo3.setName("드라마");
		new CategoryDao().insert(vo3);
		CategoryVo vo4 = new CategoryVo();
		vo4.setName("소설");
		new CategoryDao().insert(vo4);
		CategoryVo vo5 = new CategoryVo();
		vo5.setName("수필");
		new CategoryDao().insert(vo5);
		
		System.out.println("##카테고리");
		List<CategoryVo> categoryList = new CategoryDao().findAll();
		 for (CategoryVo category : categoryList) {
	            System.out.println("카테고리 : " + category.getName());
	        }
		
		 BookVo vo6 = new BookVo();
			vo6.setPrice(30000);
			vo6.setTitle("난중일기");
			vo6.setCategory_no(1L);
			new BookDao().insert(vo6);
		BookVo vo7 = new BookVo();
			vo7.setPrice(25000);
			vo7.setTitle("수학의 정석");
			vo7.setCategory_no(2L);
		new BookDao().insert(vo7);
		
		System.out.println("## 상품");
		List<BookVo> bookList = new BookDao().findAll();
		for (BookVo book : bookList) {
            System.out.println("제목 : " + book.getTitle() + ", 가격 : " + book.getPrice());
        }
		BookDao bookDao = new BookDao();
		CartVo vo8 = new CartVo();
		vo8.setMember_no(1L);
		vo8.setBook_no(1L);
		vo8.setCount(3);
		new CartDao().insert(vo8);
		
		CartVo vo9 = new CartVo();
		vo9.setMember_no(2L);
		vo9.setBook_no(1L);
		vo9.setCount(3);
		new CartDao().insert(vo9);
		System.out.println("## 카트");
		List<CartVo> cartList = new CartDao().findAll();
		for (CartVo cart : cartList
		        ) {
		            System.out.println("도서 제목 : " + bookDao.findBookTitle(cart.getBook_no()) + ", 수량 : " + cart.getCount() + ", 가격 : " + cart.getCount() * bookDao.findBookPrice(cart.getBook_no()));
		        }
		
		OrdersDao orderDao = new OrdersDao();
        orderDao.insertOrders(2L, "광주광역시", "20230905-02");

        // 주문도서
        // 도서번호, 도서제목, 수량
        // 실제 테이블 : order_no, book_no, title, count, price
        orderDao.insertOrdersBook(1L, 1L, 2);
        orderDao.insertOrdersBook(1L, 2L, 1);



        System.out.println();
        System.out.println("## 주문");
        List<OrdersVo> ordersList = orderDao.showOrders();
        for (OrdersVo orders : ordersList) {
            System.out.println("주문번호 : " + orders.getOrdernumber() + ", 주문자 이름 : " + orders.getName() + ", 주문자 이메일 : " + orders.getEmail() + ", 결제 금액 : " + orders.getTotal_price() + ", 배송지 : " + orders.getAddress());
        }

        System.out.println();
        System.out.println("## 주문도서");
        List<OrdersBookVo> ordersBooks = orderDao.showOrdersBook();
        for (OrdersBookVo ordersBook : ordersBooks) {
            System.out.println("도서 번호 : " + ordersBook.getBook_no() + ", 도서 제목 : " + ordersBook.getTitle() + ", 수량 : " + ordersBook.getCount());
        }
	}

}
