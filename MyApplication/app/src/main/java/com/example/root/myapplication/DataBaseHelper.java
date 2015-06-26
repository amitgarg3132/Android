package com.example.root.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by root on 25/6/15.
 */
public class DataBaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "khata.db";
    public static final String TABLE_NAME = "khata_table";
    public static final String Col1 = "id";
    public static final String Col2 = "name";
    public static final String Col3 = "amount";
    public static final String Col4 = "lastEntry";

    public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String Query = "create table "+TABLE_NAME+" (id INTEGER PRIMARY KEY AUTOINCREMENT,name TEXT,amount INTEGER,lastEntry INTERGER)";
        db.execSQL(Query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String Query = "DROP TABLE IF EXISTS "+TABLE_NAME;
        db.execSQL(Query);
        onCreate(db);
    }

    public boolean insertData(String name,int Amount){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues newContent = new ContentValues();
        newContent.put(Col2,name);
        newContent.put(Col4,Amount);
        newContent.put(Col3,Amount);
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
