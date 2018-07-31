package com.example.mrkoppy.myappfyp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class trip extends AppCompatActivity {

    //side navigation menu for trip page
    private DrawerLayout drawerlayout;
    private ActionBarDrawerToggle abdt;
    private EditText headernameEt, headeremailEt;
    private TextView headernameTv,headeremailTv;
    private String nameTv,emailTv;

    /*drawerlistener for ActionBarDrawerToggle so that can click it
    setdrawerIndicatorEnabled top-left there change to original button and proceed can click
    * sync it so animation will start to move it
    * set home button can change back to original state*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trip);
        drawerlayout = (DrawerLayout)findViewById(R.id.dl);
        abdt = new ActionBarDrawerToggle(this,drawerlayout,R.string.Open,R.string.Close);
        abdt.setDrawerIndicatorEnabled(true);

        drawerlayout.addDrawerListener(abdt);
        abdt.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        NavigationView nav_view = (NavigationView)findViewById(R.id.nav_view);

        nav_view.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();

                if(id == R.id.myprofile){
                    Toast.makeText(trip.this,"My Profile",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(trip.this, myprofile.class);
                    startActivity(intent);
                }

                else if(id == R.id.triprecord){
                    Toast.makeText(trip.this,"Browse Trip Record",Toast.LENGTH_SHORT).show();
                }

                else if(id == R.id.chatroom){
                    Toast.makeText(trip.this,"Group Chatroom",Toast.LENGTH_SHORT).show();
                }

                else if(id == R.id.alert){
                    Toast.makeText(trip.this,"Alert",Toast.LENGTH_SHORT);
                }
                return true;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return abdt.onOptionsItemSelected(item) || super.onOptionsItemSelected(item);
    }

    public void onSwitchRole(View view){
        Intent intent = new Intent(this, RolePage.class);
        startActivity(intent);
    }
}
