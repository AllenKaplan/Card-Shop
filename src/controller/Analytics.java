package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.CardBean;
import bean.ProductBean;
import bean.PurchaseHistoryBean;
import model.CardModel;
import model.PurchaseModel;

/**
 * Servlet implementation class CardShop
 */
@WebServlet({"/analytics", "/analytics/*"})
public class Analytics extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private CardModel cardModel = new CardModel();
	private PurchaseModel purchaseModel = new PurchaseModel();
	
	private String[] MonthArray = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Analytics() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			String target = "/analytics.jspx";
			
			try {
				List<ProductBean> cards = cardModel.retrieveCards();
				ArrayList<PurchaseHistoryBean> purchases;
				
				String year = request.getParameter("year");
				String month = request.getParameter("month");
				
				if(month == null && year == null) {
					purchases = purchaseModel.getPurchasesByMonth(3, 2019);
					request.setAttribute("month", "March");
				}
				
				else if(year == null) {
					int monthNumber = Integer.parseInt(month);
					
					purchases = purchaseModel.getPurchasesByMonth(monthNumber, 2020);
					request.setAttribute("month", MonthArray[monthNumber]);
				}
				
				else if(month == null) {
					purchases = purchaseModel.getPurchasesByMonth(1, Integer.parseInt(year));
					request.setAttribute("month", "January");
				}
				
				else {
					int monthNumber = Integer.parseInt(month);
					
					purchases = purchaseModel.getPurchasesByMonth(Integer.parseInt(month), Integer.parseInt(year));
					request.setAttribute("month", MonthArray[monthNumber]);
				}
				
				request.setAttribute("topTenCards", cards);
				request.setAttribute("monthPurchases", purchases);
				
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
