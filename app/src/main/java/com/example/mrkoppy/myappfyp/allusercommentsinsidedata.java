package com.example.mrkoppy.myappfyp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class allusercommentsinsidedata extends AppCompatActivity {
    TextView tv_UserIDD, tv_RouteIDD, tv_overallstard, tv_commentDD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_allusercommentsinsidedata);

        Post_admincomments post_admincomments = (Post_admincomments) getIntent().getSerializableExtra("post");
        Log.i("UserID", post_admincomments.UserID);
        Log.i("RouteID", post_admincomments.RouteID);

        tv_UserIDD = (TextView) findViewById(R.id.tv_UserID);
        tv_UserIDD.setText(post_admincomments.UserID);
        tv_RouteIDD = (TextView) findViewById(R.id.tv_RouteID);
        tv_RouteIDD.setText(post_admincomments.RouteID);
        tv_overallstard = (TextView) findViewById(R.id.tv_Overallstar);
        tv_overallstard.setText(post_admincomments.Overallstar);
        tv_commentDD = (TextView) findViewById(R.id.tv_userComment);
        tv_commentDD.setText(post_admincomments.Comment);


    }

    public void btn_back(View view){
        Intent intent = new Intent(this, commentfromusers.class);
        startActivity(intent);
    }
}
