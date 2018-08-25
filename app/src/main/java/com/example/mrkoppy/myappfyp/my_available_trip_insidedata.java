package com.example.mrkoppy.myappfyp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

public class my_available_trip_insidedata extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_available_trip_insidedata);

        Post_availabletrip post = (Post_availabletrip) getIntent().getSerializableExtra("post");
        Log.i("sdsddsds", post.From);
    }

    public void btn_book(View view){

    }

    public void btn_back(View view){
        Intent intent = new Intent(this, myrider.class);
        startActivity(intent);
    }
}
