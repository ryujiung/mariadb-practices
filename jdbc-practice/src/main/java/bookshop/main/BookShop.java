package bookshop.main;

import java.util.List;
import java.util.Scanner;

import bookshop.dao.BookDao;
import bookshop.vo.BookVo;

public class BookShop {
	public static void main(String[] args) {
		displayBookInfo();

		Scanner scanner = new Scanner(System.in);
		System.out.print("대여 하고 싶은 책의 번호를 입력하세요:");
		int no = scanner.nextInt();
		scanner.close();

		BookVo vo = new BookVo();
		vo.setNo(no);
		vo.setRent("Y");
		
		boolean result = new BookDao().updateRent(vo);
		System.out.println(result ? "대여성공":"대여실패");
		
		displayBookInfo();
	}

	private static void displayBookInfo() {
		System.out.println("*****도서 정보 출력하기******");
		List<BookVo> list = new BookDao().findAll();
		for(BookVo bv : list) {
			System.out.println(bv.getNo() + "| 제목 : " + bv.getTitle() + "| 대여 여부 : " + bv.getRent() + "| 작가 :"+ bv.getAuthor());
		}
	}
}