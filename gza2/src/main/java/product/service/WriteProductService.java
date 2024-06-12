package product.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import image.dao.ImageDAO;
import image.model.Image;
import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;
import product.dao.ProductContentDAO;
import product.dao.ProductDAO;
import product.model.Product;
import product.model.ProductContent;

public class WriteProductService {
	
	private ProductDAO productDAO = new ProductDAO();
	private ProductContentDAO contentDAO = new ProductContentDAO();
	private ImageDAO imageDAO = new ImageDAO();
	
	public Integer write(WriteRequest req) {
		Connection con=null;
		
		try {
			con=ConnectionProvider.getConnection();
			con.setAutoCommit(false);
			
			Product product=toProduct(req);
			Product savedProduct = productDAO.insert(con, product);
			
			if (savedProduct == null) {
				throw new RuntimeException("fail to insert product");
			}
			
			ProductContent content = toContent(savedProduct, req);
			ProductContent savedContent = contentDAO.insert(con, content);
			
			if (savedContent == null) {
				throw new RuntimeException("fail to insert product_content");
			}
			
			Image img = toImage(savedProduct, req);
			Image savedImg = imageDAO.insert(con, img);
			
			if (savedImg == null) {
				throw new RuntimeException("fail to insert image");
			}
			
			con.commit();
			
			return savedProduct.getProductNum();
		} catch (SQLException e) {
			JdbcUtil.rollback(con);
			throw new RuntimeException(e);
		} catch (RuntimeException e) {
			JdbcUtil.rollback(con);
			throw e;
		} finally {
			JdbcUtil.close(con);
		}
	}
	
	private Product toProduct(WriteRequest req) {
		Date now = new Date();
		return new Product(null, req.getProductTitle(), req.getPrice(), req.getWriter());
	}
	
	private ProductContent toContent(Product save, WriteRequest req) {
		Date now = new Date();
		return new ProductContent(save.getProductNum(), req.getProductSubTitle(), req.getProductContent(), req.getProductType() , req.getGuests(), req.getLocation(), now, now);
	}
	
	private Image toImage(Product save, WriteRequest req) {
		
		return new Image(null, req.getOriginalFileName(), req.getStoreFileName(), req.getWriter().getId(), save.getProductNum());
	}
	
	private Date toDate(String date) {
		DateFormat df = new SimpleDateFormat("yyMMdd");
		Date day = null;
		try {
		day = df.parse(date);
		return day;
		} catch(ParseException e) {
			return null;
		}
	}
}