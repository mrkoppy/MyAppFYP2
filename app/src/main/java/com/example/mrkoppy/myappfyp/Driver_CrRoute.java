package com.example.mrkoppy.myappfyp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Driver_CrRoute extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver__cr_route);
    }

    public void btn_redirect_main_menu_driver_cr_route(View view){
        Intent intent = new Intent(this, trip.class);
        startActivity(intent);
    }
}
