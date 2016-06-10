package com.ruc.bhushalsuman.ruc;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class WednesdayScheduleActivity extends Activity {

    private RUCDBAdapter dbAdapter;
    private ListView lvWedSchedule;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wednesday_schedule);

        lvWedSchedule = (ListView) findViewById(R.id.lv_wed);

        dbAdapter = new RUCDBAdapter(this);
        try {
            dbAdapter.open();
            List<FetchRecords> fRs = new ArrayList<FetchRecords>();
            List<FetchRecords> selectFromDatabase = dbAdapter.getAllScheduleData();

            for (FetchRecords ff : selectFromDatabase) {
                String dayValue = ff.getWeek_Day();
                if ((dayValue.equals("Wed")) || (dayValue.equals("wed"))
                        || (dayValue.equals("WED")) || (dayValue.equals("wednesday"))
                        || (dayValue.equals("Wednesday"))
                        || (dayValue.equals("WEDNESDAY"))) {
                    Log.d("Module", ff.getModule());

                    fRs.add(new FetchRecords(ff.getId(), ff.getModule(), ff.getLecturer(),
                            ff.getWeek_Day(), ff.getTime(), ff.getBlock(), ff.getClass_Room()));
                    Log.d("Module", fRs.toString());
                }
            }

            if (fRs.size() == 0) {
                fRs.add(new FetchRecords(null, "No Classes On Wednesday!!!", null, null, null, null, null));
            }
            ScheduleListAdapter sListAdapter = new ScheduleListAdapter(this, fRs);
            //set this data behind the current list view
            lvWedSchedule.setAdapter(sListAdapter);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
