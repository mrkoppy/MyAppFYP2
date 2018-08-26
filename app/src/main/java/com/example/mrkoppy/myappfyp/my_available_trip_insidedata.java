package com.example.mrkoppy.myappfyp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class my_available_trip_insidedata extends AppCompatActivity {
    TextView tv_origin,tv_destination,tv_estimatetime,tv_estimatecost;
    /*String str_postfrom;*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_available_trip_insidedata);

        Post_availabletrip post = (Post_availabletrip) getIntent().getSerializableExtra("post");
        Log.i("sdsddsds", post.From);

        tv_origin = (TextView)findViewById(R.id.tv_fromLocation);
        tv_origin.setText(post.From);
        tv_destination = (TextView)findViewById(R.id.tv_tolocation);
        tv_destination.setText(post.To);
        tv_estimatetime = (TextView)findViewById(R.id.tv_estimatetime);
        tv_estimatetime.setText(post.Est);
        tv_estimatecost = (TextView)findViewById(R.id.tv_estimatecost);
        tv_estimatecost.setText(post.Price);


    }

    public void btn_book(View view){

    }

    public void btn_back(View view){
        Intent intent = new Intent(this, myrider.class);
        startActivity(intent);
    }
}
