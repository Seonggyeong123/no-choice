package product.model;

public class Product {

	private Integer productNum;
	private String productTitle;
	private int price;
	private Writer writer;

	public Product(Integer productNum, String productTitle, int price, Writer writer) {
		super();
		this.productNum = productNum;
		this.productTitle = productTitle;
		this.price = price;
		this.writer = writer;
	}

	public Integer getProductNum() {
		return productNum;
	}

	public String getProductTitle() {
		return productTitle;
	}

	public int getPrice() {
		return price;
	}

	public Writer getWriter() {
		return writer;
	}

}