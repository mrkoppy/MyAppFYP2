package com.example.mrkoppy.myappfyp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

public class commentfromusers extends AppCompatActivity implements my_cmtfromuser_interface {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commentfromusers);

        BgWorker_availablecomments bgWorker_availablecomments = new BgWorker_availablecomments(commentfromusers.this, this);
        bgWorker_availablecomments.execute();

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.usercincat);
    }

    public void btn_returntoadminpanel(View view){
        Intent intent = new Intent(this,adminpanel.class);
        startActivity(intent);
    }

    @Override
    public void onClick(Post_admincomments post) {
        Log.i("UserID : ", post.UserID);
        Intent intent = new Intent(this, allusercommentsinsidedata.class);
        intent.putExtra("post", post);
        startActivity(intent);

    }


}
