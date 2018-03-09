package com.example.heyrobin.contactcardapp.controllers;

import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.heyrobin.contactcardapp.Interfaces.OnCreditsChangedListener;
import com.example.heyrobin.contactcardapp.R;
import com.example.heyrobin.contactcardapp.domain.Credits;


/* Activity Responsible for the counter screen */


public class MainActivity
        extends AppCompatActivity

        /*
         *View.OnClickListener means this class is able to listen to certain clicks
         *OnCreditsChangedListener means that this class will be able to listen and take action
         *With a click from a credits button
         */
        implements View.OnClickListener, OnCreditsChangedListener {


    //Global access to Textvield, button, and color
    private TextView counterTextView;
    private Button colourButton;

    //Log tag
    private final static String TAG = "Main_Activity";

    private int counter = 0;


    //What happens when the activity is created?
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //Standard code
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Log
        Log.d(TAG, "OnCreate initiated");

        //Linking the XML to the activity
        counterTextView = (TextView) findViewById(R.id.counter_textview);
        colourButton = (Button) findViewById(R.id.colour_button);
        Button counterButton = (Button) findViewById(R.id.count_button);

        //Setting the listeners on the buttons. This class is a onClickListener
        colourButton.setOnClickListener(this);
        counterButton.setOnClickListener(this);
    }


    //Interface Method of OnClick: Defining what happens when something is clicked
    @Override
    public void onClick(View view) {

        //Log
        Log.d(TAG, "onClick called");

        //Retrieve the ID from the pressed button
        int id = view.getId();

        switch (id) {

            //If the pressed button is the colour button, then te colour will be changed
            case R.id.colour_button:

                //Log
                Log.d(TAG, "Colour Button pressed");

                //Assigning a random colour to a value
                int randomColour = new com.example.heyrobin.contactcardapp.domain.Color().createRandomColor();

                //Assigning the random colour to the button and the textview
                counterTextView.setBackgroundColor(randomColour);
                colourButton.setBackgroundColor(randomColour);
                break;

            case R.id.count_button:

                //Log
                Log.d(TAG, "Counter button pressed");

                //Creating a new Credits class with this class as listener
                Credits credits = new Credits(this);

                credits.setCredits(this.counter);
                credits.execute();
                break;
        }
    }


    //Interface method of OnCreditsChangedListener: Defining what happens when credits changed
    @Override
    public void onCreditsChanged(int value) {

        //Log
        Log.d(TAG, "OnCreditsChanged was called");

        //Setting the amount of credits from the given value to the value of this activity
        counter = value;
        counterTextView.setText("" + counter);
    }




    //Defining what happens when the state changes:

    @Override
    protected void onStart() {

        //Log
        Log.d(TAG, "onStart was called!");

        //Starting the class
        super.onStart();
    }

    @Override
    protected void onStop() {

        //Log
        Log.d(TAG, "onStop was called");


        super.onStop();
    }

    @Override
    protected void onDestroy() {

        //Log
        Log.d(TAG, "onDestroy was called");


        super.onDestroy();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {

        //Log
        Log.d(TAG, "onSaveInstanceState was called");

        //Define what needs to be saved
        //Save the state from the counter in a Map (Key with Value)
        outState.putInt("COUNTER", this.counter);

        //Retrieving the background colour of the button
        if (colourButton.getBackground() instanceof ColorDrawable) {
            ColorDrawable colorDrawable = (ColorDrawable) colourButton.getBackground();
            outState.putInt("COLOUR", colorDrawable.getColor());
        }

        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {

        super.onRestoreInstanceState(savedInstanceState);

        //Retrieve the saved values from the Bundle with the key
        this.counter = savedInstanceState.getInt("COUNTER");
        this.colourButton.setBackgroundColor(savedInstanceState.getInt("COLOUR"));
        this.counterTextView.setBackgroundColor(savedInstanceState.getInt("COLOUR"));


        //Log
        Log.d(TAG, "onRestoreInstanceState was called: Counter = " + counter);

    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause is aangeroepen.");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume is aangeroepen.");
        counterTextView.setText("" + this.counter);
    }
}
