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

public class ServletUpdateVehicle extends HttpServlet{
	/**
	 * Class ServletUpdateVehicle used in accordance with methods doPost and doGet as it works based on reqests and responses, as this class
	 * specifically is used for updating an existing vehicle in the database and also the web server. (FRONT-END)
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
		RequestDispatcher view = req.getRequestDispatcher("update.jsp");  //request access into server resource update.jsp
		view.forward(req, res); //forwards requests from the client and sends responses to client from jsp files
	}

	/**
	* doPost Method used to handle the pages POST requests and utilized to process information,
	* Also essential when needing to submit newly updated vehicle data into the vehicle database
	* @param req used to request permission to get something or authorisation to go into server page
	* @param res used forward the servers response back to the user.
	*/
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
			VehicleDAO dao = new VehicleDAO();//declare connection to the database
			try {
				Vehicle v = new Vehicle(); //declare instance of Vehicle class
				System.out.println(v);
				//request for vehicle id number
				int vehicle_id = Integer.valueOf(req.getParameter("vehicle_id"));
				//request for vehicle make and set vehicle make in vehicle class
				String make = (String) req.getParameter("make");
				v.setMake(make);
				//request for vehicle model and set vehicle model in vehicle class
				String model = (String) req.getParameter("model");
				v.setModel(model);
				//request for vehicle year and set vehicle year in vehicle class
				int year = Integer.valueOf(req.getParameter("year"));
				v.setYear(year);
				//request for vehicle price and set vehicle price in vehicle class
				int price = Integer.valueOf(req.getParameter("price"));
				v.setPrice(price);
				//request for vehicle license and set vehicle license in vehicle class
				String license_number = (String) req.getParameter("license_number");
				v.setLicense(license_number);
				//request for vehicle colour and set vehicle colour in vehicle class
				String colour = (String) req.getParameter("colour");
				v.setColour(colour);
				//request for vehicle door number and set vehicle door number in vehicle class
				int number_doors = Integer.valueOf(req.getParameter("number_doors"));
				v.setDoors(number_doors);
				//request for vehicle transmission and set vehicle transmission in vehicle class
				String transmission = (String) req.getParameter("transmission");
				v.setTransmission(transmission);
				//request for vehicle mileage and set vehicle mileage in vehicle class
				int mileage = Integer.valueOf(req.getParameter("mileage"));
				v.setMileage(mileage);
				//request for vehicle fuel and set vehicle fuel in vehicle class
				String fuel_type = (String) req.getParameter("fuel_type");
				v.setFuel(fuel_type);
				//request for vehicle engine size and set vehicle engine size in vehicle class
				int engine_size = Integer.valueOf(req.getParameter("engine_size"));
				v.setEngine(engine_size);
				//request for vehicle body style and set vehicle body style in vehicle class
				String body_style = (String) req.getParameter("body_style");
				v.setBody(body_style);
				//request for vehicle condition and set vehicle condition in vehicle class
				String condition = (String) req.getParameter("condition");
				v.setCondition(condition);
				//request for vehicle notes and set vehicle notes in vehicle class
				String notes = (String) req.getParameter("notes");
				v.setNotes(notes);
				
				boolean complete = dao.updateVehicle(v,vehicle_id); //implement database connection to updating the vehicle based on id
				System.out.println(complete); //confirmation of action command in console
				if (complete) {	res.sendRedirect("home");	} //if true then redirect user to home page.
			} catch (SQLException e) {	e.printStackTrace();	}
		
	}
}
