package com.ruc.bhushalsuman.ruc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ThursdayScheduleActivity extends AppCompatActivity {

    RUCDBAdapter dbAdapter;
    ListView lvThuSchedule;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thursday_schedule);

        dbAdapter = new RUCDBAdapter(this);
        lvThuSchedule = (ListView)findViewById(R.id.lv_thu);

        try {
            dbAdapter.open();
            List<FetchRecords> fRs = new ArrayList<FetchRecords>();
            List<FetchRecords> selectFromDatabase = dbAdapter.getAllScheduleData();

            for (FetchRecords ff : selectFromDatabase){
                String dayValue = ff.getWeek_Day();
                if((dayValue.equals("Thu")) || (dayValue.equals("thu"))
                        || (dayValue.equals("THU")) || (dayValue.equals("thursday"))
                        || (dayValue.equals("Thursday"))
                        || (dayValue.equals("THURSDAY"))) {

                    fRs.add(new FetchRecords(ff.getId(), ff.getModule(), ff.getLecturer(),ff.getWeek_Day(), ff.getTime(), ff.getBlock(),ff.getClass_Room()));
                }
            }

            if(fRs.size() == 0){
                fRs.add(new FetchRecords(null,"No Classes On Thursday!!!", null, null, null, null, null));
            }

            ScheduleListAdapter sListAdapter = new ScheduleListAdapter(this,fRs);
            lvThuSchedule.setAdapter(sListAdapter);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
