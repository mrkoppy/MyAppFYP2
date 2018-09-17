package com.example.mrkoppy.myappfyp;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Login extends AppCompatActivity {

    /*Create edittext variable for username and password*/
    private EditText UsernameEt, PasswordEt, UsernameReset;
    private String username,password,type,usernamereset;
    private TextView mplogin,mpreset;
    /*Login counter havent set !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!*/
    private int counter = 5;
    AlertDialog alertDialog1;
    Dialog resetDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        resetDialog = new Dialog(this);

        /*Put edittext information into their own variable*/
        UsernameEt = (EditText) findViewById(R.id.etUserName);
        PasswordEt = (EditText) findViewById(R.id.etPassword);
        mplogin = (TextView)findViewById(R.id.btnLogin);
        mpreset = (TextView)findViewById(R.id.btnForget);
        /*UsernameReset = (EditText)findViewById(R.id.usernameReset);*/


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

        public void Showresetpopup(View view){

            TextView txtclose;
            Button reset;
            resetDialog.setContentView(R.layout.activity_resetpassword);
            txtclose = (TextView) resetDialog.findViewById(R.id.Xclose);
            reset = (Button)resetDialog.findViewById(R.id.Resetpassword);
            txtclose.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    resetDialog.dismiss();
                }
            });
            resetDialog.show();

            reset.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    UsernameReset = (EditText)resetDialog.findViewById(R.id.usernameReset);
                    usernamereset = UsernameReset.getText().toString();
                    type = "reset";

                    BackgroundWorker backgroundWorker = new BackgroundWorker(Login.this);
                    backgroundWorker.execute(type, usernamereset);
                }
            });
        }

        /*From main activity page go to registrationID page*/
        public void openReg(View view){
            Intent intent = new Intent(this, RegistrationID.class);
            startActivity(intent);
        }

        /*public void btnForget(View view){
            Intent intent = new Intent(this,resetpassword.class);
            startActivity(intent);
        }*/




}
