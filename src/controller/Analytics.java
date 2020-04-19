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
import model.CardModel;

/**
 * Servlet implementation class CardShop
 */
@WebServlet({"/analytics", "/analytics/*"})
public class Analytics extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private CardModel cardModel = new CardModel();
       
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
				
				System.out.println("Start of Cards:");
				for(ProductBean i: cards) {
					System.out.println(i.getName() + " " + i.getCost() + " " + i.getImg() + " " + i.getDescription());
				}
				System.out.println("End of Cards");
				
				request.setAttribute("topTenCards", cards);
				
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
