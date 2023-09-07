package bookmall.dao.test;

import java.util.List;

import bookmall.vo.BookVo;
import bookmall.vo.CategoryVo;

public class CategoryDaoTest {

	public static void main(String[] args) {
		CategoryVo vo = new CategoryVo();
		vo.setName("drama");
		insertTest(vo);
		findAll();

	}

	private static void findAll() {
		List<CategoryVo> list = new CategoryDao().findAll();
		for(CategoryVo vo : list) {
			System.out.println(vo);
		}
		
	}

	private static void insertTest(CategoryVo vo) {
		new CategoryDao().insert(vo);
		
	}

}
