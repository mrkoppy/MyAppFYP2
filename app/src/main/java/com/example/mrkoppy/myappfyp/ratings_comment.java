package com.example.mrkoppy.myappfyp;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;

public class ratings_comment extends AppCompatActivity {
    private RatingBar ratingBar;
    private TextView yourratingspoint;
    private Button submitbutton;
    EditText routeid;
    private String SrouteID,Yourratingspoint,Comment,type;
    EditText comment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ratings_comment);

        requestrouteID();
        addListenerOnRatingBar();
//        addListenerOnButton();

        comment = (EditText)findViewById(R.id.commentsection);
        ratingBar = (RatingBar) findViewById(R.id.ratingBar);
        submitbutton = (Button) findViewById(R.id.btnSubmit);
    }

    public void addListenerOnRatingBar() {

        ratingBar = (RatingBar) findViewById(R.id.ratingBar);
        yourratingspoint = (TextView) findViewById(R.id.point);

        //if rating value is changed,
        //display the current rating value in the result (textview) automatically
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            public void onRatingChanged(RatingBar ratingBar, float rating,
                                        boolean fromUser) {

                yourratingspoint.setText(String.valueOf(rating));

            }
        });
    }

//    public void addListenerOnButton() {
//
//
//
//        //if click on me, then display the current rating value.
//        submitbutton.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//
//                Toast.makeText(ratings_comment.this,
//                        String.valueOf(ratingBar.getRating()),
//                        Toast.LENGTH_SHORT).show();
//
//            }
//
//        });
//
//    }

    public void requestrouteID(){
        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setTitle("Your route ID");

        SharedPreferences sharedpre = getSharedPreferences("UserData", Context.MODE_PRIVATE);
        String value= sharedpre.getString("RouteID", "Sorry");
        Log.d("result", value);
        routeid = new EditText(this);
        alertDialog.setView(routeid);
        routeid.setText(value, TextView.BufferType.EDITABLE);
        alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                SrouteID = routeid.getText().toString();
            }
        });

        alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
                requestrouteID();
            }
        });

        alertDialog.show();
    }

    public void btnmmm(View view){
        Intent intent = new Intent(this,trip.class);
        startActivity(intent);
    }

    public void btnSubmit(View view){
        SrouteID = routeid.getText().toString();
        Comment = comment.getText().toString();
        Yourratingspoint = yourratingspoint.getText().toString();
        type = "Commentnratings";

        BackgroundWorker backgroundWorker = new BackgroundWorker(this);
        backgroundWorker.execute(type, SrouteID, Comment,Yourratingspoint);
    }


}
