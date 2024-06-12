package product.service;

import java.util.Map;

public class ModifyRequest {

	private String memberid;
	private int productNum;
	private int price;
	private String productTitle;
	private String productSubtitle;
	private String productOriginFileName;
	private String productStoreFileName;
	private String productContent;

	public ModifyRequest(String memberid, int productNum, int price, String productTitle, String productSubtitle,
			String productOriginFileName, String productStoreFileName, String productContent) {
		this.memberid = memberid;
		this.productNum = productNum;
		this.price = price;
		this.productTitle = productTitle;
		this.productSubtitle = productSubtitle;
		this.productOriginFileName = productOriginFileName;
		this.productStoreFileName = productStoreFileName;
		this.productContent = productContent;
	}

	public String getMemberid() {
		return memberid;
	}

	public int getProductNum() {
		return productNum;
	}

	public int getPrice() {
		return price;
	}

	public String getProductTitle() {
		return productTitle;
	}

	public String getProductSubtitle() {
		return productSubtitle;
	}

	public String getProductOriginFileName() {
		return productOriginFileName;
	}

	public String getProductStoreFileName() {
		return productStoreFileName;
	}

	public String getProductContent() {
		return productContent;
	}

	public void validate(Map<String, Boolean> errors) {
		if (productTitle == null || productTitle.trim().isEmpty()) {
			errors.put("title", Boolean.TRUE);
		}
	}

}