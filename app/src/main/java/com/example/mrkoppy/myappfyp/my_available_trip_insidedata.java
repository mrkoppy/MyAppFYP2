package com.example.mrkoppy.myappfyp;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class my_available_trip_insidedata extends AppCompatActivity {
    TextView tv_origin,tv_destination,tv_estimatetime,tv_estimatecost;
    Button button_bs1,button_bs2,button_bs3,button_bs4,button_bs5,button_bs6;
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
        button_bs1 = (Button)findViewById(R.id.bs1);
        button_bs2 = (Button)findViewById(R.id.bs2);
        button_bs3 = (Button)findViewById(R.id.bs3);
        button_bs4 = (Button)findViewById(R.id.bs4);
        button_bs5 = (Button)findViewById(R.id.bs5);
        button_bs6 = (Button)findViewById(R.id.bs6);

        /*First check if seats left == 6 then in this loop*/
        if(post.Seats_left.equals("6")){
            /*Once done check then in this loop*/
            if(post.Seats_left.equals("6")){
                button_bs1.setBackgroundColor(Color.GREEN);
                button_bs2.setBackgroundColor(Color.GREEN);
                button_bs3.setBackgroundColor(Color.GREEN);
                button_bs4.setBackgroundColor(Color.GREEN);
                button_bs5.setBackgroundColor(Color.GREEN);
                button_bs6.setBackgroundColor(Color.GREEN);
            }
            else if(post.Seats_left.equals("5")){
                button_bs1.setBackgroundColor(Color.RED);
                button_bs2.setBackgroundColor(Color.GREEN);
                button_bs3.setBackgroundColor(Color.GREEN);
                button_bs4.setBackgroundColor(Color.GREEN);
                button_bs5.setBackgroundColor(Color.GREEN);
                button_bs6.setBackgroundColor(Color.GREEN);
            }
            else if(post.Seats_left.equals("4")){
                button_bs1.setBackgroundColor(Color.RED);
                button_bs2.setBackgroundColor(Color.RED);
                button_bs3.setBackgroundColor(Color.GREEN);
                button_bs4.setBackgroundColor(Color.GREEN);
                button_bs5.setBackgroundColor(Color.GREEN);
                button_bs6.setBackgroundColor(Color.GREEN);
            }
            else if(post.Seats_left.equals("3")){
                button_bs1.setBackgroundColor(Color.RED);
                button_bs2.setBackgroundColor(Color.RED);
                button_bs3.setBackgroundColor(Color.RED);
                button_bs4.setBackgroundColor(Color.GREEN);
                button_bs5.setBackgroundColor(Color.GREEN);
                button_bs6.setBackgroundColor(Color.GREEN);
            }
            else if(post.Seats_left.equals("2")){
                button_bs1.setBackgroundColor(Color.RED);
                button_bs2.setBackgroundColor(Color.RED);
                button_bs3.setBackgroundColor(Color.RED);
                button_bs4.setBackgroundColor(Color.RED);
                button_bs5.setBackgroundColor(Color.GREEN);
                button_bs6.setBackgroundColor(Color.GREEN);
            }
            else if(post.Seats_left.equals("1")){
                button_bs1.setBackgroundColor(Color.RED);
                button_bs2.setBackgroundColor(Color.RED);
                button_bs3.setBackgroundColor(Color.RED);
                button_bs4.setBackgroundColor(Color.RED);
                button_bs5.setBackgroundColor(Color.RED);
                button_bs6.setBackgroundColor(Color.GREEN);
            }
            else if(post.Seats_left.equals("0")){
                button_bs1.setBackgroundColor(Color.RED);
                button_bs2.setBackgroundColor(Color.RED);
                button_bs3.setBackgroundColor(Color.RED);
                button_bs4.setBackgroundColor(Color.RED);
                button_bs5.setBackgroundColor(Color.RED);
                button_bs6.setBackgroundColor(Color.RED);
            }
            /*if 4 car seats only*/
        } else if(post.Seats_left.equals("4")){
            if(post.Seats_left.equals("4")){
                button_bs1.setBackgroundColor(Color.GREEN);
                button_bs2.setBackgroundColor(Color.GREEN);
                button_bs3.setBackgroundColor(Color.GREEN);
                button_bs4.setBackgroundColor(Color.GREEN);
                button_bs5.setBackgroundColor(Color.BLACK);
                button_bs6.setBackgroundColor(Color.BLACK);
            }
            else if(post.Seats_left.equals("3")){
                button_bs1.setBackgroundColor(Color.RED);
                button_bs2.setBackgroundColor(Color.GREEN);
                button_bs3.setBackgroundColor(Color.GREEN);
                button_bs4.setBackgroundColor(Color.GREEN);
                button_bs5.setBackgroundColor(Color.BLACK);
                button_bs6.setBackgroundColor(Color.BLACK);
            }
            else if(post.Seats_left.equals("2")){
                button_bs1.setBackgroundColor(Color.RED);
                button_bs2.setBackgroundColor(Color.RED);
                button_bs3.setBackgroundColor(Color.GREEN);
                button_bs4.setBackgroundColor(Color.GREEN);
                button_bs5.setBackgroundColor(Color.BLACK);
                button_bs6.setBackgroundColor(Color.BLACK);
            }
            else if(post.Seats_left.equals("1")){
                button_bs1.setBackgroundColor(Color.RED);
                button_bs2.setBackgroundColor(Color.RED);
                button_bs3.setBackgroundColor(Color.RED);
                button_bs4.setBackgroundColor(Color.GREEN);
                button_bs5.setBackgroundColor(Color.BLACK);
                button_bs6.setBackgroundColor(Color.BLACK);
            }
            else if(post.Seats_left.equals("0")){
                button_bs1.setBackgroundColor(Color.RED);
                button_bs2.setBackgroundColor(Color.RED);
                button_bs3.setBackgroundColor(Color.RED);
                button_bs4.setBackgroundColor(Color.RED);
                button_bs5.setBackgroundColor(Color.BLACK);
                button_bs6.setBackgroundColor(Color.BLACK);
            }

        }

    }

    public void btn_book(View view){

    }

    public void btn_back(View view){
        Intent intent = new Intent(this, myrider.class);
        startActivity(intent);
    }
}
