package com.example.mrkoppy.myappfyp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class myprofile extends AppCompatActivity {
    private EditText updateusername,updatepassword;
    private String usernameupdate,passwordupdate,type,yourname;
    private Button udupdate;
    private TextView youtusername;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myprofile);
        updateusername = (EditText) findViewById(R.id.et_updateusername);
        updatepassword = (EditText) findViewById(R.id.et_updatepassword);
        udupdate = (Button)findViewById(R.id.btn_renew);
        youtusername = (TextView)findViewById(R.id.currentusernameoutput);

        SharedPreferences sharedpre = getSharedPreferences("UserData", MODE_PRIVATE);
        yourname = sharedpre.getString("user_name","");
        youtusername.setText(yourname);

    }

    /*need to fixed some bug!!!!!!*/
    public void OnRenew(View view){
        usernameupdate = updateusername.getText().toString();
        passwordupdate = updatepassword.getText().toString();
        type = "renew";

        BackgroundWorker backgroundWorker = new BackgroundWorker(this);
        backgroundWorker.execute(type, usernameupdate, passwordupdate);

        //validation();

    }

    public void btn_redirectmainmenu1(View view){
        Intent intent = new Intent(this, trip.class);
        startActivity(intent);
    }

    public void btn_redirecttoprofiledetails(View view){
        Intent intent = new Intent(this,myprofile.class);
        startActivity(intent);
    }

    public void btn_redirecttovehicledetails(View view){
        Intent intent = new Intent(this,myvehicle.class);
        startActivity(intent);
    }
}
