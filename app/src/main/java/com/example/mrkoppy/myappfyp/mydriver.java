package com.example.mrkoppy.myappfyp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class mydriver extends AppCompatActivity { ;

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
        /*String type = "create_route";
        Bgworker_vehicle bgworker_vehicle = new Bgworker_vehicle(mydriver.this);
        bgworker_vehicle.execute();*/

        /*if () ;*/
        Intent intent = new Intent(this, Driver_CrRoute.class);
        startActivity(intent);
    }

    public void btn_ratings(View view){
        Intent intent = new Intent(this, ratings_comment.class);
        startActivity(intent);
    }


}
