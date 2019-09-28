package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.OrderDAO;
import dao.OrderItemDAO;

import bean.Order;
import bean.OrderItem;
import bean.User;

public class OrderCreateServlet extends HttpServlet{
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
		Order order = new Order();
		order.setUser((User)req.getSession().getAttribute("user"));
		
		try {
			new OrderDAO().add(order);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		List<OrderItem> orderItemList = (List<OrderItem>) req.getSession().getAttribute("orderItemList");
		
		OrderItemDAO orderItemDAO = null;
		try {
			orderItemDAO = new OrderItemDAO();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(OrderItem orderItem : orderItemList) {
			orderItem.setOrder(order);
			orderItemDAO.add(orderItem);
		}
		
		orderItemList.clear();
		
		res.sendRedirect("createSuccess.jsp");
	}
}
