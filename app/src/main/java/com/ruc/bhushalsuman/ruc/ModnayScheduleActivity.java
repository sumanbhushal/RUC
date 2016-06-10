package com.ruc.bhushalsuman.ruc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ModnayScheduleActivity extends AppCompatActivity {

    private RUCDBAdapter dbAdapter;
    private ListView lvMondSchedule;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modnay_schedule);

        dbAdapter = new RUCDBAdapter(this);
        lvMondSchedule = (ListView)findViewById(R.id.lv_mon);

        try {
            dbAdapter.open();
            List<FetchRecords> fRs = new ArrayList<FetchRecords>();
            List<FetchRecords> selectFromDatabase = dbAdapter.getAllScheduleData();

            for (FetchRecords ff : selectFromDatabase){
                String dayValue = ff.getWeek_Day();
                if((dayValue.equals("Mon")) || (dayValue.equals("mon"))
                        || (dayValue.equals("MON")) || (dayValue.equals("monday"))
                        || (dayValue.equals("Monday"))
                        || (dayValue.equals("MONDAY"))) {

                    fRs.add(new FetchRecords(ff.getId(), ff.getModule(), ff.getLecturer(),ff.getWeek_Day(), ff.getTime(), ff.getBlock(),ff.getClass_Room()));
                }
            }

            if(fRs.size() == 0){
                fRs.add(new FetchRecords(null,"No Classes On Monday", null, null, null, null, null));
            }

            ScheduleListAdapter sListAdapter = new ScheduleListAdapter(this,fRs);
            lvMondSchedule.setAdapter(sListAdapter);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
