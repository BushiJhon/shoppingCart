package dao;

import bean.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDAO {
	public UserDAO() throws ClassNotFoundException {
		Class.forName("com.mysql.jdbc.Driver");
	}
	
	private Connection getConnection() throws SQLException {
		return DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/shoppingCart?characterEncoding=UTF-8", "root", "root");
	}
	
	public User retrieve(String name, String password) {
		User user = null;
		String sql = "select * from User where name = ? and password = ?";
		try(Connection c = this.getConnection(); PreparedStatement ps = c.prepareStatement(sql);){
			ps.setString(1, name);
			ps.setString(2, password);
			
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				user = new User();
				user.setId(rs.getInt("id"));
				user.setName(rs.getString("name"));
				user.setPassword(rs.getString("password"));
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return user;
	}
}
