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

@WebServlet("/users/details")
public class DetailsClientServlet extends HttpServlet {
	ClientService clientservice = ClientService.getInstance();
	
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		RequestDispatcher dispatcher = request.getRequestDispatcher(
				"/WEB-INF/views/users/details.jsp");
		
		int id_client = Integer.parseInt(request.getParameter("id"));	
		System.out.println("Id : " + id_client);
		
		 try {
			 System.out.println("try again");
			 request.setAttribute("nomUtilisateur", clientservice.findById(id_client));
		 } catch (ServiceException e) {
			 request.setAttribute("nomUtilisateur", "Erreur lors de la recherche du client");
		 }
		 dispatcher.forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		doGet(request, response);
	}
}