package com.example.heyrobin.contactcardapp.utilities;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.heyrobin.contactcardapp.R;
import com.example.heyrobin.contactcardapp.controllers.PersonDetailActivity;
import com.example.heyrobin.contactcardapp.domain.Person;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by HeyRobin on 7-3-2018.
 * Class made to fill the rows in the Adapter View with persons and their details
 */

public class PersonAdapter extends RecyclerView.Adapter<PersonAdapter.ViewHolder>{

    //Placing the data in a ArrayList
    private ArrayList<Person> mDataset;
    private String TAG = "PersonAdapter";
    private CircleImageView profilePicture;


    //Constructor
    public PersonAdapter(ArrayList<Person> mDataset, Context context)    {
        Log.d(TAG, "constructor, mDataset.size = " + mDataset.size());
        this.mDataset = mDataset;
    }


        //INNER CLASS


        //Referencing to a v for each data item
        public class ViewHolder
                extends RecyclerView.ViewHolder
                implements View.OnClickListener {


            //Variables
            public View v;
            public TextView mRowEmailTextView;
            public TextView mRowNameTextView;
            private String TAG = "PersonAdapter.ViewHolder";

            //Constructor
            public ViewHolder(View itemView) {
                super(itemView);

                //Tag
                Log.d(TAG, "Constructor called");

                this.v = itemView;

                //This class is a onClickListener, so we can reference this class
                this.v.setOnClickListener(this);

                //linking the variables with the XML
                mRowEmailTextView = (TextView) v.findViewById(R.id.row_email_textview);
                mRowNameTextView = (TextView) v.findViewById(R.id.row_name_textview);
                profilePicture = (CircleImageView) v.findViewById(R.id.row_person_icon);
            }


            //Defining what needs to happen on a click (Interface implementation)
            @Override
            public void onClick(View view) {

                //Log
                Log.d(TAG, "OnClick called");

                //Get the position of the current Adapter position
                int pos = getAdapterPosition();
                Log.d("ViewHolder", "onClick pos = " + pos);

                //Retrieving a new person based on the position of the adapter
                Person person = mDataset.get(pos);
                Log.d(TAG, "Person retrieved: " + person.toString());

                //Starting a new Intent
                Intent personDetailIntent = new Intent(
                        v.getContext().getApplicationContext(),
                        PersonDetailActivity.class);

                //Adding the additional information to the intent
                personDetailIntent.putExtra("PERSON", person);


                //Starting the next activity on click
                view.getContext().startActivity(personDetailIntent);
                Log.d(TAG, "PersonDetailActivity started");


            }
        }


    //Creating new views
    public PersonAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.d(TAG, "onCreateViewHolder called");

        //Create a new view
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        Log.d(TAG, "Inflater made");

        //Inflating the view with the content
        View view = layoutInflater.inflate(R.layout.list_row_item, parent, false);
        Log.d(TAG, "View made");

        //Creating a new ViewHolder (that defines what happens onclick) and returning it
        Log.d(TAG, "Returned ViewHolder");
        return new ViewHolder(view);

    }


    //Replacing the contents of a view once it is no longer used
    @Override
    public void onBindViewHolder(PersonAdapter.ViewHolder holder, int position) {
        // 1. get to the desired variable from the ViewHolder
        // 2. get the right person from the position
        // 3. replace the contents of the view with the new element from the new person

        Log.d(TAG, "onBindViewHolder called");

        Person person = mDataset.get(position);
        Log.d(TAG, "Person retrieved: " + person.toString());

        holder.mRowNameTextView.setText(person.fullName);
        holder.mRowEmailTextView.setText(person.email);
        Picasso.get().load(person.imageUrl).into(profilePicture);
        Log.d(TAG, "Variables set");
    }


    //Return the size of the dataset
    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}
