package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
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
		Map<ProductBean, Integer> cart = (HashMap<ProductBean, Integer>) request.getSession().getAttribute("cart");
		if (cart == null) {
			cart = new HashMap<ProductBean, Integer>();
		}
		
		if(request.getRequestURI().contains("cart")) {
			System.out.println("GET | HOME -> CART");
			response.getWriter().append("This is your shopping cart\n");
			response.getWriter().append(cart.toString());

			request.setAttribute("products", cart);
			
			String target = "/cart.jspx";
			request.getRequestDispatcher(target).forward(request, response);
		} else if(params.containsKey("review")) {
			System.out.println("GET | HOME -> REVIEW");
			response.getWriter().append("Review added:\n");
			response.getWriter().append(request.getParameter("review"));
		} else if(params.containsKey("addToCart") && request.getParameter("addToCart") != null) {
			System.out.println("GET | HOME -> ADD TO CART");
			
			int id = Integer.parseInt(request.getParameter("addToCart"));
			
			ProductBean cardToAdd;
			try {
				cardToAdd = CardModel.getInstance().retrieveCardByID(id);

				if (cart.containsKey(cardToAdd)) {
					int currentQnty = cart.get(cardToAdd);
					cart.put(cardToAdd, currentQnty+1);
				} else {
					cart.put(cardToAdd, 1);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
					

			request.setAttribute("products", cart);
			
			String target = "/cart.jspx";
			request.getRequestDispatcher(target).forward(request, response);
		} else if(params.containsKey("search") && request.getParameter("search") != null) {
			System.out.println("GET | HOME -> SEARCH");
			List<ProductBean> products;
			
			try {
				products = CardModel.getInstance().search(request.getParameter("query"));
				request.setAttribute("products", products);
				for (ProductBean b:products)
					System.out.println(b.getName() + " Price: " + b.getCost());
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
						
			String target = "/home.jspx";
			request.getRequestDispatcher(target).forward(request, response);
		
		}else {
			System.out.println("GET | HOME PAGE");
			List<ProductBean> products;
			
			try {
				products = CardModel.getInstance().retrieveCards();
				request.setAttribute("products", products);
				for (ProductBean b:products)
					System.out.println(b.getName() + " Price: " + b.getCost());
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
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
