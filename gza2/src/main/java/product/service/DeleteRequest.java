package product.service;

import java.util.Map;

public class DeleteRequest {

	private String memberid;
	private int productNum;
	private String productTitle;
	private String imageFileName;
	private String productContent;

	public DeleteRequest(String memberid, int productNum, String productTitle, String imageFileName,
			String productContent) {
		this.memberid = memberid;
		this.productNum = productNum;
		this.productTitle = productTitle;
		this.imageFileName = imageFileName;
		this.productContent = productContent;
	}

	public String getMemberid() {
		return memberid;
	}

	public int getProductNum() {
		return productNum;
	}

	public String getProductTitle() {
		return productTitle;
	}

	public String getImageFileName() {
		return imageFileName;
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