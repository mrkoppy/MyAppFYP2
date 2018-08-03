package com.example.mrkoppy.myappfyp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.regex.Pattern;

public class RegistrationID extends AppCompatActivity {
    /*Created radiogroup,radiobutton,edittext variables*/
    RadioGroup gender;
    RadioButton radio_gender,radio_role;
    private EditText name,email,mobile,postcode,studentid,confirmpassword,password;
    private String str_gender,str_name,str_email,str_mobile,str_postcode,str_studentid,str_password,str_confirmpassword,type;

    /*Put radiogroup,radiobutton,edittext infromation into their own variables*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_id);
        gender = (RadioGroup) findViewById(R.id.radioGrp);
        name = (EditText)findViewById(R.id.et_name);
        email = (EditText)findViewById(R.id.et_email);
        mobile = (EditText)findViewById(R.id.et_mobile);
        postcode = (EditText)findViewById(R.id.et_postcode);
        studentid = (EditText)findViewById(R.id.et_studentid);
        password = (EditText) findViewById(R.id.et_password);
        confirmpassword = (EditText)findViewById(R.id.et_confirmpassword);
    }

    /*OnReg function for register button(btn_reg)*/
    public void OnReg(View view) throws InterruptedException {
        /*Check which one radio button press by users and pass str_gender_id into radio_gender variable*/
        /*Toast the content and display which gender selected by users, and show it on a jump frame*/
        /*pass the information in radio_gender into string(from int to string) because db declaration in String type*/
        int str_gender_id = gender.getCheckedRadioButtonId();
        radio_gender = findViewById(str_gender_id);
        Toast.makeText(this,"Your Selected Gender: " + radio_gender.getText(), Toast.LENGTH_LONG).show();
        str_gender = radio_gender.getText().toString();
        str_name = name.getText().toString();
        str_email = email.getText().toString();
        str_mobile = mobile.getText().toString();
        str_postcode = postcode.getText().toString();
        str_studentid = studentid.getText().toString();
        str_password = password.getText().toString();
        str_confirmpassword = confirmpassword.getText().toString();
        type = "register";

        if (!validate()){
            Toast.makeText(this,"Register has failed",Toast.LENGTH_SHORT).show();
            new Thread();
            Thread.sleep(5000);
            finish();
            startActivity(getIntent());
        }

        else {
            BackgroundWorker backgroundWorker = new BackgroundWorker(this);
            backgroundWorker.execute(type, str_gender, str_name,str_email,str_mobile,str_postcode,str_studentid,str_password);
        }



//        /*Log.d("aaa", "43434"); can check which part wrong */
    }

    public boolean validate(){
        boolean valid = true;
        if(str_name.isEmpty()||str_name.length()>10||str_name.length()<3){
            name.setError("Please Enter valid name");
            valid = false;
        }
        if(str_email.isEmpty()||!Patterns.EMAIL_ADDRESS.matcher(str_email).matches()){
            email.setError("Please Enter valid Email Address");
            valid = false;
        }
        if(str_mobile.isEmpty()||str_mobile.length()<10||str_mobile.length()>12 ){
            mobile.setError("Please Enter valid mobile number");
            valid = false;
        }
        if(str_postcode.isEmpty()||str_postcode.length()<4||str_postcode.length()>6){
            postcode.setError("Please Enter valid postcode number");
            valid = false;
        }
        if(str_studentid.isEmpty()||str_studentid.length()>10||str_studentid.length()<10){
            studentid.setError("Please Enter valid Student ID");
            valid = false;
        }
        if(str_password.isEmpty()){
            password.setError("Please Enter valid postcode number");
            valid = false;
        }
        if(str_confirmpassword.isEmpty()|| !str_confirmpassword.equals(str_password)){
            confirmpassword.setError("Please Enter valid postcode number");
            valid = false;
        }
        return valid;
    }

}
