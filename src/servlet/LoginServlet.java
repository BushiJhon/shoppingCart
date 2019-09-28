package servlet;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.User;
import bean.OrderItem;

import dao.UserDAO;

import java.util.List;

public class LoginServlet extends HttpServlet{
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
		String name = req.getParameter("name");
		String password = req.getParameter("password");
		
		User user = null;
		try {
			user = new UserDAO().retrieve(name, password);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(user != null) {
			req.getSession().setAttribute("user", user);
			res.sendRedirect("listProduct");
		}else {
			res.sendRedirect("login.jsp");
		}
		
	}
}
