package controler.vehicules;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ensta.rentmanager.exception.ServiceException;
import com.ensta.rentmanager.model.Reservation;
import com.ensta.rentmanager.service.ClientService;
import com.ensta.rentmanager.service.ReservationService;
import com.ensta.rentmanager.service.VehiculeService;

/**
 * Servlet implementation class DeleteVehiculeServlet
 */
@WebServlet("/cars/delete")
public class DeleteVehiculeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ReservationService reservationservice = ReservationService.getInstance();
	ClientService clientservice = ClientService.getInstance();
	VehiculeService vehiculeservice = VehiculeService.getInstance();
       
    /**
     * @throws IOException 
     * @see HttpServlet#HttpServlet()
     */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher(
				"/WEB-INF/views/vehicles/deleteUsers.jsp");
		int id_vehicule = Integer.parseInt(request.getParameter("id"));	
		
		try {	
			vehiculeservice.delete(id_vehicule);
			System.out.println("Delete vehicule " + String.valueOf(id_vehicule));
			List<Reservation> listreservations = reservationservice.findResaByVehiculeId(id_vehicule);
			 for (Reservation rent: listreservations) {
				 reservationservice.delete(rent.getId());
			 }
		} catch (ServiceException e) {
				e.printStackTrace();
		}
		response.sendRedirect("/RentManager/cars");	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}


}
