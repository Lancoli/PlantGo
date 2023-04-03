package com.example.myapplication.storage;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.myapplication.business.plant.Plant;

import java.util.ArrayList;

public class DBHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "plantsManager";
    private static final String TABLE_PLANTS = "plants";
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_SIZE = "size";
    private static final String KEY_RESISTANCE = "resistance";
    private static final String KEY_LIGHTNEEDS = "lightneeds";

    public DBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        String CREATE_PLANTS_TABLE = "CREATE TABLE " + TABLE_PLANTS +
                "(" + KEY_ID + " INTEGER PRIMARY KEY, " +
                KEY_NAME + " TEXT," +
                KEY_SIZE + " TEXT," +
                KEY_RESISTANCE + " TEXT," +
                KEY_LIGHTNEEDS + " TEXT" +
                ")";
        DB.execSQL(CREATE_PLANTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int i1) {
        // Drop older table if existed
        DB.execSQL("DROP TABLE IF EXISTS " + TABLE_PLANTS);

        // Create tables again
        onCreate(DB);
    }

    // code to add the new plant
    public void addPlant(Plant plant) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, plant.getName()); // Plant Name
        values.put(KEY_SIZE, plant.getSize()); // Plant Size
        values.put(KEY_RESISTANCE, plant.getResistance()); // Plant Resistance
        values.put(KEY_LIGHTNEEDS, plant.getLightNeeds()); // Plant LightNeeds

        // Inserting Row
        db.insert(TABLE_PLANTS, null, values);
        //2nd argument is String containing nullColumnHack
        db.close(); // Closing database connection
    }

    // code to get the single plant
    Plant getPlant(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(
                TABLE_PLANTS, new String[]{KEY_ID, KEY_NAME}, KEY_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        int pId = Integer.parseInt(cursor.getString(0));
        String pName = cursor.getString(1);
        Plant plant = new Plant(pName);


        return plant;
    }

    // code to get all contacts in a list view
    public ArrayList<Plant> getAllPlants() {
        ArrayList<Plant> plantList = new ArrayList<Plant>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_PLANTS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Plant plant = new Plant(cursor.getString(1));
                plant.setId(Integer.parseInt(cursor.getString(0)));

                plantList.add(plant);
            } while (cursor.moveToNext());
        }

        return plantList;
    }

    // code to update the single plant
    public int updatePlant(Plant plant) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, plant.getName());

        // updating row
        return db.update(TABLE_PLANTS, values, KEY_ID + " = ?",
                new String[] { String.valueOf(plant.getId()) });
    }

    // Deleting single plant
    public void deletePlant(Plant plant) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_PLANTS, KEY_ID + " = ?",
                new String[] { String.valueOf(plant.getId()) });
        db.close();
    }

    // Getting contacts Count
    public int getPlantsCount() {
        String countQuery = "SELECT  * FROM " + TABLE_PLANTS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        // return count
        return cursor.getCount();
    }
}
