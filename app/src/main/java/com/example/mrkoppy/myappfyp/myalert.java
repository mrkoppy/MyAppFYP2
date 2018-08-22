package com.example.mrkoppy.myappfyp;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class myalert extends  AppCompatActivity implements
        DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener{

    private Button btnPick;
    private int day,month,year,hour,minute;
    private int finalDay,finalMth,finalYear,finalHour,finalMinute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myalert);

        btnPick = (Button)findViewById(R.id.btn_reminder);

        btnPick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calender = Calendar.getInstance();
                year = calender.get(Calendar.YEAR);
                month = calender.get(Calendar.MONTH);
                day = calender.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(myalert.this,myalert.this,year,
                        month,day);
                datePickerDialog.show();

            }
        });

    }

    @Override
    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
        finalYear = i;
        finalMth = i1 + 1;
        finalDay = i2;

        Calendar c = Calendar.getInstance();
        hour = c.get(Calendar.HOUR_OF_DAY);
        minute = c.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog = new TimePickerDialog(myalert.this,myalert.this,hour,minute,
                DateFormat.is24HourFormat(this));
        timePickerDialog.show();

    }

    @Override
    public void onTimeSet(TimePicker timePicker, int i, int i1) {
        finalHour = i;
        finalMinute = i1;

        new Thread();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        finish();
        startActivity(getIntent());

        Toast.makeText(myalert.this, "Alert Have Been Set" + "\n" + "Year : " + finalYear + "\n" +
                "Month : " + finalMth + "\n" +
                "Day : " + finalDay + "\n" +
                "Hour : " + finalHour + "\n" +
                "Minute : " + finalMinute,Toast.LENGTH_SHORT).show();
        Log.i("Time information : ", "Year : " + finalYear + "Month : " + finalMth + "Day : " + finalDay +
        "Hour : " + finalHour + "Minute : " + finalMinute);


        Intent intent = new Intent(myalert.this, trip.class);
        startActivity(intent);


    }

/*    public void btnRedBackMMReminder(View view){
        Intent intent = new Intent(myalert.this, trip.class);
        startActivity(intent);
    }*/
}
