package com.example.heyrobin.contactcardapp.domain;

import android.os.AsyncTask;
import android.util.Log;

import com.example.heyrobin.contactcardapp.Interfaces.OnCreditsChangedListener;

/**
 * Created by HeyRobin on 5-3-2018.
 */

public class Credits extends AsyncTask<Void, Void, Void> {

    //Log Tag
    private final static String TAG = "Credits";

    //Counter values
    private int counter = 0;

    //The class which picks up the signal when a credit value has changed.
    //The class calling for a new Credits class, will be a OnCreditsChanged class
    private OnCreditsChangedListener listener;


    //Constructor
    public Credits (OnCreditsChangedListener activity)  {
        this.listener = activity;
    }


    //Setting the credits to a given value
    public void setCredits (int value)  {
        this.counter = value;
    }


    //Send the result back to the requesting Activity
    protected void onPostExecute (Void aVoid)   {
        Log.d(TAG, "OnPostExecute is called;");

        listener.onCreditsChanged(this.counter);
    }


    @Override
    protected Void doInBackground(Void... voids) {
        Log.d(TAG, "doInBackground is called.");

        // This piece of method is to test the async principle

//        try {
//            Thread.sleep(4000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        counter++;
        return null;
    }
}
