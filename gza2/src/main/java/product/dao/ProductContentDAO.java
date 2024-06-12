package product.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jdbc.JdbcUtil;
import product.model.ProductContent;

public class ProductContentDAO {
	
	public ProductContent insert(Connection con, ProductContent content) throws SQLException{
		PreparedStatement ps=null;
		String query="insert into product_content (product_num, product_content, product_subtitle, product_type, guests, location, reg_date, update_date) values(?,?,?,?,?,?,?,?)";
		
		try {
			ps=con.prepareStatement(query);
			ps.setInt(1, content.getProductNum());
			ps.setString(2, content.getProductContent());
			ps.setString(3, content.getProductSubTitle());
			ps.setString(4, content.getProductType());
			ps.setInt(5, content.getGuests());
			ps.setString(6, content.getLocation());
			ps.setTimestamp(7, toTimestamp(content.getRegDate()));
			ps.setTimestamp(8, toTimestamp(content.getUpdateDate()));
			
			int res=ps.executeUpdate();
			
			if (res > 0) {
				return content;
			} else {
				return null;
			}
			
		} finally {
			JdbcUtil.close(ps);
		}
	}
	
	private Timestamp toTimestamp(Date date) {
		return new Timestamp(date.getTime());
	}
	
	public ProductContent selectById(Connection con, int no) throws SQLException{
		PreparedStatement ps=null;
		ResultSet rs=null;
		try {
			ps=con.prepareStatement("select*from product_content where product_num=?");
			ps.setInt(1, no);
			rs=ps.executeQuery();
			
			ProductContent content=null;
			if(rs.next()) {
				content=new ProductContent(rs.getInt("product_num"), rs.getString("product_subtitle"), rs.getString("product_content"), rs.getString("product_type"), rs.getInt("guests"), rs.getString("location"), toDate(rs.getTimestamp("reg_date")), toDate(rs.getTimestamp("update_date")));
			}
			return content;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(ps);
		}
	}
	
	public List<ProductContent> selectByCategory(Connection con, String category, int startrow, int size) throws SQLException{
		PreparedStatement ps = null;
		ResultSet rs = null;
		String query = "select*from product_content where product_type=? order by product_num desc limit ?,?";
		
		try {
			ps=con.prepareStatement(query);
			ps.setString(1, category);
			ps.setInt(2, startrow);
			ps.setInt(3, size);
			rs=ps.executeQuery();
			
			List<ProductContent> content=new ArrayList<>();
			while (rs.next()) {
				content.add(new ProductContent(rs.getInt("product_num"), rs.getString("product_subtitle"), rs.getString("product_content"), rs.getString("product_type"), rs.getInt("guests"), rs.getString("location"), toDate(rs.getTimestamp("reg_date")), toDate(rs.getTimestamp("update_date"))));
			}
			return content;
		} finally {
			JdbcUtil.close(ps);
			JdbcUtil.close(rs);
		}
	}
	
	private Date toDate(Timestamp timestamp) {
		return new Date(timestamp.getTime());
	}
	
	public int update(Connection con, int no, String subtitle, String content) throws SQLException{
		String query="update product_content set product_content=?, product_subtitle=? where product_num=?";
		try (PreparedStatement ps=con.prepareStatement(query)){
			ps.setString(1, content);
			ps.setString(2, subtitle);
			ps.setInt(3, no);
			
			return ps.executeUpdate();
		}
	}
	
	public int delete(Connection con, int no) throws SQLException {
		String query="delete from product_content where product_num=?";
		
		try (PreparedStatement ps = con.prepareStatement(query)){
			ps.setInt(1, no);
			
			return ps.executeUpdate();
		}
	}

}