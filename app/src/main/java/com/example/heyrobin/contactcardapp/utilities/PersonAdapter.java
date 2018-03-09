package com.example.heyrobin.contactcardapp.utilities;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.heyrobin.contactcardapp.R;
import com.example.heyrobin.contactcardapp.controllers.PersonDetailActivity;
import com.example.heyrobin.contactcardapp.domain.Person;

import java.util.ArrayList;

/**
 * Created by HeyRobin on 7-3-2018.
 */

public class PersonAdapter extends RecyclerView.Adapter<PersonAdapter.ViewHolder>{

    //Placing the data in a ArrayList
    private ArrayList<Person> mDataset;


    //Constructor
    public PersonAdapter(ArrayList<Person> mDataset)    {
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
            //public ImageView favoriteIcon;


            //Constructor
            public ViewHolder(View itemView) {
                super(itemView);
                this.v = itemView;

                //This class is a onClickListener, so we can reference this class
                this.v.setOnClickListener(this);

                //linking the variables with the XML
                mRowEmailTextView = (TextView) v.findViewById(R.id.row_email_textview);
                mRowNameTextView = (TextView) v.findViewById(R.id.row_name_textview);
            }


            //Defining what needs to happen on a click (Interface implementation)
            @Override
            public void onClick(View view) {

                //Get the position of the current Adapter position
                int pos = getAdapterPosition();

                //Retrieving a new person based on the position of the adapter
                Person person = mDataset.get(pos);

                //Starting a new Intent
                Intent personDetailIntent = new Intent(
                        v.getContext().getApplicationContext(),
                        PersonDetailActivity.class);

                //Adding the additional information to the intent
                personDetailIntent.putExtra("PERSON_NAME", person.name);
                personDetailIntent.putExtra("PERSON_EMAIL", person.emailadress);

                //Starting the next activity on click
                view.getContext().startActivity(personDetailIntent);


            }
        }


    //Creating new views
    public PersonAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        //Create a new view
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());

        //Inflating the view with the content
        View view = layoutInflater.inflate(R.layout.list_row_item, parent, false);

        //Creating a new ViewHolder (that defines what happens onclick) and returning it
        return new ViewHolder(view);

    }


    //Replacing the contents of a view once it is no longer used
    @Override
    public void onBindViewHolder(PersonAdapter.ViewHolder holder, int position) {

        // 1. get to the desired variable from the ViewHolder
        // 2. get the right person from the position
        // 3. replace the contents of the view with the new element from the new person


        holder.mRowNameTextView.setText(mDataset.get(position).name);
        holder.mRowEmailTextView.setText(mDataset.get(position).emailadress);
    }


    //Return the size of the dataset
    @Override
    public int getItemCount() {
        return 0;
    }
}
