//import all necessary libraries
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import models.Sales;
import models.Vehicle;
import models.VehicleDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class ControllerConsole {
	/**
	 * Class which starts up the program using database 
	 * and all constituent methods onto console.
	 * @author Muhammad Suhaib 17026213
	 * @version 4.6 
     */
	@SuppressWarnings("resource") //for scanners not closed
	public static void main(String[] args) throws SQLException {
		VehicleDAO dao = new VehicleDAO(); //declare connection to the database
		Vehicle autoMobile = null; //declare instance of Vehicle class
		ArrayList<Vehicle> allVehicles = new ArrayList<Vehicle>(); //implement arraylist of vehicles table data
		ArrayList<Sales> allSales = new ArrayList<Sales>();	//implement arraylist of sales table data
		int select;
		//console menu system which is looped around after selection until exit selection selected
		do {
			System.out.println("---------------------------");
			System.out.println(" Vehicle Inventory System ");
			System.out.println(" Choose from the options");
			System.out.println("---------------------------");
			System.out.println("1 - Retrieve all vehicles");
			System.out.println("2 - Retrieve all vehicle sales");
			System.out.println("3 - Search for vehicle through Vehicle ID");
			System.out.println("4 - Check which license_number registrations are valid");
			System.out.println("5 - Insert new vehicle into database");
			System.out.println("6 - Update existing vehicle in database");
			System.out.println("7 - Delete vehicle from database");
			System.out.println("8 - Exit");
			System.out.print("Insert your choice? ");
			Scanner sc = new Scanner(System.in);
			select = sc.nextInt();
			switch (select) { //switch statement for selection based on menu system
			case 1:	//so this selection will retrive all the vehicles wtihin the vehicles table in the database
				allVehicles = dao.getAllVehicles(); //get show all vehicles method
				for (Vehicle veh : allVehicles) {
					System.out.println("---------------------------");
					System.out.println(veh);
					System.out.println("---------------------------");
					System.out.println("Vehicle Retrieved");
					System.out.println("---------------------------");
				}
				break;
			case 2:	//so this selection will retrive all the sales within the sales table in the database
				allSales = dao.getAllSales(); //get show all sales method 
				for (Sales sales : allSales) {
					System.out.println("---------------------------");
					System.out.println(sales);
					System.out.println("---------------------------");
					System.out.println("Sales Retrieved");
					System.out.println("---------------------------");
				}
				break;
			case 3: //so this selection will retrieve a specified vehicle by inputted insertion of vehicle id number
				System.out.print("Insert Vehicle ID to retrieve vehicle data: ");
				int get_id = sc.nextInt();
				autoMobile = dao.getVehicle(get_id); //get the 'select vehicle through id' method (search method)
				System.out.println(autoMobile);
				System.out.println("---------------------------");
				System.out.println("Search has been Completed");
				System.out.println("---------------------------");
				break;
			case 4: //so this selection will perform a regular expression search to check license plates in vehicles table are up to standard. 
				System.out.print("Checking license_number registrations...");
				allVehicles = dao.getAllVehicles(); //get show all vehicles method 
				//pre-made regular expression parameters made specifically for 2018 DVLA UK license registrations
				String regEx = "^([A-Z]{3}\\s?(\\d{3}|\\d{2}|d{1})\\s?[A-Z])|([A-Z]\\s?(\\d{3}|\\d{2}|\\d{1})\\s?[A-Z]{3})|(([A-HK-PRSVWY][A-HJ-PR-Y])\\s?([0][2-9]|[1-9][0-9])\\s?[A-HJ-PR-Z]{3})$";
				for (Vehicle v: allVehicles) {
					/**compiling pre made regular expression data and matching/referencing it to vehicle license numbers 
						if vehicle license numbers found then show in console that match is found and license is valid.**/
					Pattern pattern = Pattern.compile(regEx);
					Matcher identical =  pattern.matcher(v.getLicense());
					if (identical.find() && v.getLicense().length() <= 7) { 
						System.out.println(v);
						System.out.println("------------------------------");
						System.out.println("Valid License Registration");
						System.out.println("------------------------------");
					}else { //Otherwise show not valid registration if pre-made regex license data doesn't comply with specific vehicle 
						System.out.println(v);
						System.out.println("------------------------------");
						System.out.println("Not Valid License Registration");
						System.out.println("------------------------------");
					}
				}
				break;
			case 5: /** so this selection will allow the user to insert vehicle by inputted insertion 
							of vehicle data into the vehicle table in the database **/
				System.out.println("\nInsert Vehicle details to add Vehicle: \n");
				System.out.print("Insert Vehicle ID: ");
				int vehicle_id = sc.nextInt();
				System.out.print("Insert Vehicle Make: ");
				String make = sc.next();
				System.out.print("Insert Vehicle Model: ");
				String model = sc.next();
				System.out.print("Insert Vehicle Year of Manufacture: ");
				int year = sc.nextInt();
				System.out.print("Insert Vehicle Price: ");
				int price = sc.nextInt();
				System.out.print("Insert Vehicle License Number: ");
				String license_number= sc.next();
				System.out.print("Insert Vehicle Colour: ");
				String colour = sc.next();
				System.out.print("Insert Vehicle Door Number: ");
				int number_doors = sc.nextInt();
				System.out.print("Insert Vehicle Transmission Type: ");
				String transmission = sc.next();
				System.out.print("Insert Vehicle Mileage: ");
				int mileage = sc.nextInt();
				System.out.print("Insert Vehicle Fuel Type: ");
				String fuel_type = sc.next();
				System.out.print("Insert Vehicle Engine Size: ");
				int engine = sc.nextInt();
				System.out.print("Insert Vehicle Body Style: ");
				String body_style = sc.next();
				System.out.print("Insert Vehicle Condition: ");
				String condition = sc.next();
				System.out.print("Insert Notes for Vehicle: ");
				String notes = sc.next();
				System.out.println("---------------------------");
				System.out.println("Vehicle has been Created");
				System.out.println("---------------------------");
				try {
					Vehicle v = new Vehicle(vehicle_id, make, model, year, price, license_number, colour, number_doors,
							transmission, mileage, fuel_type, engine, body_style, condition, notes); //set variables into constructor
					dao.insertVehicle(v); //get insert method 
				} catch (Exception e) {	System.out.print(e.getMessage()); System.out.println("Incorrectly inputted"); }
				break;
			case 6: //so this selection will update a specified vehicle through input of vehicle id number.
				System.out.print("Insert Vehicle ID to Update vehicle data: ");
				int update_id = sc.nextInt();
				autoMobile = dao.getVehicle(update_id); //get the 'select vehicle through id' method (search method)
				System.out.println(autoMobile);
				boolean exit = false;
				/**update menu system so that each or all vehicle data can be modified and then also later save or cancel modifications
					console menu is looped around until the desired amount of vehicle data is updated, whcih is when 
					exit selection or update selection is made.**/
				do {
					System.out.println("\nSelect the Vehicle details below, to Update");
					System.out.print(" 1.  Make");
					System.out.print("\n 2.  Model");
					System.out.print("\n 3.  Year");
					System.out.print("\n 4.  Price");
					System.out.print("\n 5.  License Number");
					System.out.print("\n 6.  Colour");
					System.out.print("\n 7.  Number of Doors");
					System.out.print("\n 8.  Transmission");
					System.out.print("\n 9.  Mileage");
					System.out.print("\n 10. Fuel Type");
					System.out.print("\n 11. Engine Size");
					System.out.print("\n 12. Body Style");
					System.out.print("\n 13. Condition");
					System.out.print("\n 14. Notes");
					System.out.println("\n---------------------------");
					System.out.println(" 15. Update changes to vehicle");
					System.out.println(" 16. Exit");
					System.out.println("---------------------------");
					System.out.print("Insert selection: ");
					Scanner sc2 = new Scanner(System.in);
					int update_selection = sc2.nextInt();
					switch (update_selection) {
					case 1: /**this allows user to update just the vehicle make 
							thorugh modifying make variable thorugh the setter in the Vehicle class**/
						System.out.print("\nInsert Vehicle Make: ");
						make = sc.next();
						autoMobile.setMake(make);
						break;
					case 2:/**this allows user to update just the vehicle model
							thorugh modifying model variable thorugh the setter in the Vehicle class**/
						System.out.print("\nInsert Vehicle Model: ");
						model = sc.next();
						autoMobile.setModel(model);
						break;
					case 3://this allows user to update just the vehicle year 
						//		thorugh modifying year variable thorugh the setter in the Vehicle class
						System.out.print("\nInsert Vehicle Year: ");
						year = sc.nextInt();
						autoMobile.setYear(year);
						break;
					case 4://this allows user to update just the vehicle price 
						//		thorugh modifying price variable thorugh the setter in the Vehicle class
						System.out.print("\nInsert Vehicle Price: ");
						price = sc.nextInt();
						autoMobile.setPrice(price);
						break;
					case 5://this allows user to update just the vehicle license  
						//		thorugh modifying license variable thorugh the setter in the Vehicle class
						System.out.print("\nInsert Vehicle License: ");
						license_number = sc.next();
						autoMobile.setLicense(license_number);
						break;
					case 6://this allows user to update just the vehicle colour 
						//		thorugh modifying colour variable thorugh the setter in the Vehicle class
						System.out.print("\nInsert Vehicle Colour: ");
						colour = sc.next();
						autoMobile.setColour(colour);
						break;
					case 7://this allows user to update just the vehicle door number
						//		thorugh modifying door number variable thorugh the setter in the Vehicle class
						System.out.print("\nInsert Vehicle Door Number: ");
						number_doors = sc.nextInt();
						autoMobile.setDoors(number_doors);
						break;
					case 8://this allows user to update just the vehicle transmission  
						//		thorugh modifying transmission variable thorugh the setter in the Vehicle class
						System.out.print("\nInsert Vehicle Transmission Type: ");
						transmission = sc.next();
						autoMobile.setTransmission(transmission);
						break;
					case 9://this allows user to update just the vehicle mileage 
						//		thorugh modifying mileage variable thorugh the setter in the Vehicle class
						System.out.print("\nInsert Vehicle Mileage: ");
						mileage = sc.nextInt();
						autoMobile.setMileage(mileage);
						break;
					case 10://this allows user to update just the vehicle fuel type 
						//		thorugh modifying fuel type variable thorugh the setter in the Vehicle class
						System.out.print("\nInsert Vehicle Fuel Type: ");
						fuel_type = sc.next();
						autoMobile.setFuel(fuel_type);
						break;
					case 11://this allows user to update just the vehicle engine size
						//		thorugh modifying engine size variable thorugh the setter in the Vehicle class
						System.out.print("\nInsert Vehicle Engine Size: ");
						engine = sc.nextInt();
						autoMobile.setEngine(engine);
						break;
					case 12://this allows user to update just the vehicle body type 
						//		thorugh modifying body type variable thorugh the setter in the Vehicle class
						System.out.print("\nInsert Vehicle Body Type: ");
						body_style = sc.next();
						autoMobile.setBody(body_style);
						break;
					case 13://this allows user to update just the vehicle condition 
						//		thorugh modifying condition variable thorugh the setter in the Vehicle class
						System.out.print("\nInsert Vehicle Condition: ");
						condition = sc.next();
						autoMobile.setCondition(condition);
						break;
					case 14://this allows user to update just the vehicle notes thorugh modifying 
						//		notes variable thorugh the setter in the Vehicle class
						System.out.print("\nInsert Vehicle Notes: ");
						notes = sc.next();
						autoMobile.setNotes(notes);
						break;
					case 16://this will essentially update the vehicle data in the database table
						try { dao.updateVehicle(autoMobile,update_id); }  //get update method 
						catch (Exception e) { System.out.print(e.getMessage());}
						System.out.println("---------------------------");
						System.out.println("Vehicle has been Updated");
						System.out.println("---------------------------");
						exit = true;
						break;
					case 17://this will essentially exit the update selection back to the initial console menu system.
						exit = true;
						break;
					}
				} while(exit == false);
				break;
			case 7: //so this selection will delete a specified vehicle through input of vehicle id number.
				System.out.print("Insert Vehicle ID to Delete vehicle data: ");
				int del_id = sc.nextInt();
				dao.deleteVehicle(del_id); //get delete method 
				System.out.println("---------------------------");
				System.out.println("Vehicle has been Deleted");
				System.out.println("---------------------------");
				break;
			case 8:
				System.out.println("Goodbye");
				System.exit(1);
				break;
			}
		} while (select != 8); //end program if exit is selected / (8 is inputted).
	}
}
