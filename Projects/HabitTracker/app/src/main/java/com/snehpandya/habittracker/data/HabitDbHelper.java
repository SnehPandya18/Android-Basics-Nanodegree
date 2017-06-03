package com.snehpandya.habittracker.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class HabitDbHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "habit.db";
    public static final int DATABASE_VERSION = 1;

    public HabitDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_HABIT_ENTRY =
                "CREATE TABLE " + HabitContract.HabitDetails.TABLE_NAME + " (" +
                        HabitContract.HabitDetails._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        HabitContract.HabitDetails.COLUMN_PERSON_NAME + " TEXT, " +
                        HabitContract.HabitDetails.COLUMN_PERSON_AGE + " INTEGER, " +
                        HabitContract.HabitDetails.COLUMN_WEEK_DAY + " TEXT, " +
                        HabitContract.HabitDetails.COLUMN_WALKING_HABIT + " INTEGER, " +
                        HabitContract.HabitDetails.COLUMN_READING_HABIT + " INTEGER, " +
                        HabitContract.HabitDetails.COLUMN_NO_SMOKING + " INTEGER" + ")";
        db.execSQL(CREATE_HABIT_ENTRY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String DELETE_HABIT_ENTRY =
                "DELETE FROM " + HabitContract.HabitDetails.TABLE_NAME;
        db.execSQL(DELETE_HABIT_ENTRY);
    }
}