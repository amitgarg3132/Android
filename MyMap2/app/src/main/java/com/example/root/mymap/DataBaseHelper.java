package com.example.root.mymap;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by root on 1/7/15.
 */
public class DataBaseHelper extends SQLiteOpenHelper {
    //database and table name
    public static final String DATABASE_NAME = "LocationAlarm.db";
    public static final String TABLE_NAME = "Location_table";

    //column names
    public static final String Col1 = " (id";
    public static final String Col2 = "lattitude";
    public static final String Col3 = "longitude";
    public static final String Col4 = "description";

    //properties
    public static final String Col1_prop = " INTEGER PRIMARY KEY AUTOINCREMENT,";
    public static final String Col2_prop = " REAL,";
    public static final String Col3_prop = " REAL,";
    public static final String Col4_prop = " TEXT)";

    public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String Query = "create table "+TABLE_NAME+Col1+Col1_prop+Col2+Col2_prop+Col3+Col3_prop+Col4+Col4_prop;
        db.execSQL(Query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String Query = "DROP TABLE IF EXISTS "+TABLE_NAME;
        db.execSQL(Query);
        onCreate(db);
    }

    public boolean insertData(float longitude,float lattitude,String description){

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues newContent = new ContentValues();
        newContent.put(Col2,lattitude);
        newContent.put(Col4,longitude);
        newContent.put(Col3,description);

        long result = db.insert(TABLE_NAME,null,newContent);
        if(result == -1)
            return false;
        return true;

    }

    public Cursor getData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor result = db.rawQuery("select * from "+TABLE_NAME,null);
        return result;
    }
}
