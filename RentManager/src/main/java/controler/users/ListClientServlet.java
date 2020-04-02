package controler.users;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ensta.rentmanager.exception.ServiceException;
import com.ensta.rentmanager.model.Client;
import com.ensta.rentmanager.service.ClientService;

@WebServlet("/users")
public class ListClientServlet extends HttpServlet {
	ClientService clientservice = ClientService.getInstance();
	
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		RequestDispatcher dispatcher = request.getRequestDispatcher(
				"WEB-INF/views/users/list.jsp");
		
		try {
			
			/*for (Client user: clientservice.findAll()) {
				System.out.println(user.toString());
			}*/
			//Renvoie la liste d'users à la vue list.jsp
			request.setAttribute("users", clientservice.findAll());
		} catch (ServiceException e) {
				request.setAttribute("users", "Une erreur est survenue");
		}
		dispatcher.forward(request, (ServletResponse) response);		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}


