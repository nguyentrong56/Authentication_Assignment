package control;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.dataccess.LoginDataAccess;
import model.entities.MessageException;
import model.entities.User;

@SuppressWarnings("serial")
public class LoginControl extends HttpServlet {
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String address = "";
		
		try {
			
			String userName = request.getParameter("username");
			String password = request.getParameter("password");
			
			if (userName.equals("")) {
				throw new MessageException("Username not informed.");
			} else if (password.equals("")) {
				throw new MessageException("Password not informed.");
			} 
			
			User user = new User(userName, password);
				
			if (!(new LoginDataAccess().verifyCredentials(user))) {
				throw new MessageException("Incorrect credentials.");
			} else {
				request.setAttribute("Username", request.getParameter("username"));
				address = "/view/LoginSuccessView.jsp";
			}

		} catch (MessageException e) {
			if (e.getMessage().equals("Username not informed.")) {
				request.setAttribute("ErrorLogin", "Username not informed.");
				address = "/view/LoginView.jsp";
			} else if (e.getMessage().equals("Password not informed.")) {
				request.setAttribute("ErrorLogin", "Password not informed.");
				address = "/view/LoginView.jsp";	
			} else if (e.getMessage().equals("Incorrect credentials.")) {
				request.setAttribute("ErrorLogin", "Incorrect credentials.");
				address = "/view/LoginView.jsp";	
		    }
		} catch (ClassNotFoundException e) {
			request.setAttribute("ErrorLogin", "Database connection failed.");
			address = "/view/LoginView.jsp";
		} catch (SQLException e) {
			request.setAttribute("ErrorLogin", "Database connection failed.");
			address = "/view/LoginView.jsp";
		}
		
	    RequestDispatcher rd = request.getRequestDispatcher(address);
		rd.forward(request, response);

	}
	
}
