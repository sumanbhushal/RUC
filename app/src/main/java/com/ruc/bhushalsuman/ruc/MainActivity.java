package com.ruc.bhushalsuman.ruc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Creating new thread for Splash Screen to sleep for 3 sec
        Thread splashScreen = new Thread() {
            public void run() {
                try {
                    sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    //setting Intent to start login activity
                    Intent startQuestion = new Intent(getApplicationContext(), LoginActivity.class);
                    startActivity(startQuestion);
                }
            }
        };
        //staring thread
        splashScreen.start();
    }
}
