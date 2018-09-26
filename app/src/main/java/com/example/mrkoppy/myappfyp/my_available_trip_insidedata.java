package com.example.mrkoppy.myappfyp;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class my_available_trip_insidedata extends AppCompatActivity {
    TextView tv_origin,tv_destination,tv_estimatetime,tv_estimatecost,tvDrivername,tvGender,tvcarplate,tvdrivercarseat,tvstatus;
    Button button_bs1,button_bs2,button_bs3,button_bs4,button_bs5,button_bs6;
    private String type,Sname;
    /*String str_postfrom;*/
    private EditText name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_available_trip_insidedata);

        Post_availabletrip post = (Post_availabletrip) getIntent().getSerializableExtra("post");
        Log.i("sdsddsds", post.From);
        Log.i("ssssssssssss", String.valueOf(post.id));


        SharedPreferences sharedpre;
        sharedpre = this.getSharedPreferences("UserData", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpre.edit();
        editor.putString("RouteID", String.valueOf(post.id));
        editor.apply();

        tv_origin = (TextView)findViewById(R.id.tv_fromLocation);
        tv_origin.setText(post.From);
        tv_destination = (TextView)findViewById(R.id.tv_tolocation);
        tv_destination.setText(post.To);
        tv_estimatetime = (TextView)findViewById(R.id.tv_estimatetime);
        tv_estimatetime.setText(post.Est);
        tv_estimatecost = (TextView)findViewById(R.id.tv_estimatecost);
        tv_estimatecost.setText(post.Price);
        tvDrivername = (TextView)findViewById(R.id.tv_drivername) ;
        tvDrivername.setText(post.Name);
        tvGender = (TextView)findViewById(R.id.tv_gender);
        tvGender.setText(post.Gender);
        tvcarplate = (TextView)findViewById(R.id.tv_carplate);
        tvcarplate.setText(post.Carplate);
        tvdrivercarseat = (TextView)findViewById(R.id.owner_carseats_output);
        tvdrivercarseat.setText(post.Carseat);
        tvstatus = (TextView)findViewById(R.id.porg);
        tvstatus.setText(post.Status);
        button_bs1 = (Button)findViewById(R.id.bs1);
        button_bs2 = (Button)findViewById(R.id.bs2);
        button_bs3 = (Button)findViewById(R.id.bs3);
        button_bs4 = (Button)findViewById(R.id.bs4);
        button_bs5 = (Button)findViewById(R.id.bs5);
        button_bs6 = (Button)findViewById(R.id.bs6);

        /*First check if seats left == 6 then in this loop*/
        if(post.Carseat.equals("6")){
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
        }
        else if(post.Carseat.equals("4")){
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
            /*Once done check then in this loop*/
            /*if(post.Seats_left.equals("6")){
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
            }*/
            /*if 4 car seats only*/
        /*}*/ /*else if(post.Seats_left.equals("4")){
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

        }*/

    }

    public void btn_book(View view){
        type = "booking";

        BackgroundWorker backgroundWorker = new BackgroundWorker(this);
        backgroundWorker.execute(type);

    }

    public void btn_back(View view){
        Intent intent = new Intent(this, myrider.class);
        startActivity(intent);
    }

    public void btn_chatroomt(View view){


        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setTitle("Want to tell something to driver?");

        SharedPreferences sharedpre = getSharedPreferences("UserData", Context.MODE_PRIVATE);
        String value= sharedpre.getString("user_name", "Sorry");
        Log.d("result", value);
//        name = new EditText(this);
//        alertDialog.setView(name);
//        name.setText(value, TextView.BufferType.EDITABLE);
        alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
//                Sname = name.getText().toString();
                Intent intent = new Intent(my_available_trip_insidedata.this,Chatroom.class);
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


//        Intent intent = new Intent(this,Chatroom.class);
//        startActivity(intent);
    }
}
