package com.myandroid.todaymyfood;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE FOODTABLE(_id INTEGER PRIMARY KEY AUTOINCREMENT, foodname TEXT);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    //삽입 기능
    public void insert(String foodname) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("INSERT INTO FOODTABLE VALUES(null, '" + foodname + "');");
        db.close();
    }
    //삭제 기능
    public void delete(String foodname) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM FOODTABLE WHERE foodname='" + foodname + "';");
        db.close();
    }

    //조회 기능
    public String getResult() {
        SQLiteDatabase db = getReadableDatabase();
        String result = "";

        Cursor cursor = db.rawQuery("SELECT * FROM FOODTABLE", null);
        while(cursor.moveToNext()) {
            result += cursor.getString(1) + "\n";
        }
        cursor.close();

        return result;
    }

    //랜덤 돌리기 기능
    public String getNum() {
        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT foodname FROM FOODTABLE order by random() limit 1", null);
        String food = "";

        while(cursor.moveToNext()) {
            food = cursor.getString(0);
        }
        cursor.close();

        return food;
    }

}
