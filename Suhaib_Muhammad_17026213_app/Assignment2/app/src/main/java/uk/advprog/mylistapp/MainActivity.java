package uk.advprog.mylistapp;

import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class was designed to be the homepage for the user, where it primarily connects to the URL, and sends the appropriate request,
 * where the program gets the list of vehicles from the database, by providing the vehicle Make, Model, Year & License to the user.
 * The class also accommodates the, 'intents' to transfer to the details activity when clicking on a particular vehicle in the ListView
 * and insert activity when the insert button is selected/clicked.
 *
 * @author Muhammad Suhaib 17026213
 * @version 5
 */

@SuppressWarnings("unchecked") //don't display in console unchecked or unsafe operations
public class MainActivity extends AppCompatActivity {
    String[] vehIdentify; //store global string variable, to store all the vehicle identifiers by make, model, year & License
    ArrayList<Vehicle> veh = new ArrayList<>(); //declare global array to store all vehicles

    /**
     * Function created for operations to occur when activity is first opened, such as controlling the thread on the main, ensuring buttons
     * are referenced, URL has been declared and pointers to the URL have been stated, vehicle data is converted to json format that is readable
     * by the android and this is then implemented into the listView.
     * @param savedInstanceState system uses to restore the previous state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); //Ensure code scope is in the MainActivity layout of the app (homepage)

        //hack for running network on main thread
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        ListView vehicleList = findViewById(R.id.vehicleList); //reference the listView of vehicles

        //ensure the add new vehicle button takes you to the InsertActivity class when clicked
        Button addVehicle = findViewById(R.id.buttonAdd); //call the addVehicle button
        addVehicle.setOnClickListener(new View.OnClickListener() {
            /**
             * This function is simply utilized to change activities form the main activity to the insert activity
             * when the insert button has been clicked
             * @param view is responsible for drawing and event handling
             */
            @Override
            public void onClick(View view) { //button performs action when clicked
                Intent IntentAdd = new Intent(MainActivity.this, InsertActivity.class);
                startActivity(IntentAdd); //load up Insert activity according to the intent
            }
        });
        InputStream in = null; //set to null, as to ensure its empty
        try {
            URL url = new URL("http://10.0.2.2:8005/vehiclesdb/api");// The URL I want to connect to
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection(); // open the connection to the specified URL
            in = new BufferedInputStream(urlConnection.getInputStream()); // get the response from the server in an input stream
        } catch (IOException e) { e.printStackTrace(); }

        String response = convertStreamToString(in); // convert the input stream to a string
        System.out.println("Server response = " + response); // print the response to android monitor/log cat

        try {
            JSONArray jsonArray = new JSONArray(response); // declare new json array and pass string response from server, thus converting string into androids version of JSON array
            vehIdentify = new String[jsonArray.length()]; // instantiate vehicleNames array and set size to amount of vehicle objects returned by the server.

            for (int i = 0; i < jsonArray.length(); i++) { // use for loop, iterate over the JSON array (populate data)
                //get the the vehicle data from the currently converted JSON objects
                int vehicle_id = Integer.valueOf(jsonArray.getJSONObject(i).get("vehicle_id").toString()); //get android json format for vehicle id
                String make = jsonArray.getJSONObject(i).get("make").toString(); //get android json format for make
                String model = jsonArray.getJSONObject(i).get("model").toString(); //get android json format for model
                int year = Integer.valueOf(jsonArray.getJSONObject(i).get("year").toString()); //get android json format for year
                int price = Integer.valueOf(jsonArray.getJSONObject(i).get("price").toString()); //get android json format for price
                String license = jsonArray.getJSONObject(i).get("license_number").toString(); //get android json format for license number
                String colour = jsonArray.getJSONObject(i).get("colour").toString(); //get android json format for colour
                int doors = Integer.valueOf(jsonArray.getJSONObject(i).get("number_doors").toString()); //get android json format for doors
                String transmission = jsonArray.getJSONObject(i).get("transmission").toString(); //get android json format for transmission
                int mileage = Integer.valueOf(jsonArray.getJSONObject(i).get("mileage").toString()); //get android json format for mileage
                String fuel = jsonArray.getJSONObject(i).get("fuel_type").toString(); //get android json format for fuel
                int engine = Integer.valueOf(jsonArray.getJSONObject(i).get("engine_size").toString()); //get android json format for engine size
                String body = jsonArray.getJSONObject(i).get("body_style").toString(); //get android json format for body style
                String condition = jsonArray.getJSONObject(i).get("condition").toString(); //get android json format for condition
                String notes = jsonArray.getJSONObject(i).get("notes").toString(); //get android json format for notes

                Vehicle v = new Vehicle(vehicle_id, make, model, year, price, license, colour, doors,
                    transmission, mileage, fuel, engine, body, condition, notes);

                veh.add(v);  //add the vehicles to the ArrayList
                String detailsToString = make + ", " + model + ", " + year + ", " + license; //store vehicle make, model, year and license numbers.
                vehIdentify[i] = detailsToString; // add the make, model, year and licnse of each vehicle into the vehIdentify array

                ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, vehIdentify); //this points the data in vehidentify to the listView reference in the android app
                vehicleList.setAdapter(arrayAdapter); //set the details in the vehIdentify array to appear in the ListView set up in the activity homepage

                vehicleList.setOnItemClickListener(new AdapterView.OnItemClickListener() { //when any item has been selected
                    /**
                     * This function is intended to perform action when any item in the list has been clicked and
                     * passes back the event handler & references to the vehicle data.
                     * @param parent is the listView essentially
                     * @param view is responsible for drawing and event handling
                     * @param i is the current vehicle
                     * @param l is used for long integers relating to button click
                     */
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
                        Toast.makeText(MainActivity.this, "You pressed " + veh.get(i).getMake() + ", " + veh.get(i).getModel() + ", " + veh.get(i).getLicense(), Toast.LENGTH_SHORT).show(); //show notification of what was pressed
                        Intent intent = new Intent(getApplicationContext(), DetailsActivity.class); // declare new intent and from the main activity, open and start the details activity
                        intent.putExtra("vehicle", veh.get(i)); //pass back the specific vehicle details when clicking on the
                        startActivity(intent); //load up Details activity according to the intent
                    }
                });
            }
        } catch (JSONException e) { e.printStackTrace(); }
    }

    /**
     * Converts all the inputted data to string values
     * @param is is the input stream in which data is inputted
     * @return s.hasNext() used for returning next input
     */
    public String convertStreamToString(InputStream is) {
        Scanner s = new Scanner(is).useDelimiter("\\A");
        return s.hasNext() ? s.next() : "";
    }

}

