package image.model;

public class Image {

	private Integer imageId;
	private String originalName;
	private String storeName;
	private String sellerId;
	private int productNum;

	public Image(Integer imageId, String originalName, String storeName, String sellerId, int productNum) {
		this.imageId = imageId;
		this.originalName = originalName;
		this.storeName = storeName;
		this.sellerId = sellerId;
		this.productNum = productNum;
	}

	public Integer getImageId() {
		return imageId;
	}

	public String getOriginalName() {
		return originalName;
	}

	public String getStoreName() {
		return storeName;
	}

	public String getSellerId() {
		return sellerId;
	}

	public int getProductNum() {
		return productNum;
	}

}
