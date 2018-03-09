package com.example.heyrobin.contactcardapp.controllers;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.heyrobin.contactcardapp.R;
import com.example.heyrobin.contactcardapp.domain.Color;

import de.hdodenhof.circleimageview.CircleImageView;

public class PersonDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //Standard code
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_detail);


        //custom code

        //retrieving the bundle
        Bundle extras = getIntent().getExtras();

        //setting the extras from the bundle to the textviews
        String name = extras.getString("PERSON_NAME");
        String email = extras.getString("PERSON_EMAIL");

        TextView textViewPersonName = (TextView) findViewById(R.id.person_details_name);
        TextView textViewPersonEmail = (TextView) findViewById(R.id.person_details_email);
        CircleImageView profilePhoto = (CircleImageView) findViewById(R.id.person_details_image);

        textViewPersonName.setText(name);
        textViewPersonEmail.setText(email);
        profilePhoto.setBackgroundColor(new Color().createRandomColor());

    }
}
