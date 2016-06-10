package com.ruc.bhushalsuman.ruc;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import java.sql.SQLException;

public class GridMenuActivity extends AppCompatActivity {

    GridView gridView;
    String [] menuValue = new String[] {"Schedule", "Map", "Help", "Settings", "Logout"};
    Intent intent;
    RUCDBAdapter rucdbAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_menu);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        rucdbAdapter = new RUCDBAdapter(this);

        gridView = (GridView) findViewById(R.id.gVMenu1);

        //creating instances of GridAdapter
        gridView.setAdapter(new GridAdapter(this, menuValue));

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        intent = new Intent(GridMenuActivity.this, ScheduleActivity.class);
                        startActivity(intent);
                        break;
                    case 1:
                        intent = new Intent(GridMenuActivity.this, MapActivity.class);
                        startActivity(intent);
                        break;
                    case 2:
                        intent = new Intent(GridMenuActivity.this, HelpActivity.class);
                        startActivity(intent);
                        break;
                    case 3:
                        intent = new Intent(GridMenuActivity.this, SettingActivity.class);
                        startActivity(intent);
                        break;
                    case 4:
                        try {
                            rucdbAdapter.open();
                            rucdbAdapter.deleteAllData();
                            rucdbAdapter.close();
                            Intent redirectToLogin = new Intent(GridMenuActivity.this, LoginActivity.class);
                            startActivity(redirectToLogin);
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                }

            }
        });
    }

}
