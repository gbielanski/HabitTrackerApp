package pl.gbielanski.habittrackerapp.data;

import android.provider.BaseColumns;

public class HabitContract {
    class HabitEntry implements BaseColumns{
        public static final String TABLE_NAME = "habits";
        public static final String _ID = BaseColumns._ID;
        public static final String COLUMN_HABIT_NAME = "habit";
        public static final String COLUMN_FREQUENCY_NAME  = "frequency";

        public static final int FREQUENCY_ONCE_A_WEEK = 1;
        public static final int FREQUENCY_A_FEW_TIMES_A_WEEK = 2;
        public static final int FREQUENCY_ONCE_A_DAY = 3;
        public static final int FREQUENCY_A_FEW_TIMES_A_DAY = 4;
        public static final int FREQUENCY_OCCASIONALLY = 5;
    }
}
