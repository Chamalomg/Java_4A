package controler.users;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ensta.rentmanager.exception.ServiceException;
import com.ensta.rentmanager.model.Client;
import com.ensta.rentmanager.model.Reservation;
import com.ensta.rentmanager.service.ClientService;
import com.ensta.rentmanager.service.ReservationService;

@WebServlet("/users/details")
public class DetailsClientServlet extends HttpServlet {
	ReservationService reservationservice = ReservationService.getInstance();
	ClientService clientservice = ClientService.getInstance();
	
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		RequestDispatcher dispatcher = request.getRequestDispatcher(
				"/WEB-INF/views/users/details.jsp");
		
		int id_client = Integer.parseInt(request.getParameter("id"));	
		System.out.println("Id : " + id_client);
		
		 try {
			 Client client = clientservice.findById(id_client);
			 request.setAttribute("nom", client.getNom());
			 request.setAttribute("prenom", client.getPrenom());
			 request.setAttribute("mail", client.getEmail());
		 } catch (ServiceException e) {
			 request.setAttribute("nomUtilisateur", "Erreur lors de la recherche du client");
		 }
		 dispatcher.forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		doGet(request, response);
	}
}