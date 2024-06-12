package product.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jdbc.JdbcUtil;
import product.model.Product;
import product.model.Writer;

public class ProductDAO {
	
	public Product insert(Connection con, Product pro) throws SQLException{
		
		PreparedStatement ps=null;
		Statement stmt=null;
		ResultSet rs=null;
		
		String query="insert into product (product_title, writer_id, price) values(?,?,?)";
		
		try {
			ps=con.prepareStatement(query);
			
			ps.setString(1, pro.getProductTitle());
			ps.setString(2, pro.getWriter().getId());
			ps.setInt(3, pro.getPrice());
			int res=ps.executeUpdate();
			
			if (res > 0) {
				stmt=con.createStatement();
				rs=stmt.executeQuery("select last_insert_id() from product");
				if (rs.next()) {
					Integer Num=rs.getInt(1);
					return new Product(Num, pro.getProductTitle(), pro.getPrice(), pro.getWriter());
				}
			}
			
			return null;
		} finally {
			JdbcUtil.close(ps);
			JdbcUtil.close(rs);
			JdbcUtil.close(stmt);
		}
	}
	
	private Timestamp toTimestamp(Date date) {
		return new Timestamp(date.getTime());
	}
	
	public int selectCount(Connection con) throws SQLException{
		Statement stmt=null;
		ResultSet rs=null;
		
		try {
			stmt = con.createStatement();
			rs=stmt.executeQuery("select count(*) from product");
			
			if (rs.next()) {
				return rs.getInt(1);
			}
			return 0;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(stmt);
		}
	}
	
	public List<Product> select(Connection con, int startRow, int size) throws SQLException{
		PreparedStatement ps=null;
		ResultSet rs=null;
		try {
			ps=con.prepareStatement("select*from product order by product_num desc limit ?,?");
			ps.setInt(1, startRow);
			ps.setInt(2, size);
			rs=ps.executeQuery();
			List<Product> result=new ArrayList<Product>();
			while(rs.next()) {
				result.add(convertProduct(rs));
			}
			return result;
			
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(ps);
		}
	}
	
	public List<Product> selectByCategory(Connection con, int num, int startRow, int size) throws SQLException{
		PreparedStatement ps=null;
		ResultSet rs=null;
		try {
			ps=con.prepareStatement("select*from product where product_num=? order by product_num desc limit ?,?");
			ps.setInt(1, num);
			ps.setInt(2, startRow);
			ps.setInt(3, size);
			rs=ps.executeQuery();
			List<Product> result=new ArrayList<Product>();
			while(rs.next()) {
				result.add(convertProduct(rs));
			}
			return result;
			
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(ps);
		}
	}
	
	private Product convertProduct(ResultSet rs) throws SQLException {
		return new Product(rs.getInt("product_num"), rs.getString("product_title"), rs.getInt("price"), new Writer(rs.getString("writer_id"), null));
	}
	
	private Date toDate(Timestamp timestamp) {
		return new Date(timestamp.getTime());
	}
	
	public Product selectById(Connection con, int no)throws SQLException{
		PreparedStatement ps=null;
		ResultSet rs=null;
		try {
			ps=con.prepareStatement("select*from product where product_num=?");
			ps.setInt(1, no);
			rs=ps.executeQuery();
			Product product=null;
			
			if(rs.next()) {
				product=convertProduct(rs);
			}
			return product;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(ps);
		}
	}
	
	public int update(Connection con, String title, int price, int no) throws SQLException{
		String query = "update product set product_title=?, price=? where product_num=?";
		try (PreparedStatement ps = con.prepareStatement(query)){
			ps.setString(1, title);
			ps.setInt(2, price);
			ps.setInt(3, no);
			return ps.executeUpdate();
		} 
	}
	
	public int delete(Connection con, int no) throws SQLException{
		String query="delete from product where product_num=?";
		
		try (PreparedStatement ps=con.prepareStatement(query)){
			ps.setInt(1, no);
			
			return ps.executeUpdate();
		}
	}

}