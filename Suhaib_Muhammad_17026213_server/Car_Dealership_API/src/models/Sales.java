package models;


public class Sales {
	/**
	 * Class is created for initializing all values (variables) 
	 * within the Sales records table in the database, as all private variables can be accessed by
	 * other classes through the getters and setters implemented in this class.
	 * 
	 * @author Muhammad Suhaib 17026213
	 * @version 4.6
     */
	private int vehicle_id, sold_price;
	private String sold_date, status_info;
	
	/**
	 * Constructor for Sales class
	 * @param vehicle_id correlates to the vehicle_id record in the database (vehicle id)
	 * @param sold_date correlates to the sold_date record in the database (date vehicle was sold)
	 * @param sold_price correlated to the sold_price record in the database (price at which vehicle was purchased)
	 * @param status_info correlates to the status_info record in the database (has the vehicle been returned or not)
	 */
	public Sales(int vehicle_id, String sold_date, int sold_price, String status_info) {
		this.vehicle_id =vehicle_id;
		this.sold_date = sold_date;
		this.sold_price = sold_price;
		this.status_info = status_info;
	}
	
	/**
	 * ToString method is used for formatting to readable data in the console
	 * @return s is the formatted version of the sales data in text form
	 */
	public String toString() {
		String s = 	"Vehicle ID = "+this.vehicle_id+'\n'+ 
				    "Sold Date = "+this.sold_date+'\n'+
				    "Sold Price = "+this.sold_price+'\n'+
				    "Status = "+this.status_info;
		return s;
	}
	
	//Getters and setters for each variable
	
	/**
	 * Gets ID of the vehicle and returns it
	 * Sets id of vehicle to this classes vehicle_id variable
	 * @return vehicle_id
	**/
	public int getVehicle_id() { return vehicle_id;	}
	public void setVehicle_id(int vehicle_id) {	this.vehicle_id = vehicle_id; }
	
	/**
	 * Gets date of vehicle sold and returns it
	 * Sets date of vehicle sold to this classes sold_date variable
	 * @return sold_date
	**/
	public String getSoldDate() { return sold_date;	}
	public void setSoldDate(String sold_date) {	this.sold_date = sold_date; }
	
	/**
	 * Gets sold price of the vehicle and returns it
	 * Sets sold price of vehicle to this classes sold_price variable
	 * @return sold_price
	**/
	public int getSoldPrice() { return sold_price;	}
	public void setSoldPrice(int sold_price) {	this.sold_price = sold_price; }
	
	/**
	 * Gets status of vehicle and returns it
	 * Sets status of vehicle to this classes status_info variable
	 * @return set_status
	**/
	public String getStatus() { return status_info;	}
	public void setStatus(String status_info) {	this.status_info = status_info; }
}
