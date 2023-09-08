package bookmall.vo;

public class OrdersBookVo {
	private Long no;
	private Long orders_no;
	private Long book_no;
	private String title;
	private int count;
	private int price;
	
	public OrdersBookVo(Long book_no,String title,int count) {
		this.book_no = book_no;
		this.title = title;
		this.count = count;
		
	}
	public Long getNo() {
		return no;
	}
	public void setNo(Long no) {
		this.no = no;
	}
	public Long getOrders_no() {
		return orders_no;
	}
	public void setOrders_no(Long orders_no) {
		this.orders_no = orders_no;
	}
	public Long getBook_no() {
		return book_no;
	}
	public void setBook_no(Long book_no) {
		this.book_no = book_no;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	@Override
	public String toString() {
		return "OrdersBookVo [no=" + no + ", orders_no=" + orders_no + ", book_no=" + book_no + ", title=" + title
				+ ", count=" + count + ", price=" + price + "]";
	}

}
