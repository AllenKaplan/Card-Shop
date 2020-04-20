package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.CardBean;
import bean.ProductBean;
import bean.PurchaseHistoryBean;
import bean.UserBean;
import bean.UserPurchasesBean;
import model.CardModel;
import model.PurchaseModel;

/**
 * Servlet implementation class CardShop
 */
@WebServlet({"/analytics", "/analytics/*"})
public class Analytics extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private String[] MonthArray = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};

	private CardModel cardModel;
	private PurchaseModel purchaseModel;
	
	private static final String CARDS_SOLD = "cardsSold";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Analytics() {
        super();
        try {
			purchaseModel = PurchaseModel.getInstance();
			cardModel = CardModel.getInstance();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
    }
    
    private int monthNumberConverter(String month) {

    	if(month.equals("January")) return 1;
    	else if(month.equals("February")) return 2;
    	else if(month.equals("March")) return 3;
    	else if(month.equals("April")) return 4;
    	else if(month.equals("May")) return 5;
    	else if(month.equals("June")) return 6;
    	else if(month.equals("July")) return 7;
    	else if(month.equals("August")) return 8;
    	else if(month.equals("September")) return 9;
    	else if(month.equals("October")) return 10;
    	else if(month.equals("November")) return 11;
    	else if(month.equals("December")) return 12;
    	else return -1;
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			// Check if admin, if not go to home
			UserBean loggedInUser = (UserBean) request.getSession().getAttribute("loggedInUser");
			if (loggedInUser == null || !loggedInUser.getAccountType().toUpperCase().equals("ADMINISTRATOR")) {
				ServletContext context = this.getServletContext();
				String redirectPath = context.getInitParameter("redirectPath");
				response.sendRedirect(redirectPath + "login");
				return;
			}
			
			String target = "/analytics.jspx";
			
			try {
				HashMap<Integer, Integer> cards = (HashMap<Integer, Integer>) request.getServletContext().getAttribute(CARDS_SOLD);
				List<UserPurchasesBean> users = purchaseModel.getPurchasesByUser();
				List<PurchaseHistoryBean> purchases;
				
				for(Integer i: cards.keySet()) System.out.println(i + ": " + cards.get(i));
				
				String year = request.getParameter("year");
				String month = request.getParameter("month");
				
				System.out.println("Year: " + year);
				System.out.println("Month: " + month);
				
				if(month == null && year == null) {
					purchases = purchaseModel.getPurchasesByMonth(3, 2019);
					request.setAttribute("month", "March");
				}
				
				else if(year == null) {
					int monthNumber = monthNumberConverter(month);
					
					purchases = purchaseModel.getPurchasesByMonth(monthNumber, 2020);
					request.setAttribute("month", month);
				}
				
				else if(month == null) {
					purchases = purchaseModel.getPurchasesByMonth(1, Integer.parseInt(year));
					request.setAttribute("month", "January");
				}
				
				else {
					int monthNumber = monthNumberConverter(month);
					
					purchases = purchaseModel.getPurchasesByMonth(monthNumber, Integer.parseInt(year));
					request.setAttribute("month", month);
				}
				
				request.setAttribute("topTenCards", cards);
				request.setAttribute("monthPurchases", purchases);
				request.setAttribute("users", users);
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			request.getRequestDispatcher(target).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
