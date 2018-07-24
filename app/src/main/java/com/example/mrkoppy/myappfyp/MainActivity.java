package com.example.mrkoppy.myappfyp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    /*Create edittext variable for username and password*/
    EditText UsernameEt, PasswordEt;
    String username,password,type;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*Put edittext information into their own variable*/
        UsernameEt = (EditText) findViewById(R.id.etUserName);
        PasswordEt = (EditText) findViewById(R.id.etPassword);
    }

        /*OnLogin function for Login button(btnLogin)*/
        public void OnLogin(View view){
            username = UsernameEt.getText().toString();
            password = PasswordEt.getText().toString();
            type = "login";

            if(UsernameEt.getText().toString().equals(username) && PasswordEt.getText().toString().equals(password) )  {
                Intent intent = new Intent(this,RolePage.class);
                startActivity(intent);
            }
            else {

            }

            /*Create new instance for backgroundworker and assign constructor's information into it*/
            /*Execute backgroundworker(type of action such as login or register, username, password)*/
            /*Follow AsyncTask's parameter<"String",Void,Void>*/
            /*doInBackground("String"...params),must read in String type*/
            BackgroundWorker backgroundWorker = new BackgroundWorker(this);
            backgroundWorker.execute(type, username, password);


        }

        /*From main activity page go to registrationID page*/
        public void openReg(View view){
            Intent intent = new Intent(this, RegistrationID.class);
            startActivity(intent);
        }





}
