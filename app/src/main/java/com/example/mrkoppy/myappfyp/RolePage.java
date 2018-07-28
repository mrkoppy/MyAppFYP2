package com.example.mrkoppy.myappfyp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class RolePage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_role_page);
    }

    public void openDriverPage(View view){
        Intent intent = new Intent(this, trip.class);
        startActivity(intent);
    }

    public void openRiderPage(View view){
        Intent intent = new Intent(this, trip.class);
        startActivity(intent);
    }
}

