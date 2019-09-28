package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import bean.Order;

public class OrderDAO {
	public OrderDAO() throws ClassNotFoundException {
		Class.forName("com.mysql.jdbc.Driver");
	}
	
	private Connection getConnection() throws SQLException {
		return DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/shoppingCart?characterEncoding=UTF-8", "root", "root");
	}
	
	public void add(Order order) {
		String sql = "insert into order_ values(null, ?)";
		try(Connection c = this.getConnection(); PreparedStatement ps = c.prepareStatement(sql);){
			ps.setInt(1, order.getUser().getId());
			
			ps.execute();
			
			ResultSet rs = ps.getGeneratedKeys();
			if(rs.next()) {
				order.setId(rs.getInt("id"));
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
}
