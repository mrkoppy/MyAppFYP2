package com.example.mrkoppy.myappfyp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class browsetriprecord extends AppCompatActivity {
    /**/
    private String type;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browsetriprecord);

        /*BgWorker_currenttrip bgWorker_currenttrip = new BgWorker_currenttrip(browsetriprecord.this);
        bgWorker_currenttrip.execute();

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.fetch_triprecord_CT);*/
    }

    public void onCurrenttrip(View view){
        type = "getcurrenttrip";

        BackgroundWorker backgroundWorker = new BackgroundWorker(browsetriprecord.this);
        backgroundWorker.execute(type);

//        BgWorker_currenttrip bgWorker_currenttrip = new BgWorker_currenttrip(browsetriprecord.this);
//        bgWorker_currenttrip.execute(type);

//        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.fetch_triprecord_CT);
    }

    public void btnmmm(View view){
        Intent intent = new Intent(this,trip.class);
        startActivity(intent);
    }

    public void gcomment(View view){
        Intent intent = new Intent(this,ratings_comment.class);
        startActivity(intent);
    }

    public void onDeleteTrip(View view){
        Intent intent = new Intent(this,passengerOndeleteTrip.class);
        startActivity(intent);
    }

}
