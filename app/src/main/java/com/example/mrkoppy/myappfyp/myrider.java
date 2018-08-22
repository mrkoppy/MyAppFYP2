package com.example.mrkoppy.myappfyp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

public class myrider extends AppCompatActivity implements my_rider_booking_interface {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myrider);

        BgWorker_availableride bgWorker_availableride = new BgWorker_availableride(myrider.this, this);
        bgWorker_availableride.execute();

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.id_availableride);


        /*Static testing data*/
        /*ArrayList<Post_availabletrip> data = new ArrayList<>();
        data.add(new Post_availabletrip("Persiaran Multimedia, 63100 Cyberjaya, Selangor", "Persiaran Multimedia, 63100 Cyberjaya, Selangor", "18.10.18", "17:30"));
        data.add(new Post_availabletrip("Persiaran Multimedia, 63100 Cyberjaya, Selangor", "Persiaran Multimedia, 63100 Cyberjaya, Selangor", "18.10.18","17:30"));
        data.add(new Post_availabletrip("Persiaran Multimedia, 63100 Cyberjaya, Selangor", "Persiaran Multimedia, 63100 Cyberjaya, Selangor", "18.10.18","17:30"));
        data.add(new Post_availabletrip("Persiaran Multimedia, 63100 Cyberjaya, Selangor", "Persiaran Multimedia, 63100 Cyberjaya, Selangor", "18.10.18","17:30"));
        data.add(new Post_availabletrip("Persiaran Multimedia, 63100 Cyberjaya, Selangor", "Persiaran Multimedia, 63100 Cyberjaya, Selangor", "18.10.18","17:30"));

        Availabletrip_adapter adapter = new Availabletrip_adapter(this, data);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);*/
    }

    @Override
    public void onClick(Post_availabletrip post) {
        Log.i("Start Location : ", post.From);
        Intent intent = new Intent(this, my_available_trip_insidedata.class);
        intent.putExtra("post", post);
        startActivity(intent);

    }

    public void btn_redirect_main_menu_myrider(View view){
        Intent intent = new Intent(this, trip.class);
        startActivity(intent);
    }


}
