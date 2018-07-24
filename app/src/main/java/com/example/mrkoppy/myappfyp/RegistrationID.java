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

public class RegistrationID extends AppCompatActivity {
    /*Created radiogroup,radiobutton,edittext variables*/
    RadioGroup gender,role;
    RadioButton radio_gender,radio_role;
    private EditText name,email,mobile,postcode,studentid,confirmpassword,password;
    private String str_gender,str_name,str_email,str_mobile,str_postcode,str_studentid,str_password,str_confirmpassword,str_role,type;

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
        role = (RadioGroup)findViewById(R.id.radioGrp1);

    }

    /*OnReg function for register button(btn_reg)*/
    public void OnReg(View view) {
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
        int str_role_id = role.getCheckedRadioButtonId();
        radio_role = findViewById(str_role_id);
        Toast.makeText(this,"Your Selected Role: " + radio_role.getText(),Toast.LENGTH_LONG).show();
        str_role = radio_role.getText().toString();
        type = "register";

        BackgroundWorker backgroundWorker = new BackgroundWorker(this);
        backgroundWorker.execute(type, str_gender, str_name,str_email,str_mobile,str_postcode,str_studentid,str_password,str_role);

//        /*Log.d("aaa", "43434"); can check which part wrong */
    }

    public void register(){

    }

    public void initialize(){

    }

    public void storedatatobgworker(){

    }

}
