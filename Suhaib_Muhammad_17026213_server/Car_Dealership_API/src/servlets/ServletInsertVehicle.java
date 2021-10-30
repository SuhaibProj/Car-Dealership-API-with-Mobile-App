package servlets;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Vehicle;
import models.VehicleDAO;

public class ServletInsertVehicle extends HttpServlet{
	/**
	 * Class ServletInsertVehicle used in accordance with methods doPost and doGet as it works based on reqests and responses, as this class
	 * specifically is used for inserting a new vehicle into the database and into the web server. (FRONT-END)
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
		RequestDispatcher view = req.getRequestDispatcher("create.jsp");  //request access into server resource create.jsp
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
		
		int vehicle_id = Integer.valueOf(req.getParameter("vehicle_id")); //request for vehicle id number
		String make = (String) req.getParameter("make"); //request for vehicle make 
		String model = (String) req.getParameter("model"); //request for vehicle model 
		int year = Integer.valueOf(req.getParameter("year")); //request for vehicle year 
		int price = Integer.valueOf(req.getParameter("price"));//request for vehicle price 
		String license_number = (String) req.getParameter("license_number"); //request for vehicle license number
		String colour = (String) req.getParameter("colour"); //request for  vehicle colour
		int number_doors = Integer.valueOf(req.getParameter("number_doors")); //request for vehicle door number
		String transmission = (String) req.getParameter("transmission");//request for vehicle transmission
		int mileage = Integer.valueOf(req.getParameter("mileage"));//request for vehicle mileage
		String fuel_type = (String) req.getParameter("fuel_type"); //request for vehicle fuel type
		int engine_size = Integer.valueOf(req.getParameter("engine_size")); //request for vehicle engine size
		String body_style = (String) req.getParameter("body_style"); //request for vehicle body style
		String condition = (String) req.getParameter("condition"); //request for vehicle condition
		String notes = (String) req.getParameter("notes"); //request for vehicle notes

		Vehicle v = new Vehicle(vehicle_id, make, model, year, price, license_number, colour, number_doors, 
				transmission, mileage, fuel_type, engine_size, body_style, condition, notes); //set variables into constructor
		try { 
			boolean complete = dao.insertVehicle(v); //implement database connection to inserting vehicle based on inputted vehicle data.
			System.out.println(complete); //confirmation of action command in console
			if (complete) {	res.sendRedirect("home"); } //if true then redirect user to home page.
		} catch (SQLException e) {	e.printStackTrace();	}
	}
}
