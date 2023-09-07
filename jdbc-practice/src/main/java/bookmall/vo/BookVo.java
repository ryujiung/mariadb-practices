package bookmall.vo;

public class BookVo {
	private Long no;
	private Long category_no;
	private String title;
	private int price;

	@Override
	public String toString() {
		return "BookVo [no=" + no + ", category_no=" + category_no + ", title=" + title + ", price=" + price + "]";
	}
	public Long getNo() {
		return no;
	}
	public void setNo(Long no) {
		this.no = no;
	}
	public Long getCategory_no() {
		return category_no;
	}
	public void setCategory_no(Long i) {
		this.category_no = i;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}

}
