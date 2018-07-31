package com.example.mrkoppy.myappfyp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class myvehicle extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myvehicle);
    }

    public void OnUpdate2(View view){

    }

    public void btn_redirectmainmenu2(View view){
        Intent intent = new Intent(this, trip.class);
        startActivity(intent);
    }

    public void btn_redirecttoprofiledetails2(View view){
        Intent intent = new Intent(this, myprofile.class);
        startActivity(intent);
    }

    public void btn_redirecttovehicledetails2(View view){
        Intent intent = new Intent(this, myvehicle.class);
        startActivity(intent);
    }
}
