package com.example.school.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.school.model.Info;

import java.util.ArrayList;
import java.util.List;

public class InfoDao {
    private Context context;
    MyDBHelper dbHelper;

    public InfoDao(Context context) {
        this.context = context;
        dbHelper = new MyDBHelper(context);
    }

    /**
     * 添加一条记录
     */
    public void add(String dbName, String title, String contexts) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        if (db.isOpen()) {
            ContentValues values = new ContentValues();
            values.put("title", title);
            values.put("context", contexts);
            //不允许插入一个空值,如果contentvalue,一般第二个参
            db.insert(dbName, null, values);//通过组拼完成的添加的操作
        }
        db.close();
    }

    /**
     * 删除数据
     *
     * @param title
     */
    public void delete(String dbName, String title) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        if (db.isOpen()) {
            db.delete(dbName, "title=?", new String[]{title});
            db.close();
        }
    }

    public List<Info> findAll(String dbName) {
        List<Info> persons = null;
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        if (db.isOpen()) {
            persons = new ArrayList<Info>();

            Cursor cursor = db.query(dbName, null, null, null, null, null, null);

            while (cursor.moveToNext()) {
                Info info = new Info();
                info.setTitle(cursor.getString(cursor.getColumnIndex("title")));
                info.setContext(cursor.getString(cursor.getColumnIndex("context")));
                persons.add(info);
            }
            cursor.close();
            db.close();
        }
        return persons;
    }
}
