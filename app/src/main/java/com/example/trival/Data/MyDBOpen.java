package com.example.trival.Data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MyDBOpen extends SQLiteOpenHelper {

    public static final String TAG = "myTag";
    public static final String DATABASE_NAME = "quizInfo.db";
    public static final int DATABASE_VERSION = 1;


    public MyDBOpen(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(QuizTable.SQL_CREATE);
        Log.d(TAG, " db created");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
