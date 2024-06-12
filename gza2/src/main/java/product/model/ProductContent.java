package product.model;

import java.util.Date;

public class ProductContent {

	private Integer productNum;
	private String productSubTitle;
	private String productContent;
	private String productType;
	private int guests;
	private String location;
	private Date regDate;
	private Date updateDate;

	public ProductContent(Integer productNum, String productSubTitle, String productContent, String productType, int guests,
			String location, Date regDate, Date updateDate) {
		super();
		this.productNum = productNum;
		this.productSubTitle=productSubTitle;
		this.productContent = productContent;
		this.productType = productType;
		this.guests = guests;
		this.location = location;
		this.regDate = regDate;
		this.updateDate = updateDate;
	}

	public Integer getProductNum() {
		return productNum;
	}

	public String getProductContent() {
		return productContent;
	}
	
	public String getProductSubTitle() {
		return productSubTitle;
	}

	public String getProductType() {
		return productType;
	}

	public int getGuests() {
		return guests;
	}

	public String getLocation() {
		return location;
	}

	public Date getRegDate() {
		return regDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

}