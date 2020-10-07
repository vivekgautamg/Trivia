package com.example.trival.Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.trival.MainActivity;

import java.util.ArrayList;
import java.util.List;


public class QuizData {
    private Context context;
    private MyDBOpen myDBOpen;
    private SQLiteDatabase sqLiteDatabase;



    public QuizData(Context context) {

        this.context = context;
        this.myDBOpen = new MyDBOpen(context);
        sqLiteDatabase = myDBOpen.getWritableDatabase();
    }

    public void open() {
        sqLiteDatabase = myDBOpen.getWritableDatabase();
    }

    public void close() {
        sqLiteDatabase.close();
    }

    public void insertItem(Answers answers) {
        ContentValues values = answers.getContentValues();
        sqLiteDatabase.insert(QuizTable.TABLE_QUIZ, null, values);
    }

    public List<Answers> getAllItems() {
        List<Answers> answersModelList = new ArrayList<>();
        Cursor cursor = sqLiteDatabase.query(QuizTable.TABLE_QUIZ, QuizTable.ALL_COLUMNS, null, null, null, null, null);
        while (cursor.moveToNext()) {

            Answers answersModel = new Answers(
                    cursor.getString(cursor.getColumnIndex(QuizTable.COLUMN_DATETIME)),
                    cursor.getString(cursor.getColumnIndex(QuizTable.COLUMN_ID)),
                    cursor.getString(cursor.getColumnIndex(QuizTable.COLUMN_NAME)),
                    cursor.getString(cursor.getColumnIndex(QuizTable.COLUMN_CRICKETER)),
                    cursor.getString(cursor.getColumnIndex(QuizTable.COLUMN_COLORS))

            );

            answersModelList.add(answersModel);
        }
        cursor.close();

        return answersModelList;
    }
}
