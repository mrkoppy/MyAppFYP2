package com.example.mrkoppy.myappfyp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class alluserdatainsidedata extends AppCompatActivity {
    TextView tv_username,tv_gender,tv_email,tv_mobile,tv_postcode,tv_studentID,tv_carplate,tv_carseats;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alluserdatainsidedata);

        Post_alluser post = (Post_alluser) getIntent().getSerializableExtra("post");
        Log.i("sdsddsds", post.Name);
        Log.i("ssssssssssss", String.valueOf(post.id));

        SharedPreferences sharedpre;
        sharedpre = this.getSharedPreferences("UserData", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpre.edit();
        editor.putString("UserID", String.valueOf(post.id));
        Log.i("UserID", "UserID");
        editor.apply();

        tv_username = (TextView)findViewById(R.id.tv_nameD);
        tv_username.setText(post.Name);
        tv_gender = (TextView)findViewById(R.id.tv_genderD);
        tv_gender.setText(post.Gender);
        tv_email = (TextView)findViewById(R.id.tv_emailD);
        tv_email.setText(post.Email);
        tv_mobile = (TextView)findViewById(R.id.tv_mobileD);
        tv_mobile.setText(post.Mobile);
        tv_postcode = (TextView)findViewById(R.id.tv_postcodeD) ;
        tv_postcode.setText(post.Postcode);
        tv_studentID = (TextView)findViewById(R.id.tv_studentIDD);
        tv_studentID.setText(post.StudentID);
        tv_carplate = (TextView)findViewById(R.id.tv_carplateD);
        tv_carplate.setText(post.Carplate);
        tv_carseats = (TextView)findViewById(R.id.tv_carseatsD);
        tv_carseats.setText(post.Carseats);

    }

    public void btn_back(View view){
        Intent intent = new Intent(this, adminpanel.class);
        startActivity(intent);
    }
}
