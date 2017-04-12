package com.example.roman.supercontact;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
public class MyDBOpenHelper extends SQLiteOpenHelper
{
    public MyDBOpenHelper(Context context, String name, CursorFactory
            factory, int version) {
        super(context, name, factory, version);
    }

    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL(create_table);
    }
    public void onUpgrade(SQLiteDatabase db, int version_old, int version_new)
    {
        db.execSQL(drop_table);
        db.execSQL(create_table);
    }
    private static final String create_table = "create table contacts(" +
            "ID integer primary key autoincrement, " +
            "FIRST_NAME string DEFAULT ''," +
            "LAST_NAME string DEFAULT '', " +
            "HOME_PHONE string DEFAULT ''," +
            "MOBILE_PHONE string DEFAULT ''," +
            "MAIL string DEFAULT ''" +
            ")";
    private static final String drop_table = "drop table contacts";
}