package bookshop.vo;

public class BookVo {
	private String title;
	private String author;
	private String rent;
	private int no;
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getTitle() {
		return title;
	}
	public String getRent() {
		return rent;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public void setRent(String rent) {
		this.rent = rent;
	}
	@Override
	public String toString() {
		return "BookVo [title=" + title + ", author=" + author + ", rent=" + rent + "]";
	}
	


}
