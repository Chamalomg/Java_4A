package controler.users;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import com.ensta.rentmanager.exception.ServiceException;
import com.ensta.rentmanager.model.Client;
import com.ensta.rentmanager.service.ClientService;

@WebServlet("/users/create")
public class AddClientServlet extends HttpServlet {
	ClientService clientService = ClientService.getInstance();
	
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletRequest response) throws ServiceException, IOException, ServletException {
		RequestDispatcher dispatcher = request.getRequestDispatcher(
				"WEB-INF/views/users/create.jsp");
		try {
			request.setAttribute("users", clientService.findAll());
		} catch (ServiceException e) {
				request.setAttribute("nbUtilisateur", "Une erreur est survenue");
		}
		dispatcher.forward(request, (ServletResponse) response);
				
	}
	protected void doPost(HttpServletRequest request, HttpServletRequest response) {
		String last_name = request.getParameter("last_name");
		String first_name = request.getParameter("first_name");
		String email = request.getParameter("email");
		Date birthdate = Date.valueOf(request.getParameter("birthdate"));
		
		Client newClient = new Client();
		newClient.setNom(last_name);
		newClient.setPrenom(first_name);
		newClient.setEmail(email);
		newClient.setNaissance(birthdate);
		RequestDispatcher dispatcher;
		try {
			clientService.create(newClient);
			dispatcher = request.getRequestDispatcher("WEB-INF/views/users/home.jsp");
		} catch (ServiceException e) {
			dispatcher = request.getRequestDispatcher("WEB-INF/views/users/create.jsp");
		}
	}
}