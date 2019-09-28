package servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Product;

import dao.ProductDAO;

import java.io.IOException;
import java.util.List;

public class ProductListServlet extends HttpServlet{
	public void doGet(HttpServletRequest req, HttpServletResponse res) {
		try {
			List<Product> products = new ProductDAO().listProduct();
//			System.out.println(products);
			req.setAttribute("products", products);
			
			req.getRequestDispatcher("listProduct.jsp").forward(req, res);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch(IOException e) {
			e.printStackTrace();
		} catch(ServletException e) {
			e.printStackTrace();
		}
	}
}
