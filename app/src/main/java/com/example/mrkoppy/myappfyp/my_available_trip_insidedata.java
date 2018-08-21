package com.example.mrkoppy.myappfyp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class my_available_trip_insidedata extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_available_trip_insidedata);

        Post_availabletrip post = (Post_availabletrip) getIntent().getSerializableExtra("post");
        Log.i("sdsddsds", post.From);
    }

}
