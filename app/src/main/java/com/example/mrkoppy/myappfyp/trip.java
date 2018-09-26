package com.example.mrkoppy.myappfyp;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
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
    private TextView headernameTv,headeremailTv,userName,userEmail,Currentsession;
    private String nameTv,emailTv;

    /*drawerlistener for ActionBarDrawerToggle so that can click it
    setdrawerIndicatorEnabled top-left there change to original button and proceed can click
    * sync it so animation will start to move it
    * set home button can change back to original state*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trip);

        /**/
        SharedPreferences sharedpre = getSharedPreferences("UserData", MODE_PRIVATE);
        nameTv = sharedpre.getString("user_name","");
        /*emailTv = sharedpre.getString("user_email","");*/
        /**/

        drawerlayout = (DrawerLayout)findViewById(R.id.dl);
        abdt = new ActionBarDrawerToggle(this,drawerlayout,R.string.Open,R.string.Close);
        abdt.setDrawerIndicatorEnabled(true);

        drawerlayout.addDrawerListener(abdt);
        abdt.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        NavigationView nav_view = (NavigationView)findViewById(R.id.nav_view);

        /*need to be fixed header get username and email*/
        View headerView =  nav_view.inflateHeaderView(R.layout.navigation_header);
        nav_view.getHeaderView(0);
        /*headImg = (ImageView)headerView.findViewById(R.id.header_image);*/
        Currentsession = (TextView) headerView.findViewById(R.id.currentsession);
        Currentsession.setText(String.format("Welcome to Huuride: "));
        userName = (TextView) headerView.findViewById(R.id.tv_headername);
        userName.setText(nameTv);
        /*userEmail = (TextView) headerView.findViewById(R.id.tv_headeremail);
        userEmail.setText(emailTv);
*/

        nav_view.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                Fragment fragment = null;

         /*       if(id == R.id.mydriver){
                    Toast.makeText(trip.this,"Driver",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(trip.this, mydriver.class);
                    startActivity(intent);
                }

                else if(id == R.id.myrider){
                    Toast.makeText(trip.this,"Rider",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(trip.this, myrider.class);
                    startActivity(intent);
                }*/


                if(id == R.id.myprofile){
                    Toast.makeText(trip.this,"My Profile",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(trip.this, myprofile.class);
                    startActivity(intent);
                }

                else if(id == R.id.triprecord){
                    Toast.makeText(trip.this,"Browse Trip Record",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(trip.this, browsetriprecord.class);
                    startActivity(intent);
                }

                else if(id == R.id.chatroom){
                    Toast.makeText(trip.this,"Group Chatroom",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(trip.this,Chatroom.class);
                    startActivity(intent);
                }

                else if(id == R.id.alert){
                    Toast.makeText(trip.this,"Alert",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(trip.this, myalert.class);
                    startActivity(intent);
                }

                else if(id == R.id.logout){
                    Toast.makeText(trip.this,"User log out", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(trip.this,Login.class);
                    startActivity(intent);

                }
                return true;
            }
        });
    }

    public void btn_mainmenu_Rdriver (View view){
        Intent intent = new Intent(this, mydriver.class);
        startActivity(intent);
    }

    public void btn_mainmenu_Rrider (View view){
        Intent intent = new Intent(this, myrider.class);
        startActivity(intent);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return abdt.onOptionsItemSelected(item) || super.onOptionsItemSelected(item);
    }

    public void btn_fb(View view){
        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setTitle("Have any feedback? Chat with admin in admin room");

        SharedPreferences sharedpre = getSharedPreferences("UserData", Context.MODE_PRIVATE);
        String value= sharedpre.getString("user_name", "Sorry");
        Log.d("result", value);
        alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent = new Intent(trip.this,Chatroom.class);
                startActivity(intent);
            }
        });

        alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();

            }
        });

        alertDialog.show();
    }

}
