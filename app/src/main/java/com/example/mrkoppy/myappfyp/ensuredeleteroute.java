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

public class ensuredeleteroute extends AppCompatActivity {
    private String type,SrouteID,routeD;
    EditText routeid;
    TextView routeDs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ensuredeleteroute);
        deleterouteID();
        routeDs = (TextView) findViewById(R.id.routeIDID);
//        routeDs.setText(SrouteID);


    }

    public void Ensuredelete(View view){
        routeD = routeid.getText().toString();
        Log.i("SSSS",routeD);
        type = "driverdeleteroute";

        BackgroundWorker backgroundWorker = new BackgroundWorker(ensuredeleteroute.this);
        backgroundWorker.execute(type,routeD);

    }

    public void deleterouteID(){
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
                routeDs.setText(SrouteID);
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
