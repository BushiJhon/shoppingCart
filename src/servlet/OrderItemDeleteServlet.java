package servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.OrderItem;

import java.io.IOException;
import java.util.List;

public class OrderItemDeleteServlet extends HttpServlet{
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		int id = Integer.parseInt(req.getParameter("id"));
		List<OrderItem> orderItemList = (List<OrderItem>) req.getSession().getAttribute("orderItemList");
		
		int index = 0;
		for(OrderItem orderItem : orderItemList) {
			if(orderItem.getId() != id) {
				index++;
			}else {
				break;
			}
		}
		
		orderItemList.remove(index);
		
		req.getRequestDispatcher("listOrderItem").forward(req, res);
	}
}
