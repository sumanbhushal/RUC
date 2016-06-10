package com.ruc.bhushalsuman.ruc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FridayScheduleActivity extends AppCompatActivity {

    RUCDBAdapter dbAdapter;
    ListView lvFriSchedule;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friday_schedule);

        dbAdapter = new RUCDBAdapter(this);
        lvFriSchedule = (ListView)findViewById(R.id.lv_fri);

        try {
            dbAdapter.open();
            List<FetchRecords> fRs = new ArrayList<FetchRecords>();
            List<FetchRecords> selectFromDatabase = dbAdapter.getAllScheduleData();

            for (FetchRecords ff : selectFromDatabase){
                String dayValue = ff.getWeek_Day();
                if((dayValue.equals("Fri")) || (dayValue.equals("fri"))
                        || (dayValue.equals("FRI")) || (dayValue.equals("friday"))
                        || (dayValue.equals("Friday"))
                        || (dayValue.equals("FRIDAY"))) {

                    fRs.add(new FetchRecords(ff.getId(), ff.getModule(), ff.getLecturer(),ff.getWeek_Day(), ff.getTime(), ff.getBlock(),ff.getClass_Room()));
                }
            }

            if(fRs.size() == 0){
                fRs.add(new FetchRecords(null,"No Classes On Friday", null, null,  null, null, null));
            }

            ScheduleListAdapter sListAdapter = new ScheduleListAdapter(this,fRs);
            lvFriSchedule.setAdapter(sListAdapter);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
