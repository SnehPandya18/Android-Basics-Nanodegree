package com.snehpandya.habittracker;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.snehpandya.habittracker.data.HabitContract;
import com.snehpandya.habittracker.data.HabitDbHelper;

public class MainActivity extends AppCompatActivity {

    private HabitDbHelper mHabitDbHelper;
    private SQLiteDatabase mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mHabitDbHelper = new HabitDbHelper(this);
        insertData();
        Cursor cursor = readData();
    }

    private void insertData() {

        mDatabase = mHabitDbHelper.getWritableDatabase();
        mDatabase = mHabitDbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(HabitContract.HabitDetails.COLUMN_PERSON_NAME, "Ajay");
        values.put(HabitContract.HabitDetails.COLUMN_PERSON_AGE, 25);
        values.put(HabitContract.HabitDetails.COLUMN_WEEK_DAY, "MONDAY");

        values.put(HabitContract.HabitDetails.COLUMN_WALKING_HABIT, HabitContract.HabitDetails.HABIT_VALUE_TRUE);
        values.put(HabitContract.HabitDetails.COLUMN_READING_HABIT, HabitContract.HabitDetails.HABIT_VALUE_FALSE);
        values.put(HabitContract.HabitDetails.COLUMN_NO_SMOKING, HabitContract.HabitDetails.HABIT_VALUE_TRUE);

        long newRowID = mDatabase.insert(HabitContract.HabitDetails.TABLE_NAME, null, values);

        if (newRowID != -1)
            Toast.makeText(this, "Person Habit Details Saved", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(this, "Error with saving Person Habit Details", Toast.LENGTH_SHORT).show();
    }

    private Cursor readData() {
        String[] projection = {
                HabitContract.HabitDetails._ID,
                HabitContract.HabitDetails.COLUMN_PERSON_NAME,
                HabitContract.HabitDetails.COLUMN_PERSON_AGE,
                HabitContract.HabitDetails.COLUMN_WEEK_DAY,
                HabitContract.HabitDetails.COLUMN_WALKING_HABIT,
                HabitContract.HabitDetails.COLUMN_READING_HABIT,
                HabitContract.HabitDetails.COLUMN_NO_SMOKING
        };
        Cursor cursor = mDatabase.query(
                HabitContract.HabitDetails.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null);

        int idColumnIndex = cursor.getColumnIndex(HabitContract.HabitDetails._ID);
        int nameColumnIndex = cursor.getColumnIndex(HabitContract.HabitDetails.COLUMN_PERSON_NAME);
        int ageColumnIndex = cursor.getColumnIndex(HabitContract.HabitDetails.COLUMN_PERSON_AGE);
        int weekColumnIndex = cursor.getColumnIndex(HabitContract.HabitDetails.COLUMN_WEEK_DAY);
        int walkingColumnIndex = cursor.getColumnIndex(HabitContract.HabitDetails.COLUMN_WALKING_HABIT);
        int readingColumnIndex = cursor.getColumnIndex(HabitContract.HabitDetails.COLUMN_READING_HABIT);
        int noSmokingColumnIndex = cursor.getColumnIndex(HabitContract.HabitDetails.COLUMN_NO_SMOKING);

        while (cursor.moveToNext()) {
            int currentId = cursor.getInt(idColumnIndex);
            String currentPersonName = cursor.getString(nameColumnIndex);
            int currentPersonAge = cursor.getInt(ageColumnIndex);
            String currentWeekDay = cursor.getString(weekColumnIndex);
            int currentWalkingHabitStatus = cursor.getInt(walkingColumnIndex);
            int currentReadingHabitStatus = cursor.getInt(readingColumnIndex);
            int currentNoSmokingStatus = cursor.getInt(noSmokingColumnIndex);

            Toast.makeText(this, "Id: " + currentId, Toast.LENGTH_SHORT).show();
            Toast.makeText(this, "Name: " + currentPersonName, Toast.LENGTH_SHORT).show();
            Toast.makeText(this, "Age: " + currentPersonAge, Toast.LENGTH_SHORT).show();
            Toast.makeText(this, "WeekDay: " + currentWeekDay, Toast.LENGTH_SHORT).show();
            Toast.makeText(this, "WalkingHabit: " + currentWalkingHabitStatus, Toast.LENGTH_SHORT).show();
            Toast.makeText(this, "RedingHabit: " + currentReadingHabitStatus, Toast.LENGTH_SHORT).show();
            Toast.makeText(this, "NoSmokingHabit: " + currentNoSmokingStatus, Toast.LENGTH_SHORT).show();
        }

        return cursor;
    }
}