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
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;
/**
 * This class was designed to update a current vehicle in the DataBase, where it primarily connects to the URL, and sends the appropriate request and response,
 * where the program gets the updated details of the vehicle from the user or program, by providing them in TextBox's to the user and user submitting them.
 * The class also accommodates the, 'intents' to transfer to the Main activity (homepage) when clicking on the update button
 *
 * @author Muhammad Suhaib 17026213
 * @version 5
 */
public class UpdateActivity extends AppCompatActivity {
    /**
     * Function created for operations to occur when activity is first opened, such as controlling the thread on the main, ensuring buttons
     * are referenced, URL has been declared and pointers to the URL have been stated, vehicle data is converted to json format that is readable
     * by the android and this is then implemented into the DataBase.
     *
     * This function does the following, a declaration of the TextBox's inside the update activity is created,
     * then each vehicle value must be set to the current value inside each field
     * so that the user knows what he will be editing. Then get the final state of the values inside the
     * vehicle data inside the TextBox's so they can be edited later when button is clicked.
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update); //Ensure code scope is in the UpdateActivity layout of the app

        //hack for running network on main thread
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        Bundle extras = getIntent().getExtras(); //use extras to move around the app

        final Vehicle v = (Vehicle) extras.get("Vehicle"); //Vehicle object with key ('vehicle') (passed over from DetailsActivity) will be used to retrieve the vehicle data needed

        EditText id = findViewById(R.id.editTextIDU); //declare and initialize the initial EditText box with its original value of the vehicle ID in the database
        EditText make = findViewById(R.id.editTextMakeU);//declare and initialize the initial EditText box with its original value of the vehicle make in the database
        EditText model = findViewById(R.id.editTextModelU);//declare and initialize the initial EditText box with its original value of the vehicle model in the database
        EditText year = findViewById(R.id.editTextYearU);//declare and initialize the initial EditText box with its original value of the vehicle year in the database
        EditText price = findViewById(R.id.editTextPriceU);//declare and initialize the initial EditText box with its original value of the vehicle price in the database
        EditText license = findViewById(R.id.editTextILicenseU);//declare and initialize the initial EditText box with its original value of the vehicle license number in the database
        EditText colour = findViewById(R.id.editTextColourU);//declare and initialize the initial EditText box with its original value of the vehicle colour in the database
        EditText transmission = findViewById(R.id.editTextTransmissionU);//declare and initialize the initial EditText box with its original value of the vehicle transmission in the database
        EditText mileage = findViewById(R.id.editTextMileageU);//declare and initialize the initial EditText box with its original value of the vehicle mileage in the database
        EditText fuel = findViewById(R.id.editTextFuelU);//declare and initialize the initial EditText box with its original value of the vehicle fuel type in the database
        EditText body = findViewById(R.id.editTextBodyU);//declare and initialize the initial EditText box with its original value of the vehicle body style in the database
        EditText engine = findViewById(R.id.editTextEngineU);//declare and initialize the initial EditText box with its original value of the vehicle engine size in the database
        EditText doors = findViewById(R.id.editTextDoorsU);//declare and initialize the initial EditText box with its original value of the vehicle number of doors in the database
        EditText condition = findViewById(R.id.editTextConditionU);//declare and initialize the initial EditText box with its original value of the vehicle condition in the database
        EditText notes = findViewById(R.id.editTextNotesU);//declare and initialize the initial EditText box with its original value of the vehicle notes in the database

        id.setText(""+v.getVehicle_id()); //output the text inside the TextBox to original value of the vehicle ID, so user can see whats being changed.
        make.setText(""+v.getMake());//output the text inside the TextBox to original value of the vehicle make, so user can see whats being changed.
        model.setText(""+v.getModel());//output the text inside the TextBox to original value of the vehicle model, so user can see whats being changed.
        year.setText (""+v.getYear());//output the text inside the TextBox to original value of the vehicle year, so user can see whats being changed.
        price.setText(""+v.getPrice());//output the text inside the TextBox to original value of the vehicle price, so user can see whats being changed.
        license.setText(""+v.getLicense());//output the text inside the TextBox to original value of the vehicle license number, so user can see whats being changed.
        colour.setText(""+v.getColour());//output the text inside the TextBox to original value of the vehicle colour, so user can see whats being changed.
        transmission.setText(""+v.getTransmission());//output the text inside the TextBox to original value of the vehicle transmission, so user can see whats being changed.
        mileage.setText(""+v.getMileage());//output the text inside the TextBox to original value of the vehicle mileage, so user can see whats being changed.
        fuel.setText(""+v.getFuel());//output the text inside the TextBox to original value of the vehicle fuel type, so user can see whats being changed.
        body.setText(""+v.getBody());//output the text inside the TextBox to original value of the vehicle body style, so user can see whats being changed.
        engine.setText(""+v.getEngine());//output the text inside the TextBox to original value of the vehicle engine size, so user can see whats being changed.
        doors.setText(""+v.getDoors());//output the text inside the TextBox to original value of the vehicle number of doors, so user can see whats being changed.
        condition.setText(""+v.getCondition());//output the text inside the TextBox to original value of the vehicle condition, so user can see whats being changed.
        notes.setText(""+v.getNotes());//output the text inside the TextBox to original value of the vehicle notes, so user can see whats being changed.

        final EditText idU = findViewById(R.id.editTextIDU);//declare and initialize the Final EditText box with Final value of the vehicle ID in the database
        final EditText makeU = findViewById(R.id.editTextMakeU);//declare and initialize the Final EditText box with Final value of the vehicle make in the database
        final EditText modelU = findViewById(R.id.editTextModelU);//declare and initialize the Final EditText box with Final value of the vehicle model in the database
        final EditText yearU = findViewById(R.id.editTextYearU);//declare and initialize the Final EditText box with Final value of the vehicle year in the database
        final EditText priceU = findViewById(R.id.editTextPriceU);//declare and initialize the Final EditText box with Final value of the vehicle price in the database
        final EditText licenseU = findViewById(R.id.editTextILicenseU);//declare and initialize the Final EditText box with Final value of the vehicle license number in the database
        final EditText colourU = findViewById(R.id.editTextColourU);//declare and initialize the Final EditText box with Final value of the vehicle colour in the database
        final EditText doorsU = findViewById(R.id.editTextDoorsU);//declare and initialize the Final EditText box with Final value of the vehicle number of doors in the database
        final EditText transmissionU = findViewById(R.id.editTextTransmissionU);//declare and initialize the Final EditText box with Final value of the vehicle transmission in the database
        final EditText mileageU = findViewById(R.id.editTextMileageU);//declare and initialize the Final EditText box with Final value of the vehicle mileage in the database
        final EditText fuelU = findViewById(R.id.editTextFuelU);//declare and initialize the Final EditText box with Final value of the vehicle fuel type in the database
        final EditText engineU = findViewById(R.id.editTextEngineU);//declare and initialize the Final EditText box with Final value of the vehicle engine size in the database
        final EditText bodyU = findViewById(R.id.editTextBodyU);//declare and initialize the Final EditText box with Final value of the vehicle body style in the database
        final EditText conditionU = findViewById(R.id.editTextConditionU);//declare and initialize the Final EditText box with Final value of the vehicle condition in the database
        final EditText notesU = findViewById(R.id.editTextNotesU);//declare and initialize the Final EditText box with Final value of the vehicle notes in the database

        Button update = findViewById(R.id.buttonSaveUpdate); //declare the button which saves and updates the vehicle data

        final HashMap<String, String> params = new HashMap<>(); //declared HashMap for insertion and retrieval of values with inputted key.

        //listen to when button has been clicked and perform actions below
        update.setOnClickListener(new View.OnClickListener() {
            /**
             * Function is written for when converting current json data into android readable json data and then perform connetion to specified
             * URL and whilst getting the vehicle data and then at the end showing updated DataBase by moving form the updateActivity to the
             * the MainActivity.
             * @param view is responsible for drawing and event handling
             */
            @Override
            public void onClick(View view) {
                Gson gson = new Gson();  //declared new Gson variable, used to convert vehicle data into JSON representation
                int id2 = Integer.valueOf(idU.getText().toString()); //instantiate the final value inputted as the vehicle id in the EditText box as a string value
                String make2 = makeU.getText().toString();//instantiate the final value inputted as the vehicle make in the EditText box as a string value
                String model2 = modelU.getText().toString();//instantiate the final value inputted as the vehicle model in the EditText box as a string value
                int year2 = Integer.valueOf(yearU.getText().toString());//instantiate the final value inputted as the vehicle year in the EditText box as a string value
                int price2 = Integer.valueOf(priceU.getText().toString());//instantiate the final value inputted as the vehicle price in the EditText box as a string value
                String license2 = licenseU.getText().toString();//instantiate the final value inputted as the vehicle license number in the EditText box as a string value
                String colour2 = colourU.getText().toString();//instantiate the final value inputted as the vehicle colour in the EditText box as a string value
                int doors2 = Integer.valueOf(doorsU.getText().toString());//instantiate the final value inputted as the vehicle number of doors in the EditText box as a string value
                String transmission2 = transmissionU.getText().toString();//instantiate the final value inputted as the vehicle transmission in the EditText box as a string value
                int mileage2 = Integer.valueOf(mileageU.getText().toString());//instantiate the final value inputted as the vehicle mileage in the EditText box as a string value
                String fuel2 = fuelU.getText().toString();//instantiate the final value inputted as the vehicle fuel type in the EditText box as a string value
                int engine2 = Integer.valueOf(engineU.getText().toString());//instantiate the final value inputted as the vehicle engine size in the EditText box as a string value
                String body2 = bodyU.getText().toString();//instantiate the final value inputted as the vehicle body style in the EditText box as a string value
                String condition2 = conditionU.getText().toString();//instantiate the final value inputted as the vehicle condition in the EditText box as a string value
                String notes2 = notesU.getText().toString();//instantiate the final value inputted as the vehicle notes in the EditText box as a string value

                //initialize the values of the vehicle according to constructor
                Vehicle v2 = new Vehicle(id2,make2, model2, year2, price2, license2, colour2, doors2, transmission2,
                                         mileage2, fuel2, engine2, body2, condition2, notes2);

                String vehicleJson = gson.toJson(v2); //convert from vehicle gson data to json data
                params.put("Vehicle", vehicleJson); //setting the hashMap key value
                String url = "http://10.0.2.2:8005/vehiclesdb/api"; //setting the url to be passed

                //post the data into the DataBase with the correct URL and hashMap key for retrieval of vehicle data
                performPutCall(url, params);

                //ensures button goes back to the homepage after updating vehicle
                Intent IntentAdded = new Intent(UpdateActivity.this, MainActivity.class);
                startActivity(IntentAdded);
            }
        });
    }

    /**
     * This will perform a PUT call through connecting to the URL and then returning response on state of connection executes in parameters later
     * @param requestURL
     * @param postDataParams
     * @return resp, need response to to determine request has been accepted
     */
    public String performPutCall(String requestURL, HashMap<String,String> postDataParams) {
        String resp = ""; //initial empty response
        try {
            URL url = new URL(requestURL); //create the URL object, where URL will be passed
            HttpURLConnection conn = (HttpURLConnection) url.openConnection(); //create the connection object
            conn.setReadTimeout(15000); //timeout on waiting to read data is 15000 milliseconds
            conn.setConnectTimeout(15000); //if connection doesn't occur, timeout in 15000 milliseconds
            conn.setRequestMethod("PUT"); //invoke PUT statement when connecting with request (updating)
            conn.setDoInput(true);

            OutputStream os = conn.getOutputStream(); //write/send/UPDATE data to the connection using output stream and buffered writer
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8")); //set encoding with output stream writes text to character output
            writer.write(getPutDataString(postDataParams)); //write/send/UPDATE key/value data (url encoded) to the server

            writer.flush(); //clear the writer
            writer.close(); //close the writer
            os.close(); //close output stream

            int responseCode = conn.getResponseCode();

            if (responseCode == HttpsURLConnection.HTTP_OK) { //output response as empty
                Toast.makeText(this, "Success: Vehicle Updated :)", Toast.LENGTH_LONG).show(); //show vehicle inserted successful
                String line;
                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream())); //write text to character output with input in connection
                while ((line = br.readLine()) != null) { resp += line; }
            } else { //if vehicle insertion ws unsuccessful
                Toast.makeText(this, "ERROR: Failed To Update Vehicle :(", Toast.LENGTH_LONG).show(); //show vehicle inserted unsuccessful
                resp = ""; //output response as empty
            }
        } catch (Exception e) { e.printStackTrace(); }
        return resp;
    }

    /**
     * This method converts a HashMap to a URL query string of key/values pairs (e.g. : Make=BMW & Model=5-Series...)
     * @param params used as a key to return same elements as the hash map. and encoding operation
     * @return res.toString, return the result of the encoding of string
     * @throws UnsupportedEncodingException
     */
    private String getPutDataString(HashMap<String,String> params) throws UnsupportedEncodingException {
        StringBuilder res = new StringBuilder();
        boolean first = true;
        for (Map.Entry<String, String> entry : params.entrySet()) { //insert the hashMap key
            if (first) { first = false; }
            else { res.append("&"); }
            res.append(URLEncoder.encode(entry.getKey(), "UTF-8")); //ensure appropriate encoding for hashMap key
            res.append("=");
            res.append(URLEncoder.encode(entry.getValue(), "UTF-8")); //ensure appropriate encoding for hashMap value
        }
        return res.toString(); //return result in string format
    }
}