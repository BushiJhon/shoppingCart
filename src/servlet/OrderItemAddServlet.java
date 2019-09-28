package servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ProductDAO;

import bean.Product;
import bean.OrderItem;

import java.util.List;
import java.io.IOException;
import java.util.ArrayList;

public class OrderItemAddServlet extends HttpServlet{
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		
		ProductDAO productDAO = null;
		try {
			productDAO = new ProductDAO();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		int pid = Integer.parseInt(req.getParameter("pid"));
		
		Product product = productDAO.retrieve(pid);
		
		OrderItem orderItem = new OrderItem();
//		orderItem.setId(pid);
		orderItem.setProduct(product);
		orderItem.setNum(Integer.parseInt(req.getParameter("num")));
		
		List<OrderItem> orderItemList = (List<OrderItem>) req.getSession().getAttribute("orderItemList");
		
		if(orderItemList == null) {
			orderItemList = new ArrayList<>();
			req.getSession().setAttribute("orderItemList", orderItemList);
		}
		
		boolean found = false;
		for(OrderItem orderItems : orderItemList) {
			if(orderItem.getId() == orderItems.getId()) {
				orderItems.setNum(orderItems.getNum() + orderItem.getNum());
				found = true;
				break;
			}
		}
		
		if(!found)
			orderItemList.add(orderItem);
		
		res.sendRedirect("listOrderItem");
//		req.getRequestDispatcher("listOrderItem").forward(req, res);
	}
}
