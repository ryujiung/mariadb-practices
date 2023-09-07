package bookmall.dao.test;

import java.util.List;

import bookmall.vo.MemberVo;
import emaillist.dao.EmaillistDao;
import emaillist.vo.EmaillistVo;

public class MemberDaoTest {

	public static void main(String[] args) {
		MemberVo vo = new MemberVo();
		vo.setName("둘리");
		vo.setPhone("9292-1294-1123");
		vo.setEmail("dooly3@gmail.com");
		vo.setPw("3252");
		insertTest(vo);
		findAll();

	}

	private static void findAll() {
		List<MemberVo> list = new MemberDao().findAll();
		for(MemberVo vo : list) {
			System.out.println(vo);
		}
		
	}

	private static void insertTest(MemberVo vo) {
		new MemberDao().insert(vo);
		
	}

}
