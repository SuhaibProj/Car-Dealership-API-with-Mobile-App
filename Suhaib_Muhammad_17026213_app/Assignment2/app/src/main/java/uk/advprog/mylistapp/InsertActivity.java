package uk.advprog.mylistapp;

import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

/**
 * This class was designed to insert a new vehicle into the DataBase, where it primarily connects to the URL, and sends the appropriate request and response,
 * where the program gets the details of the vehicle from the user, by providing them in TextBox's to the user.
 * The class also accommodates the, 'intents' to transfer to the update activity when clicking on the insert button
 *
 * @author Muhammad Suhaib 17026213
 * @version 5
 */
public class InsertActivity extends AppCompatActivity {
    /**
     * Function created for operations to occur when activity is first opened, such as controlling the thread on the main, ensuring buttons
     * are referenced, URL has been declared and pointers to the URL have been stated, vehicle data is converted to json format that is readable
     * by the android and this is then implemented into the DataBase.
     *
     * Call and store the vehicle data corresponding to each textBox to insert vehicle
     * Also ensures each variable assigned is a final value, thus it can't be any other.
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert); //Ensure code scope is in the InsertActivity layout of the app

        //hack for running network on main thread
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        final EditText id = findViewById(R.id.editTextID); //declare and initialize the Final EditText box with  Final value of the vehicle ID in the database
        final EditText make = findViewById(R.id.editTextMake);//declare and initialize the Final EditText box with Final value of the vehicle make in the database
        final EditText model = findViewById(R.id.editTextModel);//declare and initialize the Final EditText box with Final value of the vehicle model in the database
        final EditText year = findViewById(R.id.editTextYear);//declare and initialize the Final EditText box with Final value of the vehicle year in the database
        final EditText price = findViewById(R.id.editTextPrice);//declare and initialize the Final EditText box with Final value of the vehicle price in the database
        final EditText license = findViewById(R.id.editTextILicense);//declare and initialize the Final EditText box with Final value of the vehicle license number in the database
        final EditText colour = findViewById(R.id.editTextColour);//declare and initialize the Final EditText box with Final value of the vehicle colour in the database
        final EditText doors = findViewById(R.id.editTextDoors);//declare and initialize the Final EditText box with Final value of the vehicle number of doors in the database
        final EditText transmission = findViewById(R.id.editTextTransmission);//declare and Final initialize the EditText box with Final value of the vehicle transmission in the database
        final EditText mileage = findViewById(R.id.editTextMileage);//declare and initialize the Final EditText box with Final value of the vehicle mileage in the database
        final EditText fuel = findViewById(R.id.editTextFuel);//declare and initialize the Final EditText box with Final value of the vehicle fuel type in the database
        final EditText engine = findViewById(R.id.editTextEngine);//declare and initialize the Final EditText box with Final value of the vehicle engine size in the database
        final EditText body = findViewById(R.id.editTextBody);//declare and initialize the Final EditText box with Final value of the vehicle body style in the database
        final EditText condition = findViewById(R.id.editTextCondition);//declare and initialize the Final EditText box with Final value of the vehicle condition in the database
        final EditText notes = findViewById(R.id.editTextNotes);//declare and initialize the Final EditText box with Final value of the vehicle notes in the database

        Button addVehicleSave = findViewById(R.id.buttonSaveAdd); //declare button with its corresponding identification
        final HashMap<String,String> paramsInsert = new HashMap<>(); //create hashmap to provide directive of posting data into URL and server

        addVehicleSave.setOnClickListener(new View.OnClickListener() {
            /**
             * Get vehicle data in text string format to convert to Json data format later
             * also convert the integer values of the vehicle data to string values, after this initialize these values into the
             * Vehicle constructor and then convert to android readable json data, in which after this data is passed to the database
             * and connecting to the provided URL.
             *
             * @param view is responsible for drawing and event handling
             */
            @Override
            public void onClick(View view) {
                Gson gson = new Gson(); //declared new Gson variable, used to convert vehicle data into JSON representation
                int idS = Integer.valueOf(id.getText().toString()); //instantiate the final value inputted as the vehicle id in the EditText box as a string value
                String makeS = make.getText().toString();//instantiate the final value inputted as the vehicle make in the EditText box as a string value
                String modelS = model.getText().toString();//instantiate the final value inputted as the vehicle model in the EditText box as a string value
                int yearS = Integer.valueOf(year.getText().toString());//instantiate the final value inputted as the vehicle year in the EditText box as a string value
                int priceS = Integer.valueOf(price.getText().toString());//instantiate the final value inputted as the vehicle price in the EditText box as a string value
                String licenseS = license.getText().toString();//instantiate the final value inputted as the vehicle license number in the EditText box as a string value
                String colourS = colour.getText().toString();//instantiate the final value inputted as the vehicle colour in the EditText box as a string value
                int doorsS = Integer.valueOf(doors.getText().toString());//instantiate the final value inputted as the vehicle number of doors in the EditText box as a string value
                String transmissionS = transmission.getText().toString();//instantiate the final value inputted as the vehicle transmission in the EditText box as a string value
                int mileageS = Integer.valueOf(mileage.getText().toString());//instantiate the final value inputted as the vehicle mileage in the EditText box as a string value
                String fuelS = fuel.getText().toString();//instantiate the final value inputted as the vehicle fuel type in the EditText box as a string value
                int engineS = Integer.valueOf(engine.getText().toString());//instantiate the final value inputted as the vehicle engine size in the EditText box as a string value
                String bodyS = body.getText().toString();//instantiate the final value inputted as the vehicle body style in the EditText box as a string value
                String conditionS = condition.getText().toString();//instantiate the final value inputted as the vehicle condition in the EditText box as a string value
                String notesS = notes.getText().toString();//instantiate the final value inputted as the vehicle notes in the EditText box as a string value

                //instantiate constructor for the vehicle once values have been inputted
                Vehicle v = new Vehicle(idS, makeS, modelS, yearS, priceS, licenseS, colourS, doorsS, transmissionS, mileageS, fuelS, engineS, bodyS, conditionS, notesS);

                String vehicleJson = gson.toJson(v); //convert the inputted gson data into json, to allow easy extraction of vehicle data
                paramsInsert.put("Vehicle", vehicleJson); //pass through the models.Vehicle class (Key) and json converted vehicle data into the parameter
                String url =  "http://10.0.2.2:8005/vehiclesdb/api"; //10.0.2.2 = localhost address, reserved within the emulator
                performPostCall(url, paramsInsert); //instantiates the POST directive to post the data onto the database

                //ensures button goes back & updates to the homepage after inserting vehicle
                Intent IntentAdded = new Intent(InsertActivity.this, MainActivity.class);
                startActivity(IntentAdded);
            }
        });
    }

    /**
     * This will perform a INSERT call through connecting to the URL and then returning response on state of connection executes in parameters later
     * @param requestURL
     * @param postDataParams used as a key to perform POST request
     * @return resp, need response to to determine request has been accepted
     */
    public String performPostCall (String requestURL, HashMap<String,String> postDataParams) {
        String resp = ""; //initial empty response
        try {
            URL url = new URL(requestURL);//create the URL object, where URL will be passed
            HttpURLConnection conn = (HttpURLConnection) url.openConnection(); //create the connection object
            conn.setReadTimeout(15000); //timeout on waiting to read data is 15000 milliseconds
            conn.setConnectTimeout(15000); //if connection doesn't occur, timeout in 15000 milliseconds
            conn.setRequestMethod("POST"); //invoke POST statement when connecting with request (inserting)
            conn.setDoInput(true);

            OutputStream os = conn.getOutputStream(); //write/send/POST data to the connection using output stream and buffered writer
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, StandardCharsets.UTF_8)); //set encoding with outputstream writes text to character output
            writer.write(getPostDataString(postDataParams)); //write/send/POST key/value data (url encoded) to the server

            writer.flush(); //clear the writer
            writer.close(); //close the writer
            os.close(); //close output stream

            int responseCode = conn.getResponseCode(); //get the server response code to determine what to do next (i.e. success/error)
            System.out.println("responseCode = " + responseCode); //print out response code

            if(responseCode == HttpsURLConnection.HTTP_OK){ //if vehicle insertion completed successfully
                Toast.makeText(this, "Success: Vehicle Inserted :)", Toast.LENGTH_SHORT).show(); //show vehicle inserted successful
                String line;
                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream())); //write text to character output with input in connection
                while ((line=br.readLine()) != null) { resp += line; }
            }else { //if vehicle insertion ws unsuccessful
                Toast.makeText(this, "Error: Failed To Insert Vehicle :(", Toast.LENGTH_SHORT).show(); //show vehicle inserted unsuccessful
                resp=""; //output response as empty
            }
        }catch (Exception e){ e.printStackTrace(); } //display Exception to the console
        return resp;
    }

    /**
     * This method converts a HashMap to a URL query string of key/values pairs (e.g. : Make=BMW & Model=5-Series...)
     * @param params used as a key to return same elements as the hash map. and encoding operation.
     * @return res.toString, return the result of the encoding of string
     * @throws UnsupportedEncodingException
     */
    private String getPostDataString(HashMap<String,String> params) throws UnsupportedEncodingException {
        StringBuilder res = new StringBuilder();
        boolean first = true;
        for(Map.Entry<String,String> entry : params.entrySet()) { //insert the hashMap key
            if (first){ first = false; }
            else { res.append("&"); }
            res.append(URLEncoder.encode(entry.getKey(), "UTF-8")); //ensure appropriate encoding for hashMap key
            res.append("=");
            res.append(URLEncoder.encode(entry.getValue(), "UTF-8")); //ensure appropriate encoding for hashMap value
        }
        return res.toString(); //return results in string format
    }
}
