package servlets;

import java.io.IOException;

import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.VehicleDAO;

public class ServletDeleteVehicle extends HttpServlet{
	/**
	 * Class ServletDeleteVehicle used in accordance with methods doPost and doGet as it works based on reqests and responses, as this class
	 * specifically is used for deleting a specific vehicle from the database and also the web server. (FRONT-END)
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
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
			RequestDispatcher view = req.getRequestDispatcher("delete.jsp");   //request access into server resource delete.jsp
			view.forward(req, res); //forwards requests from the client and sends responses to client from jsp files
	}

	/**
	* doPost Method used to handle the pages POST requests and utilized to process information,
	* Also essential when needing to submit newly added vehicles into the vehicle database
	* @param req used to request permission to get something or authorisation to go into server page
	* @param res used forward the servers response back to the user.
	*/
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		VehicleDAO dao = new VehicleDAO();//declare connection to the database
		try {
			int vehicle_id = Integer.valueOf(req.getParameter("id")); //request for vehicle id number
			boolean complete = dao.deleteVehicle(vehicle_id); //implement database connection to deleting the vehicle based on id
			System.out.println(complete); //confirmation of action command in console
			if (complete) {	res.sendRedirect("home");	} //if true then redirect user to home page.
		} catch (SQLException e)  {	e.printStackTrace();	}
	}
}
