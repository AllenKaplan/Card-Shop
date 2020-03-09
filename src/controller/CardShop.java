package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.ProductBean;

/**
 * Servlet implementation class CardShop
 */
@WebServlet({"/CardShop", "/CardShop/*"})
public class CardShop extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CardShop() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(request.getRequestURI().contains("cart")) {
			response.getWriter().append("This is your shopping cart");
		}
		
		else if(request.getRequestURI().contains("payment")) {
			response.getWriter().append("Make your payment");
		}
		
		else if(request.getRequestURI().contains("analytics")) {
			response.getWriter().append("Wow, look at these analytics");
		}
		
		else if(request.getRequestURI().contains("login")) {
			response.getWriter().append("Please log in");
		}
		
		else {
			model.CardShop shop = new model.CardShop();
			List<ProductBean> products = shop.retrieveCards();
			
			request.setAttribute("products", products);
			
			String target = "/home.jspx";
			request.getRequestDispatcher(target).forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
