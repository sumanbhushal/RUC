package com.ruc.bhushalsuman.ruc;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Bhushal Suman on 4/13/2016.
 */
public class RUCDBAdapter {

    private static final String DATABASE_NAME = "RUCDB";
    private static final String DATABASE_TABLE = "Guide";
    private static final int DATABASE_VERSION = 1;

    public static final String COLUMN_ID = "id";

    public static final String COLUMN_MODULE = "module";
    public static final String COLUMN_LECTURER = "lecturer";
    public static final String COLUMN_WEEKDAY = "week_day";
    public static final String COLUMN_TIME = "time";
    public static final String COLUMN_BLOCK = "block";
    public static final String COLUMN_ROOM = "class_room";

    private final static String CREATE_TABLE_GUIDE = "CREATE TABLE " + DATABASE_TABLE + " (" +
            COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COLUMN_MODULE + " TEXT, " +
            COLUMN_LECTURER + " TEXT, " +
            COLUMN_WEEKDAY + " TEXT, " +
            COLUMN_TIME + " TEXT, " +
            COLUMN_BLOCK + " TEXT, " +
            COLUMN_ROOM + " TEXT " +
            ")";

    private RUCDBHelper dbHelper;
    private final Context ourContext;
    private SQLiteDatabase RUCDatabase;

    public RUCDBAdapter(Context context){
        ourContext = context;
    }

    public static class RUCDBHelper extends SQLiteOpenHelper {
        public RUCDBHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
            Log.d("DATABASE CRATED", context.toString());
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(CREATE_TABLE_GUIDE);
            Log.d("TABLE CRATED", db.toString());
        }


        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXIST " + DATABASE_TABLE);
            onCreate(db);
        }
    }

    public RUCDBAdapter open() throws SQLException {
        dbHelper = new RUCDBHelper(ourContext);
        RUCDatabase = dbHelper.getWritableDatabase();
        return this;
    }

    public void insertIntoRUCDatabase(FetchRecords fetchRecords ){

        //creating new map of values, where columns name are keys
        ContentValues records = new ContentValues();
        records.put(COLUMN_ID, fetchRecords.getId());
        records.put(COLUMN_MODULE,fetchRecords.getModule());
        records.put(COLUMN_LECTURER,fetchRecords.getLecturer());
        records.put(COLUMN_WEEKDAY, fetchRecords.getWeek_Day());
        records.put(COLUMN_TIME,fetchRecords.getTime());
        records.put(COLUMN_BLOCK, fetchRecords.getBlock());
        records.put(COLUMN_ROOM, fetchRecords.getClass_Room());

        RUCDatabase.insert(DATABASE_TABLE,null,records);
        Log.d("DATA INSERTED", records.toString());
    }

    public void close(){
        dbHelper.close();
    }

    public List<FetchRecords> getAllScheduleData (){

        List<FetchRecords> scheduleRecords = new ArrayList<FetchRecords>();

        String getQuery = "SELECT * FROM " + DATABASE_TABLE;
        Cursor cursor = RUCDatabase.rawQuery(getQuery, null);
        if(cursor.moveToFirst()){
            do{
                FetchRecords fetchRecords = new FetchRecords();
                fetchRecords.setId(cursor.getString(0));
                fetchRecords.setModule(cursor.getString(1));
                fetchRecords.setLecturer(cursor.getString(2));
                fetchRecords.setWeek_Day(cursor.getString(3));
                fetchRecords.setTime(cursor.getString(4));
                fetchRecords.setBlock(cursor.getString(5));
                fetchRecords.setClass_Room(cursor.getString(6));
                scheduleRecords.add(fetchRecords);
            }while (cursor.moveToNext());
        }
        cursor.close();
        RUCDatabase.close();
        return scheduleRecords;
    }

    public void deleteAllData(){
        RUCDatabase.delete(DATABASE_TABLE,null,null);
    }

}
