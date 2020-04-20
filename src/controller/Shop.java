package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.ProductBean;
import model.CardModel;

/**
 * Servlet implementation class CardShop
 */
@WebServlet({"/home", "/home/*"})
public class Shop extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CardModel cardModel;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Shop() {
        super();
        try {
			cardModel = CardModel.getInstance();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletContext context = this.getServletContext();
		String redirectPath = context.getInitParameter("redirectPath");
		request.setAttribute("redirectPath", redirectPath);
		
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

			request.getSession().setAttribute("cart", cart);

			String target = "/cart.jspx";
			request.getRequestDispatcher(target).forward(request, response);
		} else if(params.containsKey("review")) {
				System.out.println("GET | HOME -> REVIEW");
				String target = "/review.jspx";
				request.getRequestDispatcher(target).forward(request, response);
		}else if(params.containsKey("submitReview") && request.getParameter("submitReview") != null) {
					System.out.println("GET | REVIEW -> SUBMIT");
					response.getWriter().append("Review added:\n");
					response.getWriter().append(request.getParameter("reviewRating\n"));
					response.getWriter().append(request.getParameter("reviewContent"));
		} else if(params.containsKey("addToCart") && request.getParameter("addToCart") != null) {
			System.out.println("GET | HOME -> ADD TO CART");
			
			int id = Integer.parseInt(request.getParameter("addToCart"));
			
			ProductBean cardToAdd;
			try {
				cardToAdd = cardModel.retrieveCardByID(id);
				
				boolean cardExists = cart.keySet().stream().filter(k -> k.getName().equals(cardToAdd.getName())).count() == 1;
					
				
//				if (cart.containsKey(cardToAdd)) {
				if (cardExists) {
					ProductBean card = (ProductBean) cart.keySet().stream().filter(k -> k.getName().equals(cardToAdd.getName())).toArray()[0];
					int currentQnty = cart.get(card);
					cart.put(card, currentQnty+1);
				} else {
					cart.put(cardToAdd, 1);

				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			System.out.println("This is the shopping cart");
			cart.forEach((k,v) -> System.out.println(k.getName() + " - " + v));
					
			request.getSession().setAttribute("cart", cart);
			
			getCards(request);
			String target = "/home.jspx";
			request.getRequestDispatcher(target).forward(request, response);	
		} else if(params.containsKey("search") && request.getParameter("search") != null) {
			System.out.println("GET | HOME -> SEARCH");
			List<ProductBean> products;
			
			try {
				products = cardModel.search(request.getParameter("query"));
				request.setAttribute("products", products);
//				for (ProductBean b:products)
//					System.out.println(b.getName() + " Price: " + b.getCost());
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			String target = "/home.jspx";
			request.getRequestDispatcher(target).forward(request, response);
		
		} else if (params.containsKey("checkout") && request.getParameter("checkout") != null) {
			response.sendRedirect(redirectPath + "payment");
		} else if (params.containsKey("toCart") && request.getParameter("toCart") != null) {
			String target = "/cart.jspx";
			request.getRequestDispatcher(target).forward(request, response);
		} else {
			System.out.println("GET | HOME PAGE");
			getCards(request);
						
			String target = "/home.jspx";
			request.getRequestDispatcher(target).forward(request, response);
		}
	}

	private void getCards(HttpServletRequest request) {
		List<ProductBean> products;
		
		try {
			products = cardModel.retrieveCards();
			request.setAttribute("products", products);
//			for (ProductBean b:products)
//				System.out.println(b.getName() + " Price: " + b.getCost());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
