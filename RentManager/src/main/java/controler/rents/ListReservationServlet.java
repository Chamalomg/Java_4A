package controler.rents;

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
import com.ensta.rentmanager.service.ReservationService;

@WebServlet("/rents")
public class ListReservationServlet extends HttpServlet {
	ReservationService reservationservice = ReservationService.getInstance();
	
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		RequestDispatcher dispatcher = request.getRequestDispatcher(
				"/WEB-INF/views/rents/listRents.jsp");
		try {
			
			/*for (Reservation rent: reservationservice.findAll()) {
				System.out.println(rent.toString());
			}*/
			request.setAttribute("rents", reservationservice.findAll());
		} catch (ServiceException e) {
				request.setAttribute("rents", "Une erreur est survenue");
		}
		dispatcher.forward(request, response);		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
