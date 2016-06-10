package com.ruc.bhushalsuman.ruc;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ScheduleActivity extends TabActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);
       /* Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);*/

        TabHost tabHost = getTabHost();

        //Index Tab for Monday
        TabHost.TabSpec mondaySpec = tabHost.newTabSpec("MON");
        mondaySpec.setIndicator("MON");
        Intent mondayIntent = new Intent(this, ModnayScheduleActivity.class);
        mondaySpec.setContent(mondayIntent);

        //Index Tab for Tuesday
        TabHost.TabSpec tuesdaySpec = tabHost.newTabSpec("TUE");
        tuesdaySpec.setIndicator("TUE");
        Intent tuesdayIntent = new Intent(this, TuesdayScheduleActivity.class);
        tuesdaySpec.setContent(tuesdayIntent);

        //Index Tab for Wednesday
        TabHost.TabSpec wednesdaySpec = tabHost.newTabSpec("WED");
        wednesdaySpec.setIndicator("WED");
        Intent wednesdayIntent = new Intent(this, WednesdayScheduleActivity.class);
        wednesdaySpec.setContent(wednesdayIntent);

        //Index Tab for Thursday
        TabHost.TabSpec thursdaySpec = tabHost.newTabSpec("THU");
        thursdaySpec.setIndicator("THU");
        Intent thursdayIntent = new Intent(this, ThursdayScheduleActivity.class);
        thursdaySpec.setContent(thursdayIntent);

        //Index Tab for Friday
        TabHost.TabSpec fridaySpec = tabHost.newTabSpec("FRI");
        fridaySpec.setIndicator("FRI");
        Intent fridayIntent = new Intent(this, FridayScheduleActivity.class);
        fridaySpec.setContent(fridayIntent);

        //Adding all TabSpec to TabHost
        tabHost.addTab(mondaySpec);
        tabHost.addTab(tuesdaySpec);
        tabHost.addTab(wednesdaySpec);
        tabHost.addTab(thursdaySpec);
        tabHost.addTab(fridaySpec);

        //getting day from the system
        SimpleDateFormat sdf = new SimpleDateFormat("EEEE");
        Date d = new Date();
        String dayOfWeek = sdf.format(d);

        //selecting tab for current day
        if (dayOfWeek.equals("Monday"))
            tabHost.setCurrentTab(0);
        else if (dayOfWeek.equals("Tuesday"))
            tabHost.setCurrentTab(1);
        else if (dayOfWeek.equals("Wednesday"))
            tabHost.setCurrentTab(2);
        else if (dayOfWeek.equals("Thursday"))
            tabHost.setCurrentTab(3);
        else if (dayOfWeek.equals("Friday"))
            tabHost.setCurrentTab(4);

    }


}
