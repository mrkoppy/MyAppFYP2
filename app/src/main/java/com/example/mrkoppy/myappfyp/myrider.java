package com.example.mrkoppy.myappfyp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class myrider extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myrider);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.id_availableride);

        ArrayList<Post_availabletrip> data = new ArrayList<>();
        data.add(new Post_availabletrip("Persiaran Multimedia, 63100 Cyberjaya, Selangor", "Persiaran Multimedia, 63100 Cyberjaya, Selangor", "18.10.18", "17:30"));
        data.add(new Post_availabletrip("Persiaran Multimedia, 63100 Cyberjaya, Selangor", "Persiaran Multimedia, 63100 Cyberjaya, Selangor", "18.10.18","17:30"));
        data.add(new Post_availabletrip("Persiaran Multimedia, 63100 Cyberjaya, Selangor", "Persiaran Multimedia, 63100 Cyberjaya, Selangor", "18.10.18","17:30"));
        data.add(new Post_availabletrip("Persiaran Multimedia, 63100 Cyberjaya, Selangor", "Persiaran Multimedia, 63100 Cyberjaya, Selangor", "18.10.18","17:30"));
        data.add(new Post_availabletrip("Persiaran Multimedia, 63100 Cyberjaya, Selangor", "Persiaran Multimedia, 63100 Cyberjaya, Selangor", "18.10.18","17:30"));

        Availabletrip_adapter adapter = new Availabletrip_adapter(this, data);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }


}
