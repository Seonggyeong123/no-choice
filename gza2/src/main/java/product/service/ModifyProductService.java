package product.service;

import java.sql.Connection;
import java.sql.SQLException;

import image.dao.ImageDAO;
import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;
import product.dao.ProductContentDAO;
import product.dao.ProductDAO;
import product.model.Product;

public class ModifyProductService {
	
	private ProductDAO productDAO = new ProductDAO();
	private ProductContentDAO contentDAO = new ProductContentDAO();
	private ImageDAO imageDAO = new ImageDAO();
	
	public void modify(ModifyRequest modReq) {
		Connection con=null;
		try {
			con=ConnectionProvider.getConnection();
			con.setAutoCommit(false);
			
			Product pro = productDAO.selectById(con, modReq.getProductNum());
			
			if (pro == null) {
				throw new ProductNotFoundException(null);
			}
			if (!canModify(modReq.getMemberid(), pro)) {
				throw new PermissionDeninedException(null);
			}
			productDAO.update(con, modReq.getProductTitle(), modReq.getPrice(), modReq.getProductNum());
			contentDAO.update(con, modReq.getProductNum(), modReq.getProductSubtitle(),modReq.getProductContent());
			imageDAO.update(con, modReq.getProductOriginFileName(), modReq.getProductStoreFileName(), modReq.getProductNum());
			con.commit();
		} catch (SQLException e) {
			JdbcUtil.rollback(con);
			throw new RuntimeException(e);
		} catch (PermissionDeninedException e) {
			JdbcUtil.rollback(con);
		} finally {
			JdbcUtil.close(con);
		}
	}
	
	private boolean canModify(String modifyingMemberId, Product product) {
		return product.getWriter().getId().equals(modifyingMemberId);
	}

}