package com.example.framwork.sql;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MySqlOpenHelper extends SQLiteOpenHelper {
    public MySqlOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, "MoneyHouse.db", null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table house(price varchar(20))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
