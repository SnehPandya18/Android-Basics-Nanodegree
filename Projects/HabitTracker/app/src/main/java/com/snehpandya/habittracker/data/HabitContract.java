package com.snehpandya.habittracker.data;

import android.provider.BaseColumns;

public class HabitContract {

    private HabitContract() {}

    public static final class HabitDetails implements BaseColumns {
        public static final String TABLE_NAME = "Habit_Detail";
        public static final String _ID = BaseColumns._ID;
        public static final String COLUMN_PERSON_NAME = "Name";
        public static final String COLUMN_PERSON_AGE = "Age";
        public static final String COLUMN_WEEK_DAY = "Week";
        public static final String COLUMN_WALKING_HABIT = "Walking_Habit";
        public static final String COLUMN_READING_HABIT = "Reading_Habit";
        public static final String COLUMN_NO_SMOKING = "No_Smoking";

        public static final int HABIT_VALUE_FALSE = 0;
        public static final int HABIT_VALUE_TRUE = 1;
    }
}
