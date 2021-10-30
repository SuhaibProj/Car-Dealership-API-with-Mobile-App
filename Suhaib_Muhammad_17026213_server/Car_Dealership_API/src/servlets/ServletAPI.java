package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import models.Vehicle;
import models.VehicleDAO;

import java.util.*;
/**
 * Class is created for initializing all values (variables) from the vehicleDAO class and utilize 
 * within the Vehicle jetty server in the database to be utilized within an api
 * also this class provides the user to perform CRUD functionality, where I create,
 * retrieve, update and delete data within my android studio application
 * 
 * @author Muhammad Suhaib 17026213
 * @version 5
 */
public class ServletAPI extends HttpServlet {
	
	public static final long serialVersionUID = 1L; //verify classes are compatible with regards to serialization.
	
	VehicleDAO dao = new VehicleDAO(); //instantiate Vehicle DataBase Access Object class
	Gson gson = new Gson(); //declared new Gson variable, used to convert vehicle data into JSON representation
	PrintWriter writer; //used to write formatted data.
	
	/**
	 * This function is utilized to get all the vehicles from the database through accessing the vehicle dao class, 
	 * and then convert the vehicles into JSON format.
	 * 
	 * Do a GET Request (get Vehicle details from request parameters and get vehicle from DataBase)
	 * 
	 * @param req gets the request for the http server
	 * @param resp gets the response from the http server
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try { //attempt operation
			ArrayList<Vehicle> vehicles = dao.getAllVehicles(); //use teh vehicle dao class to grab all the vehicles and their corresponding data into this array
			resp.setContentType("application/json"); //set the content outputted to a json application
			writer = resp.getWriter();//get response from the print writer
			String vehJSON = gson.toJson(vehicles); //convert vehicle data into json formatting
			writer.write(vehJSON); //output all the JSON converted vehicles
			writer.close(); //close the print writer.
		} catch (SQLException e) { e.printStackTrace(); } //print sql exceptions to the console.
	}
	
	/**
	 * This function is utilized to get insert a vehicle through accessing the vehicle dao class, 
	 * vehicle data inputted into the database and then convert it to JSON format.
	 * It also error handles to the console as to whether vehicle was inserted or not.
	 * 
	 * Do a POST Request (get Vehicle details from request parameters and INSERT vehicle into DataBase)
	 * 
	 * @param req gets the request for the http server
	 * @param resp gets the response from the http server
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException { 
		writer = resp.getWriter(); //get response from the print writer
		resp.setContentType("text/html;charset=UTF-8"); //encode response in appropriate server format
		String Vdata = req.getParameter("Vehicle"); //request the vehicle data from the Vehicle class
		Vehicle vehicle = gson.fromJson(Vdata, Vehicle.class); //convert vehicle data into json formatting
		boolean inserted = false; //check vehicle is not inserted initially
		try { inserted = dao.insertVehicle(vehicle); } //get the vehicle insert function form the vehicle dao class
		catch (SQLException e) { e.printStackTrace(); }//output any exception to the console.
		if (inserted) {	writer.write("New Vehicle Inserted"); } //show when vehicle has been inserted
		else { writer.write("Error: Vehicle could not be inserted"); }//if unsuccessful, show vehicle hasnt been inserted
		writer.close();//close the print writer
	}
	
	/**
	 * This function is utilized to get update a vehicle through accessing the vehicle dao class, 
	 * vehicle data modified in the database and then convert it to JSON format. 
	 * It also error handles to the console as to whether vehicle was updated or not.
	 * 
	 * Do a PUT Request (get Vehicle details from request parameters and UPDATE vehicle into DataBase)
	 * 
	 * @param req gets the request for the http server
	 * @param resp gets the response from the http server
	 */
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException { 
		writer = resp.getWriter(); //get response from the print writer
		resp.setContentType("text/html;charset=UTF-8"); //encode response in appropriate server format
		String Vdata = req.getParameter("Vehicle");	//get vehicle details from the request
		Vehicle vehicle = gson.fromJson(Vdata, Vehicle.class); //convert vehicle data into json formatting
		boolean updated = false; //check vehicle is not updated initially
		try { updated = dao.updateVehicle(vehicle, vehicle.getVehicle_id()); } //get the vehicle update function form the vehicle dao class
		catch (SQLException e) { e.printStackTrace(); }//output any exception to the console.
		if (updated) { writer.write("Vehicle Updated"); } //show when vehicle has been updated
		else { writer.write("Error: Vehicle could not be updated"); }//if unsuccessful, show vehicle hasnt been updated
		writer.close();//close the print writer
	}
	
	/**
	 * This function is utilized to delete a vehicle through accessing the vehicle dao class,
	 * via the vehicle id number in the database and then convert it to JSON format. 
	 * It also error handles to the console as to whether vehicle was deleted or not.
	 * 
	 * Do a DELETE Request (get Vehicle id from request parameters and DELETE vehicle from DataBase)
	 * 
	 * @param req gets the request for the http server
	 * @param resp gets the response from the http server
	 */
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int vehicle_id = Integer.valueOf(req.getParameter("Vehicle_id")); //get the vehicle id to be changed in URL
		boolean deleted = false; //check vehicle is not deleted initially
		try { deleted = dao.deleteVehicle(vehicle_id); } //get the vehicle delete funciton form the vehicle dao class
		catch (SQLException e) { e.printStackTrace(); } //output any exception to the console.
		if (deleted) { writer.write("Vehicle Deleted"); }  //show when vehicle has been deleted
		else { writer.write("Error: Vehicle could not be deleted"); } //if unsuccessful, show vehicle hasnt been deleted
		writer.close(); //close the print writer
	} 
	
	
}
