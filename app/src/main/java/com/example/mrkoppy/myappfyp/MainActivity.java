package com.example.mrkoppy.myappfyp;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    /*Create edittext variable for username and password*/
    private EditText UsernameEt, PasswordEt;
    private String username,password,type;
    private Button mplogin;
    /*Login counter havent set !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!*/
    private int counter = 5;
    AlertDialog alertDialog1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*Put edittext information into their own variable*/
        UsernameEt = (EditText) findViewById(R.id.etUserName);
        PasswordEt = (EditText) findViewById(R.id.etPassword);
        mplogin = (Button)findViewById(R.id.btnLogin);

    }

        /*OnLogin function for Login button(btnLogin)*/
        /*Create new instance for backgroundworker and assign constructor's information into it*/
        /*Execute backgroundworker(type of action such as login or register, username, password)*/
        /*Follow AsyncTask's parameter<"String",Void,Void>*/
        /*doInBackground("String"...params),must read in String type*/
        public void OnLogin(View view){
            username = UsernameEt.getText().toString();
            password = PasswordEt.getText().toString();
            type = "login";

            BackgroundWorker backgroundWorker = new BackgroundWorker(this);
            backgroundWorker.execute(type, username, password);

            //validation();


        }

        /*private void validation(String mpusername,String mppassword){
             if((mpusername.equals("Admin")) & (mppassword.equals("1234"))){
                 Intent intent = new Intent(this,RolePage.class);
                 //alertDialog1.setTitle("Yes");
                 startActivity(intent);


             } else {
                 counter--;

                 if(counter == 0){
                     mplogin.setEnabled(false);
                     //alertDialog1.setTitle("Wrong");
                 }
             }
        }*/

        /*From main activity page go to registrationID page*/
        public void openReg(View view){
            Intent intent = new Intent(this, RegistrationID.class);
            startActivity(intent);
        }





}
