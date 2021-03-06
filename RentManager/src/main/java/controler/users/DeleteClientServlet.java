package controler.users;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ensta.rentmanager.exception.ServiceException;
import com.ensta.rentmanager.model.Reservation;
import com.ensta.rentmanager.service.ClientService;
import com.ensta.rentmanager.service.ReservationService;

/**
 * Servlet implementation class DeleteClientServlet
 */
@WebServlet("/users/delete")
public class DeleteClientServlet extends HttpServlet {
	ReservationService reservationservice = ReservationService.getInstance();
	ClientService clientservice = ClientService.getInstance();
	
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		RequestDispatcher dispatcher = request.getRequestDispatcher(
				"/WEB-INF/views/users/deleteUsers.jsp");
		int id_client = Integer.parseInt(request.getParameter("id"));	
		
		try {	
			clientservice.delete(id_client);
			System.out.println("Delete client " + String.valueOf(id_client));
			List<Reservation> listreservations = reservationservice.findResaByClientId(id_client);
			 for (Reservation rent: listreservations) {
				 reservationservice.delete(rent.getId());
			 }
		} catch (ServiceException e) {
				e.printStackTrace();
		}
		response.sendRedirect("/RentManager/users");	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}