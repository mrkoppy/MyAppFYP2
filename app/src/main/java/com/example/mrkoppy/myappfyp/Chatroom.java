package com.example.mrkoppy.myappfyp;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Chatroom extends AppCompatActivity {

    private String Sname,chatroomname,type;
    DatabaseReference references;
    EditText editText, name;
    ListView listView;
    ArrayList<String> list;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chatroom);
        editText = (EditText)findViewById(R.id.createChatroom);
        listView = (ListView)findViewById(R.id.listView) ;
        list = new ArrayList<>();

        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,list);

        listView.setAdapter(adapter);
        references = FirebaseDatabase.getInstance().getReference().getRoot();
        requestname();


        references.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Set<String> mset = new HashSet<String>();
                Iterator i = dataSnapshot.getChildren().iterator();
                while(i.hasNext()){
                    mset.add(((DataSnapshot) i.next()).getKey());
                }

                list.clear();
                list.addAll(mset);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(),"Try again, your network may facing problem",Toast.LENGTH_LONG).show();
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(Chatroom.this,Insidechatroom.class);
                intent.putExtra("Roomname", ((TextView) view).getText().toString());
                intent.putExtra("Username", Sname);
                startActivity(intent);
            }
        });

    }

    /*request name from users*/
    /*must type in if press cancel then diaglog keep showing*/
    public void requestname(){
        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setTitle("Enter Your Name");

        SharedPreferences sharedpre = getSharedPreferences("UserData", Context.MODE_PRIVATE);
        String value= sharedpre.getString("user_name", "Sorry");
        Log.d("result", value);
        name = new EditText(this);
        alertDialog.setView(name);
        name.setText(value, TextView.BufferType.EDITABLE);
        alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                    Sname = name.getText().toString();
            }
        });

        alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
               @Override
               public void onClick(DialogInterface dialogInterface, int i) {
                   dialogInterface.cancel();
                   requestname();
               }
           });

        alertDialog.show();
    }


    /*Get created chatroom name and hash it to firebase*/
    /*Update real time to firebase */
    public void create_chatroom(View view){
        HashMap<String,Object> hashMap = new HashMap<>();
        hashMap.put(editText.getText().toString(),"");
        references.updateChildren(hashMap);

        list.add(editText.getText().toString());
        adapter.notifyDataSetChanged();

        chatroomname = editText.getText().toString();
        type = "chatroom";

        BackgroundWorker backgroundWorker = new BackgroundWorker(this);
        backgroundWorker.execute(type, chatroomname);

    }

    public void btnreturnmainmenu(View view){
        Intent intent = new Intent(Chatroom.this,trip.class);
        startActivity(intent);
    }
}
