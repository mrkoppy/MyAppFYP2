package com.example.mrkoppy.myappfyp;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class myalert extends Activity{


    /*Button setTime,scheduleEvent;*/
    Button oneday,threeday,sevenday;
    TextView txtmm;
    /*static final int DATEPICKER_DIALOG_ID = 0;
    static final int TIMEPICKER_DIALOG_ID = 1;*/
    /*int finalYear, finalMonth, finalDay, finalHour, finalMinute;*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myalert);

        final Calendar calendar = Calendar.getInstance();
        /*finalYear = calendar.get(Calendar.YEAR);
        finalMonth = calendar.get(Calendar.MONTH);
        finalDay = calendar.get(Calendar.DAY_OF_MONTH);
        finalHour = calendar.get(Calendar.HOUR_OF_DAY);
        finalMinute = calendar.get(Calendar.MINUTE);*/

        /*setTime = (Button) findViewById(R.id.btn_reminder);
        scheduleEvent = (Button) findViewById(R.id.Time);*/

        oneday = (Button)findViewById(R.id.ringoneday);
        threeday = (Button)findViewById(R.id.ringthreeday);
        sevenday = (Button)findViewById(R.id.ringsevenday);
        txtmm = (TextView)findViewById(R.id.mmrmdbefore);

        /*setTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog(DATEPICKER_DIALOG_ID);
            }
        });

        scheduleEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(myalert.this,AlertRing.class);
                final PendingIntent pendingIntent = PendingIntent.getBroadcast(myalert.this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
                final AlarmManager alarmMgr = (AlarmManager) (myalert.this.getSystemService(Context.ALARM_SERVICE));

                final Calendar cal = Calendar.getInstance();
                cal.setTimeInMillis(System.currentTimeMillis());
                cal.set(Calendar.YEAR, finalYear);
                cal.set(Calendar.MONTH, finalMonth);
                cal.set(Calendar.DAY_OF_MONTH, finalDay);
                cal.set(Calendar.HOUR_OF_DAY, finalHour);
                cal.set(Calendar.MINUTE, finalMinute);
                cal.set(Calendar.SECOND, 0);
                final long mills = cal.getTimeInMillis();
                Log.i("HEllo", Long.toString(mills));
                Log.i("HEllo", Long.toString(cal.getTimeInMillis()));
                Log.i("HEllo", Long.toString(System.currentTimeMillis()));

                assert alarmMgr != null;

                *//*higher than 23*//*
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    alarmMgr.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, System.currentTimeMillis()+ 10000*3, pendingIntent);
                } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                    alarmMgr.setExact(AlarmManager.RTC_WAKEUP, System.currentTimeMillis()+ 10000*3, pendingIntent);
                } else {
                    alarmMgr.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis()+ 10000*3, AlarmManager.INTERVAL_DAY, pendingIntent);
                }

                Toast.makeText(myalert.this, "Event scheduled at " + finalHour + ":" + finalMinute + " " + finalDay + "/" + finalMonth + "/" + finalYear, Toast.LENGTH_LONG).show();

            }
        });*/

        oneday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(myalert.this,AlertRing.class);
                final PendingIntent pendingIntent = PendingIntent.getBroadcast(myalert.this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
                final AlarmManager alarmMgr = (AlarmManager) (myalert.this.getSystemService(Context.ALARM_SERVICE));

                final Calendar cal = Calendar.getInstance();
                cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH) + 1;
                int day = cal.get(Calendar.DAY_OF_MONTH) + 1;
                cal.get(Calendar.HOUR_OF_DAY);
                cal.get(Calendar.MINUTE);
                cal.get(Calendar.SECOND);
                String Year = Long.toString(cal.get(Calendar.YEAR));
                /*String Month = Long.toString(cal.get(Calendar.MONTH ));*/
                String Day = Long.toString(cal.get(Calendar.DAY_OF_MONTH));
                String Hour = Long.toString(cal.get(Calendar.HOUR_OF_DAY));
                String Minute = Long.toString(cal.get(Calendar.MINUTE));
                String Second = Long.toString(cal.get(Calendar.SECOND));
                final long mills = cal.getTimeInMillis();

                assert alarmMgr != null;

                /*higher than 23*/
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    alarmMgr.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, System.currentTimeMillis()+ 86400000, pendingIntent);
                } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                    alarmMgr.setExact(AlarmManager.RTC_WAKEUP, System.currentTimeMillis()+ 86400000, pendingIntent);
                } else {
                    alarmMgr.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + 86400000, AlarmManager.INTERVAL_DAY, pendingIntent);
                }

                Toast.makeText(myalert.this, "Ring scheduled at " + Hour + ":" + Minute + " " + day + "/" + month + "/" + Year, Toast.LENGTH_LONG).show();

            }
        });

        threeday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(myalert.this,AlertRing.class);
                final PendingIntent pendingIntent = PendingIntent.getBroadcast(myalert.this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
                final AlarmManager alarmMgr = (AlarmManager) (myalert.this.getSystemService(Context.ALARM_SERVICE));

                final Calendar cal = Calendar.getInstance();
                cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH) + 1;
                int day = cal.get(Calendar.DAY_OF_MONTH) + 3;
                cal.get(Calendar.HOUR_OF_DAY);
                cal.get(Calendar.MINUTE);
                cal.get(Calendar.SECOND);
                String Year = Long.toString(cal.get(Calendar.YEAR));
                /*String Month = Long.toString(cal.get(Calendar.MONTH ));*/
                String Day = Long.toString(cal.get(Calendar.DAY_OF_MONTH));
                String Hour = Long.toString(cal.get(Calendar.HOUR_OF_DAY));
                String Minute = Long.toString(cal.get(Calendar.MINUTE));
                String Second = Long.toString(cal.get(Calendar.SECOND));
                final long mills = cal.getTimeInMillis();

                assert alarmMgr != null;

                /*higher than 23*/
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    alarmMgr.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, System.currentTimeMillis()+ 259200000, pendingIntent);
                } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                    alarmMgr.setExact(AlarmManager.RTC_WAKEUP, System.currentTimeMillis()+ 259200000, pendingIntent);
                } else {
                    alarmMgr.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + 259200000, AlarmManager.INTERVAL_DAY, pendingIntent);
                }

                Toast.makeText(myalert.this, "Ring scheduled at " + Hour + ":" + Minute + " " + day + "/" + month + "/" + Year, Toast.LENGTH_LONG).show();

            }
        });

        sevenday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(myalert.this,AlertRing.class);
                final PendingIntent pendingIntent = PendingIntent.getBroadcast(myalert.this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
                final AlarmManager alarmMgr = (AlarmManager) (myalert.this.getSystemService(Context.ALARM_SERVICE));

                final Calendar cal = Calendar.getInstance();
                cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH) + 1;
                int day = cal.get(Calendar.DAY_OF_MONTH) + 7;
                cal.get(Calendar.HOUR_OF_DAY);
                cal.get(Calendar.MINUTE);
                cal.get(Calendar.SECOND);
                String Year = Long.toString(cal.get(Calendar.YEAR));
                String Day = Long.toString(cal.get(Calendar.DAY_OF_MONTH));
                String Hour = Long.toString(cal.get(Calendar.HOUR_OF_DAY));
                String Minute = Long.toString(cal.get(Calendar.MINUTE));
                String Second = Long.toString(cal.get(Calendar.SECOND));
                final long mills = cal.getTimeInMillis();

                assert alarmMgr != null;

                /*higher than 23*/
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    alarmMgr.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, System.currentTimeMillis()+ 604800000, pendingIntent);
                } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                    alarmMgr.setExact(AlarmManager.RTC_WAKEUP, System.currentTimeMillis()+ 604800000, pendingIntent);
                } else {
                    alarmMgr.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + 604800000, AlarmManager.INTERVAL_DAY, pendingIntent);
                }

                Toast.makeText(myalert.this, "Ring scheduled at " + Hour + ":" + Minute + " " + day + "/" + month + "/" + Year, Toast.LENGTH_LONG).show();

            }
        });


    }

    public void btn_mm(View view){
        txtmm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(myalert.this, trip.class);
                startActivity(intent);
            }
        });
    }

    /*@Override
    protected Dialog onCreateDialog(int id) {
        if (id == DATEPICKER_DIALOG_ID) {
            return new DatePickerDialog(this, datePickerListener, finalYear, finalMonth, finalDay);
        } else if (id == TIMEPICKER_DIALOG_ID) {
            return new TimePickerDialog(this, timePickerListener, finalHour, finalMinute, false);
        } else {
            return null;
        }
    }

    private DatePickerDialog.OnDateSetListener datePickerListener =
            new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                    finalYear = i;
                    finalMonth = i1 + 1;
                    finalDay = i2;
                    showDialog(TIMEPICKER_DIALOG_ID);
                }
            };

    protected TimePickerDialog.OnTimeSetListener timePickerListener =
            new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker timePicker, int i, int i1) {
                    finalHour = i;
                    finalMinute = i1;
                }
            };*/

}


/*    <Button
        android:id="@+id/btn_reminder"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_alignParentTop="true"
        android:layout_centerHorizontal="true"
        android:layout_marginTop="192dp"
        android:text="Time" />
        set calender time (backup

        <Button
        android:id="@+id/Time"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_centerInParent="true"
        android:text="@string/set_your_reminder"
        android:background="#2196F3"/>
        set alarm for value get from calender time*/