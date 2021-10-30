//import allnecessary libraries
package models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
//import java.sql.Statement;
import java.util.ArrayList;

public class VehicleDAO {
	/** 
	 * Class which acts as a database connector, as its functions are to retrieve data, 
	 * get specfic data, delete data and update data.
	 * @author Muhammad Suhaib 17026213
	 * @version 4.6 
    **/
	
	Connection dbConnection = null; //connection to database
	ResultSet result = null; //used to execute sql commands
	
	/**
	 * Makes and returns a connection to the vehicle database
	 * through specifying driver used for connection.
	 * @return conn
	**/
    private static Connection getDBConnection() {
        Connection conn = null;
        try { Class.forName("org.sqlite.JDBC"); } 
        catch(ClassNotFoundException e) { System.out.println(e.getMessage()); }
        try { conn = DriverManager.getConnection("jdbc:sqlite:vehicles.sqlite"); } 
        catch (SQLException e) { System.out.println(e.getMessage()); }
        return conn;
    }
    /** 
	 * Gets all the vehicles from the vehicle table in the database.
	 * @return vehicles
	 * @throws SQLException
	**/ 
	public ArrayList<Vehicle> getAllVehicles() throws SQLException {
		System.out.println("\nRetrieving all vehicles ...");
		ArrayList<Vehicle> vehicles = new ArrayList<>(); //implement arraylist of vehicles table data
		try {
			dbConnection = getDBConnection(); //get or implement the connection for database
			PreparedStatement state = dbConnection.prepareStatement("SELECT * FROM vehicles;"); //pre-compiled statement for sql query
			System.out.println("SQL Query = " + "SELECT * FROM vehicles;"); //done so query is visible in console
			result = state.executeQuery();  //execute the query into this class
			while(result.next()) { //list and request all available records in the table corresponding to sql query
	            	int vehicle_id = result.getInt("vehicle_id");
	            	String make =result.getString("make");
	            	String model=result.getString("model");
	            	int year=result.getInt("year");
	            	int price=result.getInt("price");
	            	String license_number=result.getString("license_number");
	            	String colour=result.getString("colour");
	            	int number_doors=result.getInt("number_doors");
	            	String transmission=result.getString("transmission");
	            	int mileage=result.getInt("mileage");
	            	String fuel_type=result.getString("fuel_type");
	            	int engine_size=result.getInt("engine_size");
	            	String body_style=result.getString("body_style");
	            	String condition=result.getString("condition");
	            	String notes=result.getString("notes");
	                vehicles.add(new Vehicle(vehicle_id, make, model, year, price, license_number, colour, number_doors, 
	                		transmission, mileage, fuel_type, engine_size, body_style, condition, notes)); //set variables into constructor
	        }
		} finally {
			if (result != null) { result.close(); } //ensure query closes after use
			if (dbConnection != null) { dbConnection.close(); } //ensure database connection closes after use
		} return vehicles;
	}
	
	/** 
	 * Gets all the sales from the sales table in the database.
	 * @return salesList
	 * @throws SQLException
	**/ 
	public ArrayList<Sales> getAllSales() throws SQLException {
		System.out.println("\nRetrieving all vehicles sales...");
		ArrayList<Sales> salesList = new ArrayList<>(); //implement arraylist of sales table data
		try {
			dbConnection = getDBConnection(); //get or implement the connection for database
			PreparedStatement state = dbConnection.prepareStatement("SELECT * FROM sales;"); //pre-compiled statement for sql query
			System.out.println("SQL Query = " + "SELECT * FROM sales;"); //done so query is visible in console
			result = state.executeQuery(); //execute the query into this class
			while(result.next()) { //list and request all available records in the table corresponding to sql query
	            	int vehicle_id = result.getInt("vehicle_id");
	            	String sold_date = result.getString("sold_date");
	            	int sold_price = result.getInt("sold_price");
	            	String status_info = result.getString("status_info");
	                salesList.add(new Sales(vehicle_id, sold_date, sold_price, status_info)); //set variables into constructor
	        }
		} finally { 
			if (result != null) { result.close(); } //ensure query closes after use
			if (dbConnection != null) { dbConnection.close(); } //ensure database connection closes after use
		} return salesList;
	}
	/** 
	 * Searches for a specific vehicle through specified vehicle_id parameter from the vehicle table in the database.
	 * @param id correlates to vehicle_id
	 * @return autoMobile
	 * @throws SQLException
	**/ 
	public Vehicle getVehicle(int id) throws SQLException {
		Vehicle autoMobile = null;
		System.out.println("\nRetrieving vehicle... ");
		try {
			dbConnection = getDBConnection(); //get or implement the connection for database
			PreparedStatement state = dbConnection.prepareStatement("SELECT * FROM vehicles WHERE vehicle_id = ?;"); //pre-compiled statement for sql query
			System.out.println("SQL Query = " + "SELECT * FROM vehicles WHERE vehicle_id ="+id+";"); //done so query is visible in console
			state.setInt(1, id); //set 1st parameter (= ?) of prepared statement to id variable
			result = state.executeQuery(); //execute the query into this class
			while(result.next()) {
	            	int vehicle_id = result.getInt("vehicle_id");
	            	String make =result.getString("make");
	            	String model=result.getString("model");
	            	int year=result.getInt("year");
	            	int price=result.getInt("price");
	            	String license_number=result.getString("license_number");
	            	String colour=result.getString("colour");
	            	int number_doors=result.getInt("number_doors");
	            	String transmission=result.getString("transmission");
	            	int mileage=result.getInt("mileage");
	            	String fuel_type=result.getString("fuel_type");
	            	int engine_size=result.getInt("engine_size");
	            	String body_style=result.getString("body_style");
	            	String condition=result.getString("condition");
	            	String notes=result.getString("notes");
	                autoMobile = new Vehicle(vehicle_id, make, model, year, price, license_number, colour, number_doors, 
	                		transmission, mileage, fuel_type, engine_size, body_style, condition, notes);
			}
		} catch(SQLException e) { System.out.println(e.getMessage()); }
		finally {
			if (result != null) { result.close(); } //ensure query closes after use
			if (dbConnection != null) { dbConnection.close(); } //ensure database connection closes after use
		} return autoMobile;
	}
	
	/** 
	 * Deletes a specific vehicle through specified vehicle_id parameter from the vehicle table in the database.
	 * @param id correlates to vehicle_id
	 * @return true
	 * @throws SQLException
	**/ 
	public boolean deleteVehicle(int id) throws SQLException {
		System.out.println("\nDeleting vehicle... ");
		try {
			dbConnection = getDBConnection();//get or implement the connection for database
			PreparedStatement state = dbConnection.prepareStatement("DELETE FROM vehicles WHERE vehicle_id = ?;"); //pre-compiled statement for sql query
			System.out.println("SQL Query = " + "DELETE FROM vehicles WHERE vehicle_id ="+id+";"); //done so query is visible in console
			state.setInt(1, id); //set 1st parameter (= ?) of prepared statement to id variable
			state.execute();//execute the query into this class
		}catch(SQLException e) { System.out.println(e.getMessage()); } 
		finally {	if (dbConnection != null) { dbConnection.close(); } //ensure database connection closes after use
		} return true;
	}
	
	/** 
	 * Inserts a vehicle through use of getters in the vehicle class, aswell as correlating class to the database.
	 * Also every record in the vehicles table in the database must be inserted.
	 * @param v correlates to vehicle class
	 * @return established
	 * @throws SQLException
	**/ 
	public boolean insertVehicle(Vehicle v) throws SQLException {
		System.out.println("\nInserting vehicle... ");
		boolean established = false;
		try {
			dbConnection = getDBConnection();//get or implement the connection for database
			PreparedStatement state = dbConnection.prepareStatement("INSERT INTO Vehicles (vehicle_id, make, model, year, price, license_number, "
					+ "colour, number_doors, transmission, mileage, fuel_type, engine_size, body_style, condition, notes) "
					+ "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)"); //pre-compiled statement for sql query
			state.setInt(1, v.getVehicle_id()); //set 1st parameter (= ?) of prepared statement to id of vehicle
			state.setString(2, v.getMake()); //set 2nd parameter (= ?) of prepared statement to make of vehicle
			state.setString(3, v.getModel()); //set 3rd parameter (= ?) of prepared statement to model of vehicle
			state.setInt(4, v.getYear()); //set 4th parameter (= ?) of prepared statement to year of vehicle
			state.setInt(5, v.getPrice()); //set 5th parameter (= ?) of prepared statement to price of vehicle
			state.setString(6, v.getLicense()); //set 6th parameter (= ?) of prepared statement to license number of vehicle
			state.setString(7, v.getColour()); //set 7th parameter (= ?) of prepared statement to colour of vehicle
			state.setInt(8, v.getDoors()); //set 8th parameter (= ?) of prepared statement to number of doors of vehicle
			state.setString(9, v.getTransmission()); //set 9th parameter (= ?) of prepared statement to transmission of vehicle
			state.setInt(10, v.getMileage()); //set 10th parameter (= ?) of prepared statement to mileage of vehicle
			state.setString(11, v.getFuel()); //set 11th parameter (= ?) of prepared statement to fuel type of vehicle
			state.setInt(12, v.getEngine()); //set 12th parameter (= ?) of prepared statement to engine of vehicle
			state.setString(13, v.getBody()); //set 13th parameter (= ?) of prepared statement to body type of vehicle
			state.setString(14, v.getCondition()); //set 14th parameter (= ?) of prepared statement to condition of vehicle
			state.setString(15, v.getNotes()); //set 15th parameter (= ?) of prepared statement to notes of vehicle
			established = state.execute(); //execute the query into this class
			established = true;
		}finally {	if (dbConnection != null) { dbConnection.close(); } //ensure database connection closes after use
		} return established;
	}
	
	/** 
	 * Updates for a vehicle through specified vehicle_id parameter from the vehicle table in the database.
	 * Also each record in the vehicles table in the database can be updated or modified
	 * @param v correlates to vehicle class
	 * @param id correlates to vehicle_id
	 * @return true
	 * @throws SQLException
	**/ 
	public boolean updateVehicle(Vehicle v , int id) throws SQLException {
		System.out.println("Updating Vehicle... ");
		try {
			dbConnection = getDBConnection();//get or implement the connection for database
			PreparedStatement state = dbConnection.prepareStatement("UPDATE Vehicles SET vehicle_id = ?, make = ?, model = ?, year = ?, price = ?, "
					+ "license_number = ?, colour = ?, number_doors = ?, transmission = ?, mileage = ?, fuel_type = ?, engine_size = ?, "
					+ "body_style = ?, condition = ?, notes = ? WHERE Vehicle_id = ?;"); //pre-compiled statement for sql query
			state.setInt(1, id); //set 1st parameter (= ?) of prepared statement to id of vehicle
			state.setString(2, v.getMake()); //set 2nd parameter (= ?) of prepared statement to make of vehicle
			state.setString(3, v.getModel()); //set 3rd parameter (= ?) of prepared statement to model of vehicle
			state.setInt(4, v.getYear()); //set 4th parameter (= ?) of prepared statement to year of vehicle
			state.setInt(5, v.getPrice()); //set 5th parameter (= ?) of prepared statement to price of vehicle
			state.setString(6, v.getLicense()); //set 6th parameter (= ?) of prepared statement to license number of vehicle
			state.setString(7, v.getColour()); //set 7th parameter (= ?) of prepared statement to colour of vehicle
			state.setInt(8, v.getDoors()); //set 8th parameter (= ?) of prepared statement to number of doors of vehicle
			state.setString(9, v.getTransmission()); //set 9th parameter (= ?) of prepared statement to transmission of vehicle
			state.setInt(10, v.getMileage()); //set 10th parameter (= ?) of prepared statement to mileage of vehicle
			state.setString(11, v.getFuel()); //set 11th parameter (= ?) of prepared statement to fuel type of vehicle
			state.setInt(12, v.getEngine()); //set 12th parameter (= ?) of prepared statement to engine of vehicle
			state.setString(13, v.getBody()); //set 13th parameter (= ?) of prepared statement to body type of vehicle
			state.setString(14, v.getCondition()); //set 14th parameter (= ?) of prepared statement to condition of vehicle
			state.setString(15, v.getNotes()); //set 15th parameter (= ?) of prepared statement to notes of vehicle
			state.setInt(16, id); //set 16th parameter (= ?) of prepared statement to id variable
			state.executeUpdate(); //execute the query into this class
		} catch (SQLException e) { 
			System.out.println(e.getMessage());
			return false;
		} finally {	if (dbConnection != null) { dbConnection.close(); } //ensure database connection closes after use
		} return true;
	}
}
