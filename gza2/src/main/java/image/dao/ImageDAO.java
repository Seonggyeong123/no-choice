package image.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import image.model.Image;
import jdbc.JdbcUtil;

public class ImageDAO {
	public Image insert(Connection con, Image img) throws SQLException {
		String query = "insert into image (original_name, store_name, seller_id, product_num) values (?, ?, ?, ?)";
		try (PreparedStatement ps = con.prepareStatement(query)) {
			ps.setString(1, img.getOriginalName());
			ps.setString(2, img.getStoreName());
			ps.setString(3, img.getSellerId());
			ps.setInt(4, img.getProductNum());
			int result = ps.executeUpdate();

			if (result > 0) {
				return img;
			} else {
				return null;
			}
		}
	}

	public Image selectOneByProductNum(Connection con, int id) throws SQLException {
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = con.prepareStatement("select * from image where product_num = ?");
			ps.setInt(1, id);
			rs = ps.executeQuery();

			Image img = null;
			if (rs.next()) {
				img = new Image(rs.getInt("image_id"), rs.getString("original_name"), rs.getString("store_name"),
						rs.getString("seller_id"), rs.getInt("product_num"));
			}
			return img;
		} finally {
			JdbcUtil.close(ps);
			JdbcUtil.close(ps);
		}
	}

	public int update(Connection con, String original, String store, int num) throws SQLException {
		String query = "update image set original_name=?, store_name=? where product_num=?";

		try (PreparedStatement ps = con.prepareStatement(query)) {
			ps.setString(1, original);
			ps.setString(2, store);
			ps.setInt(3, num);

			return ps.executeUpdate();
		}
	}

	public int delete(Connection con, int productNum) throws SQLException {
		String query = "delete from image where product_num=?";

		try (PreparedStatement ps = con.prepareStatement(query)) {
			ps.setInt(1, productNum);

			return ps.executeUpdate();
		}
	}

	public int deleteToChange(Connection con, int imgId) throws SQLException {
		String query = "delete from image where img_id=?";

		try (PreparedStatement ps = con.prepareStatement(query)) {
			ps.setInt(1, imgId);

			return ps.executeUpdate();
		}
	}
	
	public List<Image> selectByProductNum(Connection con, int num) throws SQLException{
		String query = "select * from image where product_num = ?";
		
		try (PreparedStatement ps = con.prepareStatement(query)){
			ps.setInt(1, num);
			ResultSet rs = ps.executeQuery();
			List<Image> images = new ArrayList<>();
			while (rs.next()) {
				images.add(new Image(rs.getInt("image_id"), rs.getString("original_name"), 
						rs.getString("store_name"), rs.getString("seller_id"), rs.getInt("product_num")));
			}
			return images;
		}
	}
	
}
