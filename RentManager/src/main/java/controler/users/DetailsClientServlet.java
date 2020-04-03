package controler.users;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
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
import com.ensta.rentmanager.model.Vehicule;
import com.ensta.rentmanager.service.ClientService;
import com.ensta.rentmanager.service.ReservationService;
import com.ensta.rentmanager.service.VehiculeService;

@WebServlet("/users/details")
public class DetailsClientServlet extends HttpServlet {
	ReservationService reservationservice = ReservationService.getInstance();
	ClientService clientservice = ClientService.getInstance();
	VehiculeService vehiculeservice = VehiculeService.getInstance();
	
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		RequestDispatcher dispatcher = request.getRequestDispatcher(
				"/WEB-INF/views/users/detailsUsers.jsp");
		
		int id_client = Integer.parseInt(request.getParameter("id"));	
		System.out.println("Id : " + id_client);
		/*for (Client user: clientservice.findAll()) {
			System.out.println(user.toString());
		}*/
		 try {
			 Client client = clientservice.findById(id_client);
			 request.setAttribute("nom", client.getNom());
			 request.setAttribute("prenom", client.getPrenom());
			 request.setAttribute("mail", client.getEmail());
			 
			 int nbVoitures = reservationservice.findResaByClientId(id_client).size();
			 int nbReservations = reservationservice.findResaByClientId(id_client).size();
			 request.setAttribute("nbVoitures", nbVoitures);
			 request.setAttribute("nbReservations", nbReservations);
			 
			 
			 
			 List<Reservation> listreservations = reservationservice.findResaByClientId(id_client);
			 List <Vehicule> listvehicules = new ArrayList<>();;
			 for (Reservation rent: listreservations) {
				 //if (!listvehicules.contains(vehiculeservice.findById(rent.getId_vehicule()))) {
				 listvehicules.add(vehiculeservice.findById(rent.getId_vehicule()));
				 //}
			 }
			for (Reservation resa: listreservations) {
				System.out.println("Reservation : " + resa.toString());
			}
			for (Vehicule veh: listvehicules) {
				System.out.println("Vehicule : " + veh.toString());
			}
			 request.setAttribute("vehicules", listvehicules);
			 request.setAttribute("rents", listreservations);
			 
			 
		 } catch (ServiceException e) {
			 request.setAttribute("nomUtilisateur", "Erreur lors de la recherche du client");
		 }
		 dispatcher.forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		doGet(request, response);
	}
}