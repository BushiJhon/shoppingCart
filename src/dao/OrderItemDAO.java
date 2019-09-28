package dao;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import bean.OrderItem;

public class OrderItemDAO {
	public OrderItemDAO() throws ClassNotFoundException {
		Class.forName("com.mysql.jdbc.Driver");
	}
	
	private Connection getConnection() throws SQLException {
		return DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/shoppingCart?characterEncoding=UTF-8", "root", "root");
	}
	
	public void add(OrderItem orderItem){
		String sql = "insert into orderitem values(null, ?, ?, ?)";
		try(Connection c = this.getConnection(); PreparedStatement ps = c.prepareStatement(sql);){
			ps.setInt(1, orderItem.getOrder().getUser().getId());
			ps.setInt(2, orderItem.getNum());
			ps.setInt(3, orderItem.getOrder().getId());
			
			ps.execute();
			
			ResultSet rs = ps.getGeneratedKeys();
			if(rs.next()) {
				orderItem.setId(rs.getInt("id"));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
}
