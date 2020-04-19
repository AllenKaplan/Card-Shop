package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.CardBean;
import bean.ProductBean;
import model.CardModel;

/**
 * Servlet implementation class CardShop
 */
@WebServlet({"/home", "/home/*"})
public class Shop extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Shop() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<String, String[]> params = request.getParameterMap();

		@SuppressWarnings("unchecked")
		Map<ProductBean, Integer> cart = (Map<ProductBean, Integer>) request.getSession().getAttribute("cart");
		
		
		if(request.getRequestURI().contains("cart")) {
			response.getWriter().append("This is your shopping cart");
			response.getWriter().append(cart.toString());
		} else if(request.getRequestURI().contains("login")) {
			response.getWriter().append("Please log in");
		} else if(params.containsKey("addToCart")) {
			CardModel cards = new CardModel();
			
			ProductBean cardToAdd = cards.retrieveCard(request.getParameter("addToCart"));
					
			if (cart.containsKey(cardToAdd)) {
				int currentQnty = cart.get(cardToAdd);
				cart.put(cardToAdd, currentQnty+1);
			} else {
				cart.put(cardToAdd, 1);
			}
			
		} else if(params.containsKey("search")) {
			CardModel cards = new CardModel();
			List<ProductBean> products;
			
			try {
				products = cards.search(request.getParameter("search"));
				request.setAttribute("products", products);
				for (ProductBean b:products)
					System.out.println(b.getName() + " Price: " + b.getCost());
			} catch (SQLException e) {
				e.printStackTrace();
			}
						
			String target = "/home.jspx";
			request.getRequestDispatcher(target).forward(request, response);
		
		}else {
			CardModel cards = new CardModel();
			List<ProductBean> products;
			
			try {
				products = cards.retrieveCards();
				request.setAttribute("products", products);
				for (ProductBean b:products)
					System.out.println(b.getName() + " Price: " + b.getCost());
			} catch (SQLException e) {
				e.printStackTrace();
			}
						
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
