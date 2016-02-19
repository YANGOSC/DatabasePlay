package com.hpe.yanliu.databaseplay;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

/**
 * Created by yanliu on 2016/2/16.
 */
public class MyDatabaseHelper extends SQLiteOpenHelper{
    //把建表语句定义成一个字符串常量
    public static final String CREATE_BOOK ="create table Book("+"id integer primary key autoincrement,"
                                            +"author text,"+"price real,"+"pages integer,"+"name text)";

    public static final String CREATE_CATEGORY = "create table Category(" + "id integer primary key autoincrement,"
                                                +"category_name text," +"category_code integer)";

    //创建全局变量Context
    private Context mContext;
    public MyDatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        mContext = context;
    }

    //getReadableDatabase()和getWriteableDatabase()方法返回的就是SQLiteDatabase
    public void onCreate(SQLiteDatabase db) {
        //执行建表语句
        db.execSQL(CREATE_BOOK);
        db.execSQL(CREATE_CATEGORY);
        Toast.makeText(mContext,"Create succeeded",Toast.LENGTH_LONG).show();

    }

    @Override
    //先执行DROP语句将数据库已经存在的表删除，在调用onCreate()创建，先删除存在的表，因为如果创建表时发现这张表已经存在了，就会直接报错
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists Book");
        db.execSQL("drop table if exists Category");
        onCreate(db);

    }
}
