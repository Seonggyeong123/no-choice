package product.service;

import java.util.Map;

import product.model.Writer;

public class WriteRequest {

	private Writer writer;
	private String productTitle;
	private String productSubTitle;
	private String productContent;
	private String productType;
	private int price;
	private String originalFileName;
	private String storeFileName;
	private int guests;
	private String location;

	public WriteRequest(Writer writer, String productTitle, String productSubTitle, String productContent,
			String productType, int price, String originalFileName, String storeFileName, int guests, String location) {
		this.writer = writer;
		this.productTitle = productTitle;
		this.productSubTitle = productSubTitle;
		this.productContent = productContent;
		this.productType = productType;
		this.price = price;
		this.originalFileName = originalFileName;
		this.storeFileName = storeFileName;
		this.guests = guests;
		this.location = location;
	}

	public Writer getWriter() {
		return writer;
	}

	public String getProductTitle() {
		return productTitle;
	}

	public String getProductSubTitle() {
		return productSubTitle;
	}

	public String getProductContent() {
		return productContent;
	}

	public String getProductType() {
		return productType;
	}

	public int getPrice() {
		return price;
	}

	public String getOriginalFileName() {
		return originalFileName;
	}

	public String getStoreFileName() {
		return storeFileName;
	}

	public int getGuests() {
		return guests;
	}

	public String getLocation() {
		return location;
	}

	public void validate(Map<String, Boolean> errors) {
		if (productTitle == null || productTitle.trim().isEmpty()) {
			errors.put("title", Boolean.TRUE);
		}
	}

}