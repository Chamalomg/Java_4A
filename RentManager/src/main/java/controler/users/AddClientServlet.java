package controler.users;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ensta.rentmanager.exception.ServiceException;
import com.ensta.rentmanager.model.Client;
import com.ensta.rentmanager.service.ClientService;

@WebServlet("/users/create")
public class AddClientServlet extends HttpServlet {
	ClientService clientservice = ClientService.getInstance();
	
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		RequestDispatcher dispatcher = request.getRequestDispatcher(
				"/WEB-INF/views/users/createUser.jsp");
		dispatcher.forward(request, response); //ok
				
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
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
			//Nouveau client, et retourne sur la page d'accueil, si erreur : recommence
			clientservice.create(newClient);
			response.sendRedirect(request.getContextPath() + "/users");
		} catch (ServiceException e) {
			dispatcher = request.getRequestDispatcher("/WEB-INF/views/users/listUsers.jsp");
		}
		
		//dispatcher.forward(request, response);
		
	}
}