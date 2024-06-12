package product.service;

import java.sql.Connection;
import java.sql.SQLException;

import image.dao.ImageDAO;
import image.model.Image;
import jdbc.connection.ConnectionProvider;
import product.dao.ProductContentDAO;
import product.dao.ProductDAO;
import product.model.Product;
import product.model.ProductContent;

public class ReadProductService {

	private ProductDAO productDAO = new ProductDAO();
	private ProductContentDAO contentDAO = new ProductContentDAO();
	private ImageDAO imgDAO = new ImageDAO();

	public ProductData getProduct(int productNum) {
		try (Connection con = ConnectionProvider.getConnection()) {
			Product product = productDAO.selectById(con, productNum);
			if (product == null) {
				throw new ProductNotFoundException(null);
			}

			ProductContent content = contentDAO.selectById(con, productNum);
			if (content == null) {
				throw new ProductContentNotFoundException();
			}

			Image img = imgDAO.selectOneByProductNum(con, productNum);
			if (img == null) {
			}

			return new ProductData(product, content, img);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}