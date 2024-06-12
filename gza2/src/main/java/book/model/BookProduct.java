package book.model;

public class BookProduct {

	private Integer productCode;
	private Integer productNum;
	private Integer bookCode;
	private int price;
	private int guest;
	private int totalPrice;

	public BookProduct(Integer productCode, Integer productNum, Integer bookCode, int price, int guest, int totalPrice) {
		this.productCode = productCode;
		this.productNum = productNum;
		this.bookCode = bookCode;
		this.price = price;
		this.guest = guest;
		this.totalPrice = totalPrice;
	}

	public Integer getProductCode() {
		return productCode;
	}

	public Integer getProductNum() {
		return productNum;
	}

	public Integer getBookCode() {
		return bookCode;
	}

	public int getPrice() {
		return price;
	}

	public int getGuest() {
		return guest;
	}

	public int getTotalPrice() {
		return totalPrice;
	}

}
