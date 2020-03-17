package controller;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.UserModel;

/**
 * Servlet implementation class Login
 */
@WebServlet({"/login", "/login/*"})
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
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String loginString = request.getParameter("login");
		
		boolean login = loginString != null && loginString.equals("true");
		
		System.out.println(request.getRequestURI());
		System.out.println(username);
		System.out.println(password);
		System.out.println(loginString);
		
		if (login) {
			UserModel userModel = (UserModel) this.context.getAttribute("userModel");
			try {
				if (userModel.login(username, password)) {
					String previousPage = request.getParameter("previousPage");
					if (previousPage != null) {
						System.out.println("A");
						response.sendRedirect(previousPage);
					} else {
						System.out.println("A-");
						response.sendRedirect("/EECS4413_Project/home");
						return;
					}
					
				} else {
					// login failed
					System.out.println("Login failed");
				}
				
				return;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		String target = "/login.jspx";
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
