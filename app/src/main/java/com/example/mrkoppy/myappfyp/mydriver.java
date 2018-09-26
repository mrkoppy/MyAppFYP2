package com.example.mrkoppy.myappfyp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class mydriver extends AppCompatActivity { ;
    private String type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mydriver);

    }

    public void mydriverbtmenu(View view){
        Intent intent = new Intent(this, trip.class);
        startActivity(intent);
    }

    public void createRoute(View view){

        Intent intent = new Intent(this, Driver_CrRoute.class);
        startActivity(intent);

    }

    public void driverdeleteRoute(View view){
        Intent intent = new Intent(this,ensuredeleteroute.class);
        startActivity(intent);

    }

    public void drivercurrentroute(View view){
        type = "drivergetroutedetails";

        BackgroundWorker backgroundWorker = new BackgroundWorker(mydriver.this);
        backgroundWorker.execute(type);
    }




}
