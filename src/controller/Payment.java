package controller;

import java.io.IOException;
import java.util.Random;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.UserBean;
import model.UserModel;

/**
 * Servlet implementation class Payment
 */
@WebServlet({"/payment", "/payment/*"})
public class Payment extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ServletContext context;
	private int succeededRequests;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Payment() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    @Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		succeededRequests = 0;
		context = getServletContext();
		// Instantiate Loan object
		try {
			UserModel userModel = new UserModel();
			context.setAttribute("userModel", userModel);
		} catch (Exception e) {
			// set error?
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String address = request.getParameter("address");
		String city = request.getParameter("city");
		String province = request.getParameter("province");
		String postal = request.getParameter("postal");
		String cardNumber = request.getParameter("cardNumber");
		String securityCode = request.getParameter("securityCode");
		String paymentString = request.getParameter("payment");
		
		boolean payment = paymentString != null && paymentString.equals("true");
		
		UserBean loggedInUser = (UserBean) request.getSession().getAttribute("loggedInUser");
		
		if (loggedInUser == null) {
			// User not logged in. Redirect to login page.
			response.sendRedirect("/EECS4413_Project/login");
			return;
		} else if (payment) {
			UserModel userModel = (UserModel) this.context.getAttribute("userModel");
			
			// fail every third request
			if (succeededRequests >= 2) {
				// failed
				succeededRequests = 0;
				request.setAttribute("message", "Credit Card Authorization Failed");
			} else {
				// passed
				// update address data
				try {
					userModel.updateAddress(firstName, lastName, address, city, province, postal);
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				// update order data
				request.setAttribute("message", "Order Successfully Completed");
			}
			String target = "/results.jspx";
			request.getRequestDispatcher(target).forward(request, response);
			return;
		} else {
			// get default payment info
			request.setAttribute("firstName", loggedInUser.getFirstName());
			request.setAttribute("lastName", loggedInUser.getLastName());
			request.setAttribute("address", loggedInUser.getAddress());
			request.setAttribute("city", loggedInUser.getCity());
			request.setAttribute("province", loggedInUser.getProvince());
			request.setAttribute("postal", loggedInUser.getPostal());
		}
		
		String target = "/payment.jspx";
		request.getRequestDispatcher(target).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
