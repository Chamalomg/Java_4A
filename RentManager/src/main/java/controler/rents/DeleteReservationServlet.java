package controler.rents;

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
 * Servlet implementation class DeleteReservationServlet
 */
@WebServlet("/rents/delete")
public class DeleteReservationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ReservationService reservationservice = ReservationService.getInstance();
	ClientService clientservice = ClientService.getInstance();
	VehiculeService vehiculeservice = VehiculeService.getInstance();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher(
				"/WEB-INF/views/rents/deleteRents.jsp");
		int id_resa = Integer.parseInt(request.getParameter("id"));	
		
		try {	
			reservationservice.delete(id_resa);
			System.out.println("Delete reservation " + String.valueOf(id_resa));
		} catch (ServiceException e) {
				e.printStackTrace();
		}
		response.sendRedirect("/RentManager/rents");	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
