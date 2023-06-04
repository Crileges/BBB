package com.example.bbbthirdtry;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.bbbthirdtry.MainFragments.Quest.Categories;
import com.example.bbbthirdtry.MainFragments.Quest.Points;
import com.example.bbbthirdtry.MainFragments.Quest.Quest;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String COLUMN_ID = "ID";
    public static final String QUEST_TABLE = "QUEST_TABLE";
    public static final String COLUMN_POINTS = "POINTS";
    public static final String COLUMN_QUEST_TITLE = "QUEST_TITLE";
    public static final String COLUMN_DESCRIPTION = "DESCRIPTION";
    public static final String COLUMN_CATEGORY = "CATEGORY";
    public static final String COLUMN_DONE = "DONE";
    public static final String COLUMN_LAT = "LAT";
    public static final String COLUMN_LON = "LON";
    public static final String COLUMN_RADIUS = "RADIUS";

    public DatabaseHelper(@Nullable Context context) {
        super(context, "questDB", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableStatement =
                "CREATE TABLE " + QUEST_TABLE + "(" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_POINTS + " INT, " + COLUMN_QUEST_TITLE + " TEXT, " + COLUMN_DESCRIPTION + " TEXT, " + COLUMN_CATEGORY + " TEXT, " + COLUMN_DONE + " INT, " + COLUMN_LAT + " REAL, " + COLUMN_LON + " REAL, " + COLUMN_RADIUS + " INT)";

        db.execSQL(createTableStatement);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean addOne(Quest quest){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_POINTS, quest.getPoints().toString());
        cv.put(COLUMN_QUEST_TITLE, quest.getTitle());
        cv.put(COLUMN_DESCRIPTION, quest.getDes());
        cv.put(COLUMN_CATEGORY, quest.getCategory().toString());
        cv.put(COLUMN_DONE, quest.isDone());
        cv.put(COLUMN_LAT, quest.getLat());
        cv.put(COLUMN_LON, quest.getLon());
        cv.put(COLUMN_RADIUS, quest.getRadius());

        long insert = db.insert(QUEST_TABLE, null, cv);
        if(insert == -1){
            return false;
        } else {
            return true;
        }
    }

    public List<Quest> getQuests(){
        List<Quest> retList = new ArrayList<>();

        String queryString = "SELECT * FROM " + QUEST_TABLE;

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(queryString, null);
        if(cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                int points = cursor.getInt(1);
                String title = cursor.getString(2);
                String description = cursor.getString(3);
                String category = cursor.getString(4);
                Boolean done = cursor.getInt(5) == 1 ? true: false;
                Double lat = cursor.getDouble(6);
                Double lon = cursor.getDouble(7);
                int radius = cursor.getInt(8);

                Quest quest = new Quest(id, Points.convertToPoints(points), title, description, Categories.convertToCategory(category), done, lat, lon, radius);
                Boolean check = retList.add(quest);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return retList;
    }
}
