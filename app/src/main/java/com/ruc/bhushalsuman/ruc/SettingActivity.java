package com.ruc.bhushalsuman.ruc;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class SettingActivity extends AppCompatActivity {

    RUCDBAdapter dbAdapter;
    String notificationTitle;
    String notificationBody;
    ToggleButton toggleButton;
    AlarmManager alarmManager;
    PendingIntent alarmIntent;
    Notify notify;
    boolean isAlarmOn = false;
    int weekDay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        dbAdapter = new RUCDBAdapter(this);
        notify = new Notify();

        alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        toggleButton = (ToggleButton) findViewById(R.id.tBNotification);

        toggleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (toggleButton.isChecked()) {
                    setAlarm();
                    Toast.makeText(SettingActivity.this, "Notification is Turn ON", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(SettingActivity.this, "Notification is Turn OFF", Toast.LENGTH_LONG).show();
                    stopAlarm();
                }
            }
        });

    }

    public void setAlarm() {

        try {
            //getting the day and time from database
            dbAdapter.open();
            List<FetchRecords> scheduleData = dbAdapter.getAllScheduleData();
            String moduleValue, lecturer, dayValue, startTime, block, classValue;
            int notificationId = 1;
            Intent alertIntent;
            for (FetchRecords fR : scheduleData) {
                moduleValue = fR.getModule();
                lecturer = fR.getLecturer();
                dayValue = fR.getWeek_Day();
                startTime = fR.getTime();
                block = fR.getBlock();
                classValue = fR.getClass_Room();

                Log.d("Day Value From DB", dayValue.toString());

                //getting day from the system
                SimpleDateFormat sdf = new SimpleDateFormat("EEEE");
                Date d = new Date();
                String dayOfWeek = sdf.format(d);
                Log.d("Day Value From Date", dayOfWeek);

                if (dayOfWeek.compareTo(dayValue) == 0 && startTime != null) {
                    if (dayValue.equalsIgnoreCase("Monday")) {
                        weekDay = 2;
                    } else if (dayValue.equalsIgnoreCase("Tuesday")) {
                        weekDay = 3;
                    } else if (dayValue.equalsIgnoreCase("Wednesday")) {
                        weekDay = 4;
                    } else if (dayValue.equalsIgnoreCase("Thursday")) {
                        weekDay = 5;
                    } else if (dayValue.equalsIgnoreCase("Friday")) {
                        weekDay = 6;
                    }

                    //getting current instance of date and time
                    Calendar dateTimeNow = Calendar.getInstance();
                    dateTimeNow = (Calendar) dateTimeNow.clone();

                    // Set the alarm to start at 7:30 a.m.
                    Calendar alertTime = Calendar.getInstance();
                    alertTime.setTimeInMillis(System.currentTimeMillis());
                    alertTime.set(Calendar.DAY_OF_WEEK, weekDay);
                    alertTime.set(Calendar.HOUR_OF_DAY, 7);
                    alertTime.set(Calendar.MINUTE, 30);
                    alertTime.set(Calendar.SECOND, 00);
                    alertTime.set(Calendar.MILLISECOND,00);

                    if (alertTime.compareTo(dateTimeNow) <= 0) {
                        // if time has passed add 1 days
                        alertTime.add(Calendar.DATE, 1);
                    }

                    Log.d("ALERT TIME", alertTime.toString());

                    notificationTitle = "Come To University";
                    notificationBody = "Today at " + startTime + ", Lecture on " + moduleValue
                            + " from " + lecturer + " at block " + block + " in class " + classValue;

                    alertIntent = new Intent(this, Notify.class);
                    Log.d("Notification Body", "" + notificationId);
                    alertIntent.putExtra("NOTIFICATION_ID", notificationId);
                    alertIntent.putExtra("NOTIFICATION_TITLE", notificationTitle);
                    alertIntent.putExtra("NOTIFICATION_BODY", notificationBody);

                    alarmIntent = PendingIntent.getBroadcast(this, 0, alertIntent, PendingIntent.FLAG_UPDATE_CURRENT);
                    alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, alertTime.getTimeInMillis(), 24 * 60 * 60 * 1000, alarmIntent);

                    notificationId++;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        isAlarmOn = true;
    }

    public void stopAlarm() {
        if (isAlarmOn)
            alarmManager.cancel(alarmIntent);
    }

}
