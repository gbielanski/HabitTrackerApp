package pl.gbielanski.habittrackerapp.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static pl.gbielanski.habittrackerapp.data.HabitContract.HabitEntry.*;

public class HabitDbHelper extends SQLiteOpenHelper {
    private static final String SQL_CREATE_DB =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    _ID + " INTEGER PRIMARY KEY," +
                    COLUMN_HABIT_NAME + " TEXT NOT NULL, " +
                    COLUMN_FREQUENCY_NAME + " INTEGER NOT NULL DEFAULT 5);";

    private static final String SQL_DELETE_DB =
            "DROP TABLE IF EXISTS " + TABLE_NAME;

    private static final String DATABASE_NAME = "habit.db";
    private static final int VERSION = 1;

    public HabitDbHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_DB);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_DB);
    }
}
