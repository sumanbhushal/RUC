package com.ruc.bhushalsuman.ruc;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    EditText editTextUsername, editTextPassword;
    Button submit;
    String username, password;
    String userLoginSuccessTag;

    RUCDBAdapter dbAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        editTextUsername = (EditText) findViewById(R.id.etUsername);
        editTextPassword = (EditText) findViewById(R.id.etPassword);
        submit = (Button) findViewById(R.id.btSubmit);

        dbAdapter = new RUCDBAdapter(this);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username = editTextUsername.getText().toString();
                password = editTextPassword.getText().toString();

                if (username.isEmpty() || password.isEmpty()) {
                    Toast.makeText(LoginActivity.this, "Username or Password is Empty!!!", Toast.LENGTH_LONG).show();
                } else {
                    ValidateUser validateUser = new ValidateUser();
                    validateUser.execute();
                }
            }
        });
    }

    class ValidateUser extends AsyncTask<String, String, String>{
        ProgressDialog pDialog;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(LoginActivity.this);
            pDialog.setMessage("Loading please wait... ");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }

        @Override
        protected String doInBackground(String... params) {
            Log.d("username in Background", "userName: " + username);
            if(username.equals("admin") && password.equals("admin")){
                userLoginSuccessTag = "1";
            }else{
                userLoginSuccessTag = "0";
            }
            return userLoginSuccessTag;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            pDialog.dismiss();
            if(userLoginSuccessTag.equalsIgnoreCase("1")){

                try{
                    //Inserting schedule data into database
                    dbAdapter.open();
                    dbAdapter.insertIntoRUCDatabase(new FetchRecords("1", "RAWDATA", "Troels Andreasen",
                            "Tuesday", "9:00", "8", "08.02"));
                    dbAdapter.insertIntoRUCDatabase(new FetchRecords("2", "Android", "Morten Rhiger",
                            "Wednesday", "9:00", "8", "08.01"));
                    dbAdapter.insertIntoRUCDatabase(new FetchRecords("3", "RAWDATA", "Henrik Bulskov,",
                            "Friday", "9:00", "8", "08.02"));
                    dbAdapter.close();
                }catch (Exception e){
                    e.printStackTrace();
                }
                Intent menuGridIntent = new Intent(LoginActivity.this,GridMenuActivity.class);
                startActivity(menuGridIntent);
            }else {
                Toast.makeText(LoginActivity.this, "Invalid Username or Password", Toast.LENGTH_LONG).show();
            }
        }
    }

}
