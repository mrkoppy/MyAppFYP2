package com.example.mrkoppy.myappfyp;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class passengerOndeleteTrip extends AppCompatActivity {
    private String type,tripD,StripD;
    EditText tripid;
    TextView tripDs;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passenger_ondelete_trip);

        deleterouteID();
        tripDs = (TextView) findViewById(R.id.tripIDID);
    }

    public void PassengerEnsuredelete(View view){
        tripD = tripid.getText().toString();
        Log.i("SSSS",tripD);
        type = "passengerdeletetrip";

        BackgroundWorker backgroundWorker = new BackgroundWorker(passengerOndeleteTrip.this);
        backgroundWorker.execute(type,tripD);

    }

    public void deleterouteID(){
        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setTitle("Your route ID");

        SharedPreferences sharedpre = getSharedPreferences("UserData", Context.MODE_PRIVATE);
        String value= sharedpre.getString("RouteID", "Sorry");
        Log.d("result", value);
        tripid = new EditText(this);
        alertDialog.setView(tripid);
        tripid.setText(value, TextView.BufferType.EDITABLE);
        alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                StripD = tripid.getText().toString();
                tripDs.setText(StripD);
            }
        });

        alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
                deleterouteID();
            }
        });

        alertDialog.show();
    }
}
