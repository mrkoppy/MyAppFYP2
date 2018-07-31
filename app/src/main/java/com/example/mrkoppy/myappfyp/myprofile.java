package com.example.mrkoppy.myappfyp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class myprofile extends AppCompatActivity {
    private EditText updateusername,updatepassword;
    private String usernameupdate,passwordupdate,type;
    private Button udupdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myprofile);
        updateusername = (EditText) findViewById(R.id.et_updateusername);
        updatepassword = (EditText) findViewById(R.id.et_updatepassword);
        udupdate = (Button)findViewById(R.id.btn_update);

    }

    public void OnUpdate(View view){
        usernameupdate = updateusername.getText().toString();
        passwordupdate = updatepassword.getText().toString();
        type = "update";

        BackgroundWorker backgroundWorker = new BackgroundWorker(this);
        backgroundWorker.execute(type, usernameupdate, passwordupdate);

        //validation();


    }
}
