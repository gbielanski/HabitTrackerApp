package pl.gbielanski.habittrackerapp;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import pl.gbielanski.habittrackerapp.data.HabitContract;
import pl.gbielanski.habittrackerapp.data.HabitDbHelper;

import static pl.gbielanski.habittrackerapp.data.HabitContract.HabitEntry.COLUMN_FREQUENCY_NAME;
import static pl.gbielanski.habittrackerapp.data.HabitContract.HabitEntry.COLUMN_HABIT_NAME;
import static pl.gbielanski.habittrackerapp.data.HabitContract.HabitEntry.FREQUENCY_A_FEW_TIMES_A_DAY;
import static pl.gbielanski.habittrackerapp.data.HabitContract.HabitEntry.FREQUENCY_A_FEW_TIMES_A_WEEK;
import static pl.gbielanski.habittrackerapp.data.HabitContract.HabitEntry.FREQUENCY_OCCASIONALLY;
import static pl.gbielanski.habittrackerapp.data.HabitContract.HabitEntry.FREQUENCY_ONCE_A_DAY;
import static pl.gbielanski.habittrackerapp.data.HabitContract.HabitEntry.FREQUENCY_ONCE_A_WEEK;
import static pl.gbielanski.habittrackerapp.data.HabitContract.HabitEntry.TABLE_NAME;
import static pl.gbielanski.habittrackerapp.data.HabitContract.HabitEntry._ID;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "HabbitTracker";
    private HabitDbHelper habitDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        habitDbHelper = new HabitDbHelper(this);

        insertDataIntoTable();

        Cursor cursor = readDataFromTable();
        while (cursor.moveToNext()){
            int idColumnIndex = cursor.getColumnIndex(_ID);
            int habitColumnIndex = cursor.getColumnIndex(COLUMN_HABIT_NAME);
            int frequencyColumnIndex = cursor.getColumnIndex(COLUMN_FREQUENCY_NAME);

            int id = cursor.getInt(idColumnIndex);
            String habit = cursor.getString(habitColumnIndex);
            int frequency = cursor.getInt(frequencyColumnIndex);

            String rowData = "_ID: " + id + " HABIT_NAME: " + habit + " FREQUENCY " + getFrequencyDescription(frequency);
            Log.v(TAG, rowData);
        }

        cursor.close();
    }

    private Cursor readDataFromTable() {
        SQLiteDatabase sqLiteDatabase = habitDbHelper.getReadableDatabase();

        String [] projection = {_ID, COLUMN_HABIT_NAME, COLUMN_FREQUENCY_NAME};
        return sqLiteDatabase.query(TABLE_NAME, projection, null, null, null, null, null);
    }

    private String getFrequencyDescription(int frequency) {

        switch (frequency){
            case FREQUENCY_ONCE_A_WEEK:
                return "FREQUENCY_ONCE_A_WEEK (" + FREQUENCY_ONCE_A_WEEK + ")";
            case FREQUENCY_A_FEW_TIMES_A_WEEK:
                return "FREQUENCY_A_FEW_TIMES_A_WEEK (" + FREQUENCY_A_FEW_TIMES_A_WEEK + ")";
            case FREQUENCY_ONCE_A_DAY:
                return "FREQUENCY_ONCE_A_DAY (" + FREQUENCY_ONCE_A_DAY + ")";
            case FREQUENCY_A_FEW_TIMES_A_DAY:
                return "FREQUENCY_A_FEW_TIMES_A_DAY (" + FREQUENCY_A_FEW_TIMES_A_DAY + ")";
            case FREQUENCY_OCCASIONALLY:
                return "FREQUENCY_OCCASIONALLY (" + FREQUENCY_OCCASIONALLY + ")";
            default:
                return "UNKNOWN FREQUENCY (" + frequency + ")";
        }
    }

    private void insertDataIntoTable() {
        SQLiteDatabase sqLiteDatabase = habitDbHelper.getWritableDatabase();
        ContentValues values1 = new ContentValues();
        values1.put(HabitContract.HabitEntry.COLUMN_HABIT_NAME, "walking the dog");
        values1.put(HabitContract.HabitEntry.COLUMN_FREQUENCY_NAME, FREQUENCY_A_FEW_TIMES_A_DAY);
        sqLiteDatabase.insert(TABLE_NAME, null, values1);

        ContentValues values2 = new ContentValues();
        values2.put(HabitContract.HabitEntry.COLUMN_HABIT_NAME, "practicing the saxophone");
        values2.put(HabitContract.HabitEntry.COLUMN_FREQUENCY_NAME, FREQUENCY_A_FEW_TIMES_A_WEEK);
        sqLiteDatabase.insert(TABLE_NAME, null, values2);
    }
}
