package com.example.heyrobin.contactcardapp.controllers;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.heyrobin.contactcardapp.R;
import com.example.heyrobin.contactcardapp.domain.Person;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class PersonDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //Standard code
        super.onCreate(savedInstanceState);
        setContentView(R.layout.person_detailed);



        //CUSTOM CODE

        //retrieving the bundle
        Bundle extras = getIntent().getExtras();

        //Retrieving the keys from the bundle
        Person person = (Person) extras.get("PERSON");

        String name = person.fullName;
        String email = person.email;
        String streetName = person.streetName;
        String city = person.city;
        String country = person.country;
        String phonenumber = person.phoneNumber;
        String imageUrl = person.imageUrl;

        //All the textviews
        TextView  nameLargeTextView = (TextView) findViewById(R.id.person_details_name);
        TextView  nameSmallTextView = (TextView) findViewById(R.id.person_detailed_person_name_small);
        TextView  emailTextView = (TextView) findViewById(R.id.person_detailed_email);
        TextView  addressTextView = (TextView) findViewById(R.id.person_detailed_address);
        TextView  countryTextView = (TextView) findViewById(R.id.person_detailed_country);
        TextView  phoneTextView = (TextView) findViewById(R.id.person_detailed_phone);
        ImageView backgroundImageView = (ImageView) findViewById(R.id.person_details_background);
        CircleImageView profilePicture = (CircleImageView) findViewById(R.id.person_details_image);

        //Setting all the values
        nameLargeTextView.setText(name);
        nameSmallTextView.setText(name);
        emailTextView.setText(email);
        addressTextView.setText(streetName + ", " + city);
        countryTextView.setText(country);
        phoneTextView.setText(phonenumber);

        Picasso.get().load(imageUrl).into(profilePicture);

    }
}
