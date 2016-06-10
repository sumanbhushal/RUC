package com.ruc.bhushalsuman.ruc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TuesdayScheduleActivity extends AppCompatActivity {

    RUCDBAdapter dbAdapter;
    ListView lvTueSchedule;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tuesday_schedule);

        dbAdapter = new RUCDBAdapter(this);
        lvTueSchedule = (ListView)findViewById(R.id.lv_tue);

        try {
            dbAdapter.open();
            List<FetchRecords> fRs = new ArrayList<FetchRecords>();
            List<FetchRecords> selectFromDatabase = dbAdapter.getAllScheduleData();

            for (FetchRecords ff : selectFromDatabase){
                String dayValue = ff.getWeek_Day();
                if((dayValue.equals("Tue")) || (dayValue.equals("tue"))
                        || (dayValue.equals("TUE")) || (dayValue.equals("tuesday"))
                        || (dayValue.equals("Tuesday"))
                        || (dayValue.equals("TUESDAY"))) {

                    fRs.add(new FetchRecords(ff.getId(), ff.getModule(), ff.getLecturer(),ff.getWeek_Day(), ff.getTime(), ff.getBlock(),ff.getClass_Room()));
                }
            }

            if(fRs.size() == 0){
                fRs.add(new FetchRecords(null,"No Classes On Tuesday!!!", null, null, null, null, null));
            }

            ScheduleListAdapter sListAdapter = new ScheduleListAdapter(this,fRs);
            lvTueSchedule.setAdapter(sListAdapter);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
