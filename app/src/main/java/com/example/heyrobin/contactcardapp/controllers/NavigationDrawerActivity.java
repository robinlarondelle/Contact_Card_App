package com.example.heyrobin.contactcardapp.controllers;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.heyrobin.contactcardapp.utilities.PersonAdapter;
import com.example.heyrobin.contactcardapp.R;
import com.example.heyrobin.contactcardapp.domain.Person;

import java.util.ArrayList;

/*  Activity Responible for the display of persons in a list, and for the navigation to other
    Activities.
 */

public class NavigationDrawerActivity
        extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    //Creating variables for the RecyclerView
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //Standard Code
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_drawer);

        //Creating the toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Creating the floating button with snackbar
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Button Pressed", Snackbar.LENGTH_LONG).setAction("Action", null).show();
            }
        });

        //Creating the drawer
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        //Creating the navigation v
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        //CUSTOM CODE


        //Generating some persons
        ArrayList<Person> persons = new ArrayList<>();
        for(int i = 0; i < 10; i++) {
            persons.add(new Person("Robin Schellius", 49, "r.schellius@avans.nl"));
            persons.add(new Person("Henk Jansen", 49, "h.jansen@avans.nl"));
            persons.add(new Person("Kees Jansen", 49, "k.jansen@avans.nl"));
            persons.add(new Person("Piet Jansen", 49, "p.jansen@avans.nl"));
            persons.add(new Person("Marieke Jansen", 49, "m.jansen@avans.nl"));
        }



        //Creating the recycler v
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);


        //improving performance by defining the fixed size of the RecycleView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager. "this" defines that the lineair layout needs to be used
        // in this class, this activity. This is the context in which the layout needs to run
//        LinearLayoutManager llm = new LinearLayoutManager(this);
//        llm.setOrientation(LinearLayoutManager.VERTICAL);


        mLayoutManager = new LinearLayoutManager(this);


        mRecyclerView.setLayoutManager(mLayoutManager);

        // defining what adapter to use in the Recycler View
        mAdapter = new PersonAdapter(persons);
        mRecyclerView.setAdapter(mAdapter);

    }


    //Defining what happens when the back button is pressed
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    //Adding items to the options menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.navigation_drawer, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent settingsIntent = new Intent(getApplicationContext(), SettingsActivity.class);
            startActivity(settingsIntent);
        }

        return super.onOptionsItemSelected(item);
    }


    //What should be opened when an item is clicked in the drawer menu?
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {


        // Handle navigation v item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_counter) {

            //Starting an intent to target your selected activity
            Intent mainIntent = new Intent(getApplicationContext(), MainActivity.class);

            //Establishing the connetion between this activity and the targeted
            startActivity(mainIntent);

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
