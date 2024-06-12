package product.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import image.dao.ImageDAO;
import image.model.Image;
import jdbc.connection.ConnectionProvider;
import product.dao.ProductDAO;
import product.model.Product;

public class ListProductService {

	private ProductDAO productDAO = new ProductDAO();
	private ImageDAO imageDAO = new ImageDAO();
	private int size = 10;

	public ProductPage getProductPage(int pageNum) {
		try (Connection con = ConnectionProvider.getConnection()) {
			int total = productDAO.selectCount(con);
			List<Product> products = productDAO.select(con, (pageNum - 1) * size, size);
			List<ProductWithImage> content= new ArrayList<>();
			for (Product product : products) {
				List<Image> images = imageDAO.selectByProductNum(con, product.getProductNum());
				content.add(new ProductWithImage(products, images));
			}
			return new ProductPage(total, pageNum, size, content);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}