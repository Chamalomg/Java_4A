package controler.vehicules;

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
import com.ensta.rentmanager.model.Vehicule;
import com.ensta.rentmanager.service.VehiculeService;

@WebServlet("/cars/create")
public class AddVehiculeServlet extends HttpServlet {
	VehiculeService vehiculeService = VehiculeService.getInstance();
	
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		RequestDispatcher dispatcher = request.getRequestDispatcher(
				"WEB-INF/views/vehicles/create.jsp");
		try {
			request.setAttribute("vehicules", vehiculeService.findAll());
		} catch (ServiceException e) {
				request.setAttribute("nbUtilisateur", "Une erreur est survenue");
		}
		dispatcher.forward(request, response);
				
	}
	protected void doPost(HttpServletRequest request, HttpServletRequest response) {
		String modele = request.getParameter("last_name");
		String constructeur = request.getParameter("first_name");
		int Nb_places = Integer.parseInt(request.getParameter("Nb_places"));
		
		Vehicule newVehicule = new Vehicule();
		newVehicule.setModele(modele);
		newVehicule.setConstructeur(constructeur);
		newVehicule.setNb_places((byte)Nb_places);
		RequestDispatcher dispatcher;
		try {
			vehiculeService.create(newVehicule);
			dispatcher = request.getRequestDispatcher("WEB-INF/views/users/home.jsp");
		} catch (ServiceException e) {
			dispatcher = request.getRequestDispatcher("WEB-INF/views/users/create.jsp");
		}
	}
}