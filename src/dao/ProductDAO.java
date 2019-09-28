package dao;

import bean.Product;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.List;
import java.util.ArrayList;

public class ProductDAO {
	public ProductDAO() throws ClassNotFoundException {
		Class.forName("com.mysql.jdbc.Driver");
	}
	
	private Connection getConnect() throws SQLException {
		return DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/shoppingCart?characterEncoding=UTF-8", "root", "root");
	}
	
	public Product retrieve(int id) {
		Product product = null;
		String sql = "select * from product where id = ?";
		try(Connection connection = this.getConnect(); PreparedStatement preparedStatement = connection.prepareStatement(sql);){
			preparedStatement.setInt(1, id);
			
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()) {
				product = new Product();
				product.setId(id);
				product.setName(rs.getString("name"));
				product.setPrice(rs.getFloat("price"));
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return product;
	}
	
	public List<Product> listProduct() {
		List<Product> list = new ArrayList<>();
		
		String sql = "select * from product";
		try(Connection c = this.getConnect(); PreparedStatement ps = c.prepareStatement(sql);){
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				Product p = new Product();
				p.setId(rs.getInt("id"));
				p.setName(rs.getString("name"));
				p.setPrice(rs.getFloat("price"));
				
				list.add(p);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
//	public static void main(String [] args) throws ClassNotFoundException {
//		System.out.println(new ProductDAO().listProduct().get(3).getName());
//	}
}
