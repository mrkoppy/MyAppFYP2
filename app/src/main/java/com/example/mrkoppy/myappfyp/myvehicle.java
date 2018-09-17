package com.example.mrkoppy.myappfyp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class myvehicle extends AppCompatActivity {
    private EditText Et_carplate,Et_carseats;
    private Button udvehicle;
    private String carplateEt,carseatsEt,type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myvehicle);
        Et_carplate = (EditText)findViewById(R.id.et_carplate);
        Et_carseats = (EditText)findViewById(R.id.et_carseats);
        udvehicle = (Button)findViewById(R.id.btn_update2);
    }

    public void OnUpdate2(View view){
        carplateEt = Et_carplate.getText().toString();
        carseatsEt = Et_carseats.getText().toString();

        type = "update";

        BackgroundWorker backgroundWorker = new BackgroundWorker(this);
        backgroundWorker.execute(type, carplateEt, carseatsEt);

    }

    public void btn_redirectmainmenu2(View view){
        Intent intent = new Intent(this, trip.class);
        startActivity(intent);
    }

    public void btn_redirecttoprofiledetails2(View view){
        Intent intent = new Intent(this, myprofile.class);
        startActivity(intent);
    }

    public void btn_redirecttovehicledetails2(View view){
        Intent intent = new Intent(this, myvehicle.class);
        startActivity(intent);
    }
}
