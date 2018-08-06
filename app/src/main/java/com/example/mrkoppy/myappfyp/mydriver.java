package com.example.mrkoppy.myappfyp;

import android.content.Intent;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;

public class mydriver extends AppCompatActivity {
    private SearchView mSearchView;
    private ListView mListView;
    private ArrayAdapter mAdapter;
    private String [] data = {"JB","KL","Kelantan","Perlis","Penang","Kuantan","Kedah"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mydriver);

        /*mListView = (ListView)findViewById(R.id.listview);
        final ArrayAdapter<String> mAdapter = new ArrayAdapter<String>(mydriver.this, android.R.layout.simple_list_item_1, data);
        mListView.setAdapter(mAdapter);
        mListView.setTextFilterEnabled(true);

        mSearchView = (SearchView) findViewById(R.id.mydriver_bar_search);
        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                mAdapter.getFilter().filter(newText);
                return false;
            }

        });*/
    }

    public void mydriverbtmenu(View view){
        Intent intent = new Intent(this, trip.class);
        startActivity(intent);
    }

    public void onOffer(View view){
        Intent intent = new Intent(this, mydriver.class);
        startActivity(intent);
    }

}
