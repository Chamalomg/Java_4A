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
	VehiculeService vehiculeservice = VehiculeService.getInstance();

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/vehicles/createVehicule.jsp");
		dispatcher.forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String modele = request.getParameter("modele");
		String constructeur = request.getParameter("manufacturer");
		String Nb_places = request.getParameter("seats");
		int nplaces = Integer.parseInt(Nb_places);
		
		Vehicule newVehicule = new Vehicule();
		newVehicule.setModele(modele);
		newVehicule.setConstructeur(constructeur);
		newVehicule.setNb_places((byte) nplaces);

		RequestDispatcher dispatcher;
		try {
			vehiculeservice.create(newVehicule);
			dispatcher = request.getRequestDispatcher("/WEB-INF/views/vehicle/createVehicule.jsp");
		} catch (ServiceException e) {
			dispatcher = request.getRequestDispatcher("/WEB-INF/views/vehicle/createVehicule.jsp");
		}
		dispatcher.forward(request, response);
	}
}