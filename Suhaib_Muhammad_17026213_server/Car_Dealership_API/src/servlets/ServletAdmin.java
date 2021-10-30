package servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Vehicle;
import models.VehicleDAO;

@SuppressWarnings("serial")
public class ServletAdmin extends HttpServlet {
	/**
	 * Class servletAdmin used in accordance with doPost method as it works based on reqests and responses, as this class specifically
	 * is used for logging into the server page, as username and password is set in this method and 
	 * if username and password is correct user is granted admission into admin mode. this class is also used for displaying all vehicle records.
	 * @author Muhammad Suhaib 17026213
	 * @version 4.6
     */
	
	/**
	 * doPost Method used to handle the pages POST requests and utilized to process information,
	 * @param req used to request permission to get something or authorisation to go into server page
	 * @param res used forward the servers response back to the user.
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		VehicleDAO dao = new VehicleDAO(); //declare connection to the database
		ArrayList<Vehicle> allVehicles = new ArrayList<Vehicle>(); //declare arraylist of vehicles table data
		String username = req.getParameter("username"); //parameter username used in accordance with html code
		String password = req.getParameter("password");	//parameter password used in accordance with html code
		if (username.equals("admin") && password.equals("kings123")) { //if username and password entered correctly
			try {	allVehicles = dao.getAllVehicles();	}  //implement connection to the database thorugh diplaying all vehicles
			catch (SQLException e) { e.printStackTrace(); }
			RequestDispatcher rQ = req.getRequestDispatcher("admin.jsp");  //request access into server resource admin.jsp
			req.setAttribute("allVehicles", allVehicles); //set attributes of particular data set
			rQ.forward(req, res); //forwards requests from the client and sends responses to client from jsp files
		}	else { res.sendRedirect("home");  } //redirect user to home page.
	}
	
}
