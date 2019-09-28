package bean;

import bean.User;

public class Order {
	private int id;
	private User user;
	
	public void setId(int id) {
		this.id = id;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	
	public int getId() {
		return this.id;
	}
	
	public User getUser() {
		return this.user;
	}
}
