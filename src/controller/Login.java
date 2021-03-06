package controller;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.DatatypeConverter;

import bean.UserBean;
import model.UserModel;

/**
 * Servlet implementation class Login
 */
@WebServlet({ "/login", "/login/*" })
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ServletContext context;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Login() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		context = getServletContext();
		// Instantiate Loan object
		try {
			UserModel userModel = UserModel.getInstance();
			context.setAttribute("userModel", userModel);
		} catch (Exception e) {
			// set error?
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String username = request.getParameter("username");
		String rawPassword = request.getParameter("password");
		String loginString = request.getParameter("login");
		String startRegisterString = request.getParameter("startRegister");
		String registerString = request.getParameter("register");

		boolean login = loginString != null && loginString.equals("true");
		boolean startRegister = startRegisterString != null && startRegisterString.equals("true");
		boolean register = registerString != null && registerString.equals("true");

		System.out.println(request.getRequestURI());
		System.out.println(username);
		System.out.println(rawPassword);
		System.out.println(loginString);

		if (login) {
			UserModel userModel = (UserModel) this.context.getAttribute("userModel");
			try {
			    // Hash password
			    MessageDigest md;
			    String password = "";
				try {
					md = MessageDigest.getInstance("MD5");
					md.update(rawPassword.getBytes());
				    byte[] digest = md.digest();
				    password = DatatypeConverter.printHexBinary(digest).toUpperCase();
				} catch (NoSuchAlgorithmException e1) {
					e1.printStackTrace();
				}
			    
				System.out.println("Hashed pass: " + password);
				
				UserBean loggedInUser = userModel.login(username, password);
				if (loggedInUser != null) {
					// On successful login, set accountType
					request.getSession().setAttribute("loggedInUser", loggedInUser);

					ServletContext context = this.getServletContext();
					String redirectPath = context.getInitParameter("redirectPath");
					response.sendRedirect(redirectPath + "payment");
					return;
				} else {
					// login failed
					throw new Exception("No account associated with that username and password");
				}
			} catch (Exception e) {
				e.printStackTrace();
				// print error and go back to login
				request.setAttribute("error", "Login Failed: " + e.getMessage());
			}
		} else if (register) {
			System.out.println("Complete Registration");

			// Get data
			String firstName = request.getParameter("firstName");
			String lastName = request.getParameter("lastName");
			String address = request.getParameter("address");
			String city = request.getParameter("city");
			String province = request.getParameter("province");
			String postal = request.getParameter("postal");

			// Create account
			UserModel userModel = (UserModel) this.context.getAttribute("userModel");
			try {
				// Hash password
			    MessageDigest md;
			    String password = "";
				try {
					md = MessageDigest.getInstance("MD5");
					md.update(rawPassword.getBytes());
				    byte[] digest = md.digest();
				    password = DatatypeConverter.printHexBinary(digest).toUpperCase();
				} catch (NoSuchAlgorithmException e1) {
					e1.printStackTrace();
				}
			    
				System.out.println("Hashed pass: " + password);
				
				userModel.register(username, password, firstName, lastName, address, city, province, postal);
			} catch (Exception e) {
				e.printStackTrace();
				// print error and go back to registration
				request.setAttribute("error", "Registration Failed: " + e.getMessage());
				String target = "/register.jspx";
				request.getRequestDispatcher(target).forward(request, response);
				return;
			}
		} else if (startRegister) {
			System.out.println("Start registration");
			String target = "/register.jspx";
			request.getRequestDispatcher(target).forward(request, response);
			return;
		}

		String target = "/login.jspx";
		request.getRequestDispatcher(target).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
