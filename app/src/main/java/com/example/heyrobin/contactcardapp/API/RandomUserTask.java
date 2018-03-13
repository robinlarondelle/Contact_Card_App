package com.example.heyrobin.contactcardapp.API;

import android.os.AsyncTask;
import android.util.Log;

import com.example.heyrobin.contactcardapp.domain.Person;
import com.example.heyrobin.contactcardapp.Interfaces.OnRandomUserAvailable;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by HeyRobin on 11-3-2018.
 *
 * Class for getting the persons from Random API
 */

public class RandomUserTask extends AsyncTask<String, Void, String> {


    //Callback variable
    private OnRandomUserAvailable listener = null;
    private final String TAG = "RandomUserTask";


    //Constructor -> the requesting class tells this class that it's listening for any response
    public RandomUserTask(OnRandomUserAvailable listener)   {
        this.listener = listener;
        Log.d(TAG, "Listener set: " + this.listener.toString());
    }


    //doInBackground will be used to established a connection to the internet
    @Override
    protected String doInBackground(String... strings) {

        //inputStream
        InputStream inputStream = null;

        //Getting the responsCode from the conncection (404 - NOT FOUND for example)
        int responseCode = -1;

        //URL retrieved from .execute()
        String personURL = strings[0];
        Log.d(TAG, "PersonURL = " + personURL);

        //Result for return
        String response = "";


        //Setting up a connection
        try {

            //Create a new URL and opening the connection
            URL url = new URL(personURL);
            URLConnection urlConnection = url.openConnection();

            //Checking if the connection is not null
            if (!(urlConnection instanceof HttpURLConnection))  {
                return null;
            }

            //Establishing a HTTP connection
            HttpURLConnection httpURLConnection = (HttpURLConnection) urlConnection;
            httpURLConnection.setAllowUserInteraction(false);
            httpURLConnection.setInstanceFollowRedirects(true);
            httpURLConnection.setRequestMethod("GET");

            //Execute the request
            httpURLConnection.connect();

            //Check if the request succeeded
            responseCode = httpURLConnection.getResponseCode();
            Log.d(TAG, "responseCode = " + responseCode);

            if (responseCode == httpURLConnection.HTTP_OK)  {
                inputStream = httpURLConnection.getInputStream();
                response = getStringFromInputStream(inputStream);
                Log.d(TAG, "Response = " + response);

            } else  {
                Log.d(TAG, "ERROR, Invalid Response");
            }

        } catch(Exception e)    {
            e.printStackTrace();
            return null;
        }

        return response;
    }


    //OnPostExecute processes the result from the doOnBackground method
    @Override
    protected void onPostExecute(String response) {

        Log.d(TAG, "OnPostExecute response = " + response);

        //Check if the response is not null
        if (response == null || response.equals("")) {
            Log.d(TAG, "OnPostExecute received an empty response");
            return;
        }

        //The response is a JSON object
        JSONObject jsonObject;

        try {

            //Creating a new JSON object with the response
            jsonObject = new JSONObject(response);

            //Retrieving all users from the object "results"
            JSONArray users = jsonObject.getJSONArray("results");

            //Start a loop to get all the data
            for (int i = 0; i < users.length(); i++)    {

                //Make a new user object from the user at position i
                JSONObject user = users.getJSONObject(i);

                //Get the necessary data

                //getting all the data from current user from the "name" object
                JSONObject name = user.getJSONObject("name");
                String firstName = name.getString("first");
                String lastName = name.getString("last");
                String email = user.getString("email");
                String phoneNumber = user.getString("phone");
                String nationality = user.getString("nat");
                JSONObject location = user.getJSONObject("location");
                String street = location.getString("street");
                String city = location.getString("city");
                Log.d(TAG, "Got the following results: firstName: " + firstName +
                ", lastName: " + lastName + ", email: " + email + ", " + phoneNumber +
                ", nationality: " + nationality + ", street: " + street + ", city: " +
                        city);

                //getting all the data from current user from the "picute" object
                JSONObject picture = user.getJSONObject("picture");
                String imageURL = picture.getString("large");
                Log.d(TAG, "received the following imageURL: " + imageURL);


                //Create a new person object with the gathered data
                Person person = new Person(firstName, lastName, email, imageURL, street, city, nationality, phoneNumber);

                //create a callback with the new data
                listener.onRandomUserAvailable(person);
            }

        } catch(Exception e)    {
            e.printStackTrace();
        }

    }

    //Converting a inputStream to a String
    private static String getStringFromInputStream(InputStream is) {

        BufferedReader br = null;
        StringBuilder sb = new StringBuilder();

        String line;
        try {

            br = new BufferedReader(new InputStreamReader(is));
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return sb.toString();
    }

}
