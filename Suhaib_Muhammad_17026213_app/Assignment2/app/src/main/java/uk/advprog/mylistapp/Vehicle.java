package uk.advprog.mylistapp;

import java.io.Serializable;

public class Vehicle implements Serializable {
    /**
     * Class is created for initializing all values (variables)
     * within the Vehicle records table in the database, as all private variables can be accessed by
     * other classes through the getters and setters implemented in this class.
     *
     * @author Muhammad Suhaib 17026213
     * @version 5
     */

    private int vehicle_id, year, price, number_doors, mileage, engine_size;
    private String make, model, license_number, colour, transmission, fuel_type, body_style, condition, notes;

    /**
     * Constructor for Vehicle class
     * @param vehicle_id correlates to the vehicle_id record in the database (vehicle id)
     * @param make correlates to the make record in the database (vehicle make name)
     * @param model correlates to the model record in the database (vehicle model name)
     * @param year correlates to the year record in the database (vehicle year of production)
     * @param price correlates to the price record in the database (vehicle price of purchase)
     * @param license_number correlates to the license_number record in the database (vehicle license registration number)
     * @param colour correlates to the make colour in the database (colour of vehicle)
     * @param number_doors correlates to the number_doors record in the database (vehicles number of doors)
     * @param transmission correlates to the transmission record in the database (vehicles type of transmission)
     * @param mileage correlates to the mileage record in the database (number of miles vehicle has driven)
     * @param fuel_type correlates to the fuel_type record in the database (type of fuel vehicle takes)
     * @param engine_size correlates to the engine_size record in the database (size of the vehicle engine)
     * @param body_style correlates to the body_style record in the database (type of body style for vehicle)
     * @param condition correlates to the condition record in the database (condition vehicle is in)
     * @param notes correlates to the notes record in the database (any further notes for vehicle)
     */
    public Vehicle(int vehicle_id, String make, String model, int year, int price, String license_number,
                   String colour, int number_doors, String transmission, int mileage, String fuel_type, int engine_size,
                   String body_style,String condition, String notes) {
        this.vehicle_id =vehicle_id;
        this.make = make;
        this.model=model;
        this.year=year;
        this.price = price;
        this.license_number = license_number;
        this.colour = colour;
        this.number_doors = number_doors;
        this.transmission = transmission;
        this.mileage = mileage;
        this.fuel_type = fuel_type;
        this.engine_size = engine_size;
        this.body_style = body_style;
        this.condition = condition;
        this.notes = notes;
    }

    /**
     * Overloaded Constructor for vehicle class so can be used primarily without hassle of parameters in another class.
     */
    public Vehicle() { }

    //Getters and setters for each variable
    /**
     * Gets ID of the vehicle and returns it
     * Sets id of vehicle to this classes vehicle_id variable
     * @return vehicle_id
     **/
    public int getVehicle_id() { return vehicle_id;	}
    public void setVehicle_id(int vehicle_id) {	this.vehicle_id = vehicle_id; }

    /**
     * Gets make of the vehicle and returns it
     * Sets make of vehicle to this classes make variable
     * @return make
     **/
    public String getMake() { return make; }
    public void setMake(String make) { this.make = make; }

    /**
     * Gets model of the vehicle and returns it
     * Sets model of vehicle to this classes model variable
     * @return model
     **/
    public String getModel() { return model; }
    public void setModel(String model) { this.model = model; }

    /**
     * Gets year of the vehicle and returns it
     * Sets year of vehicle to this classes year variable
     * @return year
     **/
    public int getYear() { return year; }
    public void setYear(int year) { this.year = year; }

    /**
     * Gets price of the vehicle and returns it
     * Sets price of vehicle to this classes price variable
     * @return price
     **/
    public int getPrice() {	return price; }
    public void setPrice(int price) { this.price = price; }

    /**
     * Gets number of doors of the vehicle and returns it
     * Sets number of doors of vehicle to this classes number_doors variable
     * @return number_doors
     **/
    public int getDoors() {	return number_doors; }
    public void setDoors(int number_doors) { this.number_doors = number_doors; }

    /**
     * Gets mileage of the vehicle and returns it
     * Sets mileage of vehicle to this classes mileage variable
     * @return mileage
     **/
    public int getMileage() { return mileage; }
    public void setMileage(int mileage) { this.mileage = mileage; }

    /**
     * Gets engine size of the vehicle and returns it
     * Sets engine size of vehicle to this classes engine_size variable
     * @return engine_size
     **/
    public int getEngine() { return engine_size; }
    public void setEngine(int engine_size) { this.engine_size = engine_size; }

    /**
     * Gets license number of the vehicle and returns it
     * Sets license of vehicle to this classes license_number variable
     * @return license_number
     **/
    public String getLicense() { return license_number; }
    public void setLicense(String license_number) { this.license_number = license_number; }

    /**
     * Gets colour of the vehicle and returns it
     * Sets colour of vehicle to this classes colour variable
     * @return colour
     **/
    public String getColour() { return colour; }
    public void setColour(String colour) { this.colour = colour; }

    /**
     * Gets transmission of the vehicle and returns it
     * Sets transmission of vehicle to this classes transmission variable
     * @return transmission
     **/
    public String getTransmission() { return transmission; }
    public void setTransmission(String transmission) { this.transmission = transmission; }

    /**
     * Gets type of fuel of the vehicle and returns it
     * Sets type of fuel of vehicle to this classes fuel_type variable
     * @return fuel_type
     **/
    public String getFuel() { return fuel_type; }
    public void setFuel(String fuel_type) { this.fuel_type = fuel_type; }

    /**
     * Gets body of the vehicle and returns it
     * Sets body of vehicle to this classes body_style variable
     * @return body_style
     **/
    public String getBody() { return body_style; }
    public void setBody(String body_style) { this.body_style = body_style; }

    /**
     * Gets condition of the vehicle and returns it
     * Sets condition of vehicle to this classes condition variable
     * @return condition
     **/
    public String getCondition() { return condition; }
    public void setCondition(String condition) { this.condition = condition; }

    /**
     * Gets notes of the vehicle and returns it
     * Sets notes of vehicle to this classes notes variable
     * @return notes
     **/
    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }

    /**
     * ToString method is used for formatting raw data to readable data in the console
     * @return s is the formatted version of the vehicles data in text form
     */
    @Override
    public String toString() {
        String s = 	"Vehicle ID = "+this.vehicle_id+ '\n' +
                "Make = "+ this.make+ '\n'+
                "Model = "+this.model+ '\n'+
                "Year = "+this.year + '\n'+
                "Price = "+this.price+'\n'+
                "License = "+this.license_number+'\n'+
                "Colour = "+this.colour+'\n'+
                "Doors = "+this.number_doors+'\n'+
                "Transmission = "+this.transmission+'\n'+
                "Mileage = "+this.mileage+'\n'+
                "Fuel = "+this.fuel_type+'\n'+
                "Engine = "+this.engine_size+'\n'+
                "Body = "+this.body_style+'\n'+
                "Condition = "+this.condition+'\n'+
                "Notes = "+this.notes;
        return s;
    }
}
