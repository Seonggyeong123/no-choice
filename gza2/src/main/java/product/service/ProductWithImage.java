package product.service;

import java.util.List;

import image.model.Image;
import product.model.Product;
import product.model.ProductContent;

public class ProductWithImage {
	private List<Product> product;
	private List<ProductContent> content;
	private List<Image> images;

	public ProductWithImage(List<Product> product, List<ProductContent> content, List<Image> images) {
		this.product = product;
		this.images = images;
		this.content=content;
	}
	public ProductWithImage(List<Product> product, List<Image> images) {
		this.product = product;
		this.images = images;
	}
	
	public List<ProductContent> getContent() {
		return content;
	}

	public List<Product> getProduct() {
		return product;
	}

	public List<Image> getImages() {
		return images;
	}
}
