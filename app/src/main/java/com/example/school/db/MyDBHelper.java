package com.example.school.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by amosli on 14-6-12.
 */
public class MyDBHelper extends SQLiteOpenHelper{
    /**
     *
     * @param context
     */
    public MyDBHelper(Context context) {
        super(context, "sqlitedb", null, 1);
    }

    /**
     * 数据库第一次创建的时候调用此方法
     * @param db
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table if not exists newsinfo (newsid integer primary key autoincrement ,title varchar(30) ,context varchar(30) )");
        db.execSQL("create table if not exists msginfo (newsid integer primary key autoincrement ,title varchar(30) ,context varchar(30) )");
        db.execSQL("create table if not exists pdtinfo (newsid integer primary key autoincrement ,title varchar(30) ,context varchar(30) )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}