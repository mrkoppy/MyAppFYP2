package com.example.mrkoppy.myappfyp;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class adminpanel extends AppCompatActivity implements my_alluser_interface {
    Dialog deletediaglog;
    public String type;
    EditText deleteuser;
    public String Deleteuser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminpanel);
        deletediaglog = new Dialog(this);


        BgWorker_availablealluser bgWorker_availablealluser = new BgWorker_availablealluser(adminpanel.this,this);
        bgWorker_availablealluser.execute();

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.alluserdata);
    }

    public void Logout(View view){
        Toast.makeText(this,"Log out successfully " , Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this,Login.class);
        startActivity(intent);
    }

    @Override
    public void onClick(Post_alluser post) {

    }

    public void deleteuser(View view){

        TextView txtclose;
        Button delete;
        deletediaglog.setContentView(R.layout.activity_alluserinsidedata);
        txtclose = (TextView) deletediaglog.findViewById(R.id.XcloseX);
        delete = (Button)deletediaglog.findViewById(R.id.deleteuser);
        txtclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deletediaglog.dismiss();
            }
        });
        deletediaglog.show();

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteuser = (EditText)deletediaglog.findViewById(R.id.typeinusername);
                Deleteuser = deleteuser.getText().toString();
                type = "deleteuser";

                BackgroundWorker backgroundWorker = new BackgroundWorker(adminpanel.this);
                backgroundWorker.execute(type, Deleteuser);
            }
        });
    }

}
