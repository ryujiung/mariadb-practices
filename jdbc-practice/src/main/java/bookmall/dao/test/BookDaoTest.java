package bookmall.dao.test;

import java.util.List;

import bookmall.vo.BookVo;
import bookmall.vo.MemberVo;

public class BookDaoTest {

	public static void main(String[] args) {
		BookVo vo = new BookVo();
		vo.setPrice(3000);
		vo.setTitle("dfjkasj");
		vo.setCategory_no(1L);
		insertTest(vo);
		findAll();

	}



	private static void findAll() {
		List<BookVo> list = new BookDao().findAll();
		for(BookVo vo : list) {
			System.out.println(vo);
		}
		
	}

	private static void insertTest(BookVo vo) {
		new BookDao().insert(vo);
		
	}

}
