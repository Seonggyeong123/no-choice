package home.service;

import java.util.List;

import product.service.ProductWithImage;

public class HomePage {

	// 여기에 리스트객체 추가
	private List<ProductWithImage> content;

	// 여기에 매개변수 추가
	public HomePage(List<ProductWithImage> content) {
		this.content = content;
	}

	// 아래로 접근자 추가
	public List<ProductWithImage> getContent() {
		return content;
	}

}
