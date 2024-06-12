package image.service;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import image.dao.ImageDAO;
import image.model.Image;
import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;
import product.service.PermissionDeninedException;

public class DeleteImageService {

	private ImageDAO imageDAO = new ImageDAO();

	public void delete(int productNum) {
		Connection con = null;
		try {
			con = ConnectionProvider.getConnection();
			con.setAutoCommit(false);

			Image image = imageDAO.selectOneByProductNum(con, productNum);

			if (image == null) {

			}
			imageDAO.delete(con, productNum);

		} catch (SQLException e) {
			JdbcUtil.rollback(con);
			throw new RuntimeException();
		} catch (PermissionDeninedException e) {
			JdbcUtil.rollback(con);
			throw e;
		} finally {
			JdbcUtil.close(con);
		}

	}
}
