package controler.vehicules;

import java.io.IOException;

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

@WebServlet("/cars")
public class ListVehiculeServlet extends HttpServlet {
	VehiculeService vehiculeservice = VehiculeService.getInstance();
	
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		RequestDispatcher dispatcher = request.getRequestDispatcher(
				"/WEB-INF/views/vehicles/listVehicules.jsp");
		try {
			/*for (Vehicule car: vehiculeservice.findAll()) {
				System.out.println(car.toString());
			}*/
			
			//Renvoie la liste de vehicules à la vue list.jsp
			request.setAttribute("vehicles", vehiculeservice.findAll());
		} catch (ServiceException e) {
			request.setAttribute("vehicles", "Une erreur est survenue");
		}
		dispatcher.forward(request, response);		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}


