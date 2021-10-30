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


public class ServletHome extends HttpServlet {
	/**
	 * Class servletHome used in accordance with doPost method as it works based on reqests and responses, as this class specifically
	 * is used for displaying all vehicle records.
	 * @author Muhammad Suhaib 17026213
	 * @version 4.6
     */
	private static final long serialVersionUID = 1L;
	/**
	* doGet Method used to handle the pages GET requests and normally used to serve the pages 
	* with a form.
	* @param req used to request permission to get something or authorisation to go into server page
	* @param res used forward the servers response back to the user.
	*/
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
		try {	
			VehicleDAO dao = new VehicleDAO();//declare connection to the database
			ArrayList<Vehicle> allVehicles = dao.getAllVehicles(); //declare arraylist of vehicles table data
			RequestDispatcher rQ = req.getRequestDispatcher("index.jsp");  //request access into server resource index.jsp
			req.setAttribute("allVehicles", allVehicles); //set attributes of particular data set
			rQ.forward(req, res); //forwards requests from the client and sends responses to client from jsp files
		} catch (SQLException e) {	e.printStackTrace();	}
	}
}
