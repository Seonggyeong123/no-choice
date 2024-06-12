package product.service;

import image.model.Image;
import product.model.Product;
import product.model.ProductContent;

public class ProductData {

	private Product product;
	private ProductContent content;
	private Image image;

	public ProductData(Product product, ProductContent content, Image image) {
		super();
		this.product = product;
		this.content = content;
		this.image = image;
	}

	public Product getProduct() {
		return product;
	}

	public ProductContent getContent() {
		return content;
	}

	public Image getImage() {
		return image;
	}

}