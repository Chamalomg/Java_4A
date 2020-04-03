package controler.rents;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ensta.rentmanager.exception.ServiceException;
import com.ensta.rentmanager.model.Client;
import com.ensta.rentmanager.model.Reservation;
import com.ensta.rentmanager.service.ReservationService;

/**
 * Servlet implementation class AddReservationServlet
 */
@WebServlet("/rents/create")
public class AddReservationServlet extends HttpServlet {
	ReservationService reservationservice = ReservationService.getInstance();
	private static final long serialVersionUID = 1L;
       

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher(
				"/WEB-INF/views/rents/createRent.jsp");
		dispatcher.forward(request, response);
				
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String voiture = request.getParameter("last_name");
		String client = request.getParameter("first_name");
		Date debut = Date.valueOf(request.getParameter("begin"));
		Date fin = Date.valueOf(request.getParameter("end"));
		Date birthdate = Date.valueOf(request.getParameter("birthdate"));
		
		Reservation newReservation = new Reservation();
		newReservation.setId_client(1);
		newReservation.setId_vehicule(2);
		newReservation.setDebut(debut);
		newReservation.setFin(fin);
		
		RequestDispatcher dispatcher;
		try {
			//Nouveau client, et retourne sur la page d'accueil, si erreur : recommence
			reservationservice.create(newReservation);
			response.sendRedirect(request.getContextPath() + "/rents");
		} catch (ServiceException e) {
			dispatcher = request.getRequestDispatcher("/WEB-INF/views/users/listRents.jsp");
		}

	}

}
