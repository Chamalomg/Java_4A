package controler;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import com.ensta.rentmanager.exception.ServiceException;
import com.ensta.rentmanager.model.Client;
import com.ensta.rentmanager.service.ClientService;

public class UserDetailsServlet extends HttpServlet {
	ClientService clientservice = ClientService.getInstance();
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletRequest response) throws ServiceException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher(
				"WEB-INF/views/users/details.jsp");
		int id = Integer.parseInt(request.getParameter("id"));
		System.out.println("Id : " + id);
		 try {
			 Client newclient = new Client();
			 clientservice.findById(id);
			 request.setAttribute("nomUtilisateur", newclient.getNom());
		 } catch (ServiceException e) {
			 request.setAttribute("nomUtilisateur", "Erreur lors ");
		 }
	}
}
