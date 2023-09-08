package bookmall.dao.test;

import java.util.List;

import bookmall.vo.BookVo;
import bookmall.vo.CartVo;

public class CartDaoTest {

	public static void main(String[] args) {
		CartVo vo = new CartVo();
		vo.setMember_no(3L);
		vo.setBook_no(1L);
		vo.setCount(2);
		insertTest(vo);
		findAll();

	}

	private static void findAll() {
		List<CartVo> list = new CartDao().findAll();
		for(CartVo vo : list) {
			System.out.println(vo);
		}
		
		
	}

	private static void insertTest(CartVo vo) {
		new CartDao().insert(vo);
		
	}

}
