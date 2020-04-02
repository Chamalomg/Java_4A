package controler.vehicules;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ensta.rentmanager.exception.ServiceException;
import com.ensta.rentmanager.model.Client;
import com.ensta.rentmanager.model.Vehicule;
import com.ensta.rentmanager.service.ClientService;
import com.ensta.rentmanager.service.ReservationService;
import com.ensta.rentmanager.service.VehiculeService;

@WebServlet("/cars/details")
public class DetailsVehiculeServlet extends HttpServlet {
	
	ReservationService reservationservice = ReservationService.getInstance();
	ClientService clientservice = ClientService.getInstance();
	VehiculeService vehiculeservice = VehiculeService.getInstance();
	
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		RequestDispatcher dispatcher = request.getRequestDispatcher(
				"/WEB-INF/views/vehicles/detailsVehicle.jsp");
		
		int id_vehicule = Integer.parseInt(request.getParameter("id"));	
		System.out.println("Id : " + id_vehicule);
		
		 try {
			 Vehicule vehicule = vehiculeservice.findById(id_vehicule);
			 request.setAttribute("constructeur", vehicule.getConstructeur());
			 request.setAttribute("nb_places", vehicule.getNb_places());
			 request.setAttribute("modele", vehicule.getModele());
		 } catch (ServiceException e) {
			 request.setAttribute("nomUtilisateur", "Erreur lors de la recherche du client");
		 }
		 //dispatcher.forward(request, response);
	
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		doGet(request, response);
	}
}