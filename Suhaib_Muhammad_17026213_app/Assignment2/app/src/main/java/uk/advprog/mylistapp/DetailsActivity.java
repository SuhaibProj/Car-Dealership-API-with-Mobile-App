package uk.advprog.mylistapp;

import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import javax.net.ssl.HttpsURLConnection;

/**
 * This class was designed to be the details page for the user, where it primarily connects to the URL, and sends the appropriate request and response,
 * where the program gets the details of the vehicle from the database, by providing them in TextBoxs' to the user.
 * The class also accommodates the, 'intents' to transfer to the update activity when clicking on the update button
 * and main activity (homepage) when the delete button is selected/clicked.
 *
 * @author Muhammad Suhaib 17026213
 * @version 5
 */
public class DetailsActivity extends AppCompatActivity {
    /**
     * Function created for operations to occur when activity is first opened, such as controlling the thread on the main, ensuring buttons
     * are referenced, URL has been declared and pointers to the URL have been stated, vehicle data is converted to json format that is readable
     * by the android and this is then implemented into the listView.
     *
     * In general, the function, creates a vehicle object, declares and sets vehicle data for each TextBox, ensures the delete vehicle button
     * deletes the vehicle. and vehicle is specified by the vehicle details in which the user is in.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details); //Ensure code scope is in the DetailsActivity layout of the app

        //hack for running network on main thread
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        Bundle extras = getIntent().getExtras(); // get the intent and extras to perform navigation through the app

        final Vehicle veh = (Vehicle) extras.get("vehicle"); //Vehicle object with key ('vehicle') (passed over from MainActivity) will be used to retrieve the value or variable needed

        //ensure the update vehicle button takes you to the UpdateActivity class when clicked
        Button updateVehicle = findViewById(R.id.buttonUpdate);
        updateVehicle.setOnClickListener(new View.OnClickListener() { //will event handle the update button click function
            /**
             * This function is simply utilized to change activities form the details activity to the update activity
             * when the update button has been clicked
             * @param view is responsible for drawing and event handling
             */
            @Override
            public void onClick(View view) { //when clicked
                Intent IntentUpdate = new Intent(DetailsActivity.this, UpdateActivity.class); //from details activity to update activity
                IntentUpdate.putExtra("Vehicle", veh); //passes the parameters of the vehicle back
                startActivity(IntentUpdate); //initiate intent
            }
        });

        //declare and set vehicle data for the TextBox for the vehicles id
        TextView id = findViewById(R.id.textViewID);
        id.setText("Vehicle ID: "+ Integer.toString(veh.getVehicle_id()));
        //declare and set vehicle data for the TextBox for the vehicles make
        TextView make = findViewById(R.id.textViewMake);
        make.setText("Vehicle Make: "+veh.getMake());
        //declare and set vehicle data for the TextBox for the vehicles model
        TextView model = findViewById(R.id.textViewModel);
        model.setText("Vehicle Model: "+veh.getModel());
        //declare and set vehicle data for the TextBox for the vehicles year
        TextView year = findViewById(R.id.textViewYear);
        year.setText("Vehicle Year: "+Integer.toString(veh.getYear()));
        //declare and set vehicle data for the TextBox for the vehicles price
        TextView price = findViewById(R.id.textViewPrice);
        price.setText("Vehicle Price: "+Integer.toString(veh.getPrice()));
        //declare and set vehicle data for the TextBox for the vehicles license
        TextView license = findViewById(R.id.textViewLicense);
        license.setText("Vehicle License Number: "+veh.getLicense());
        //declare and set vehicle data for the TextBox for the vehicles colour
        TextView colour = findViewById(R.id.textViewColour);
        colour.setText("Vehicle Colour: "+veh.getColour());
        //declare and set vehicle data for the TextBox for the vehicles doors
        TextView doors = findViewById(R.id.textViewDoors);
        doors.setText("Vehicle Door Number: "+Integer.toString(veh.getDoors()));
        //declare and set vehicle data for the TextBox for the vehicles transmission
        TextView transmission = findViewById(R.id.textViewTransmission);
        transmission.setText("Vehicle Transmission: "+veh.getTransmission());
        //declare and set vehicle data for the TextBox for the vehicles mileage
        TextView mileage = findViewById(R.id.textViewMileage);
        mileage.setText("Vehicle Mileage: "+Integer.toString(veh.getMileage()));
        //declare and set vehicle data for the TextBox for the vehicles fuel
        TextView fuel = findViewById(R.id.textViewFuel);
        fuel.setText("Vehicle Fuel: "+veh.getFuel());
        //declare and set vehicle data for the TextBox for the vehicles engine
        TextView engine = findViewById(R.id.textViewEngine);
        engine.setText("Vehicle Engine: "+Integer.toString(veh.getEngine()));
        //declare and set vehicle data for the TextBox for the vehicles body
        TextView body = findViewById(R.id.textViewBody);
        body.setText("Vehicle Body Style: "+veh.getBody());
        //declare and set vehicle data for the TextBox for the vehicles condition
        TextView condition = findViewById(R.id.textViewCondition);
        condition.setText("Vehicle Condition: "+veh.getCondition());
        //declare and set vehicle data for the TextBox for the vehicles notes
        TextView notes = findViewById(R.id.textViewNotes);
        notes.setText("Vehicle Notes: "+veh.getNotes());

        final HashMap<String,String> paramsDelete = new HashMap<>(); //create hashmap to provide directive of posting data into URL and server

        //ensure the delete vehicle button deletes the vehicle and takes the user back to the home page when clicked
        Button del = findViewById(R.id.buttonDelete);
        del.setOnClickListener(new View.OnClickListener() { //will event handle the update button click function
            @Override
            public void onClick(View view) { //when clicked
                Gson gson = new Gson();
                String vDelete = String.valueOf(veh.getVehicle_id());
                paramsDelete.put("vehicle_id", vDelete);
                String jsonVehId = gson.toJson(paramsDelete);
                String url = "http://10.0.2.2:8005/vehiclesdb/api?&Vehicle_id="+veh.getVehicle_id(); //setting the url to be passed with the vehicle id as URI
                performDeleteCall(url, paramsDelete); //perform the delete call, so delete the vehicle with the requirements given in function

                //ensures button goes back to the homepage after updating vehicle
                Intent IntentAdded = new Intent(DetailsActivity.this, MainActivity.class);
                startActivity(IntentAdded);
            }
        });
    }
    /**
     * This will perform a DELETE call through connecting to the URL and then returning response on state of connection executes in parameters later
     * @param requestURL to request the appropriate URL connection
     * @param paramsDelete used as a key to perform DELETE request
     * @return resp, need response to to determine request has been accepted
     */
    public String performDeleteCall(String requestURL, HashMap<String,String> paramsDelete) {
        String resp = ""; //initial empty response
        try {
            URL url = new URL(requestURL); //create the URL object, where URL will be passed
            HttpURLConnection conn = (HttpURLConnection) url.openConnection(); //create the connection object
            conn.setReadTimeout(15000); //timeout on waiting to read data is 15000 milliseconds
            conn.setConnectTimeout(15000); //if connection doesn't occur, timeout in 15000 milliseconds
            conn.setRequestMethod("DELETE"); //invoke DELETE statement when connecting with request (deleting)
            conn.setDoInput(true);

            OutputStream os = conn.getOutputStream(); //write/send/DELETE data to the connection using output stream and buffered writer
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8")); //set encoding with output stream writes text to character output
            writer.write(getDeleteDataString(paramsDelete)); //write/send/DELETE key/value data (url encoded) to the server

            writer.flush();//clear the writer
            writer.close(); //close the writer
            os.close();//close output stream

            int responseCode = conn.getResponseCode();

            if (responseCode == HttpsURLConnection.HTTP_OK) { //if vehicle deletion completed successfully
                Toast.makeText(this, "Success: Vehicle Deleted :)", Toast.LENGTH_LONG).show(); //show vehicle deletion successful
                String line;
                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                while ((line = br.readLine()) != null) { resp += line; }
            } else { //if vehicle deletion ws unsuccessful
                Toast.makeText(this, "ERROR: Failed To Delete Vehicle :(", Toast.LENGTH_LONG).show();//show vehicle deletion unsuccessful
                resp = ""; //output response as empty
            }
        } catch (Exception e) { e.printStackTrace(); } //display Exception to the console
        return resp;
    }

    /**
     * This method converts a HashMap to a URL query string of key/values pairs (e.g. : Make=BMW & Model=5-Series...)
     * @param params used as a key to return same elements as the hash map, and encoding operation.
     * @return res.toString, return the result of the encoding of string
     * @throws UnsupportedEncodingException
     */
    private String getDeleteDataString(HashMap<String,String> params) throws UnsupportedEncodingException {
        StringBuilder res = new StringBuilder();
        boolean first = true;
        for (Map.Entry<String, String> entry : params.entrySet()) { //insert the hashMap key and have the same elements as the hash map.
            if (first) { first = false; }
            else { res.append("&"); }
            res.append(URLEncoder.encode(entry.getKey(), "UTF-8")); //ensure appropriate encoding for hashMap key
            res.append("=");
            res.append(URLEncoder.encode(entry.getValue(), "UTF-8")); //ensure appropriate encoding for hashMap value
        }
        return res.toString(); //return result in string format
    }
}
