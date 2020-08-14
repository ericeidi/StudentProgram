package com.assignment2.assignment2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DatabaseHandler extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "Student";
    public static final String TABLE_NAME = "Student";

    Context context;
    SQLiteDatabase myDb;

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME,null,1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS Student(id INTEGER PRIMARY KEY AUTOINCREMENT, name VARCHAR, lastName VARCHAR, marks VARCHAR, course VARCHAR, credit VARCHAR);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }

    public long insertData(String name, String lastName, String marks, String course, String credit){


        SQLiteDatabase db = this.getWritableDatabase();
        //myDb.execSQL("CREATE TABLE IF NOT EXISTS Student.db(id INTEGER PRIMARY KEY AUTOINCREMENT, name VARCHAR, lastName VARCHAR, marks VARCHAR, course VARCHAR, credit VARCHAR);");
       // myDb.openOrCreateDatabase("Student.db", null);
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("lastName", lastName);
        contentValues.put("marks", marks);
        contentValues.put("course", course);
        contentValues.put("credit", credit);
        long result = db.insert("Student", null, contentValues);
        //db.execSQL("INSERT INTO "+ TABLE_NAME + "(name, lastName, marks, course, credit) VALUES ( '" + name + "','" + lastName +"','" + marks +  "','" + course + "','" + credit +"');");

        db.close();
        return result;

    }

    public ArrayList<ItemsAdapter> getAllData(String orderBy) {
        ArrayList<ItemsAdapter> arrayList = new ArrayList<>();

        String selectQuery = "SELECT * FROM " + TABLE_NAME + " ORDER BY " + orderBy;

        SQLiteDatabase myDb = this.getWritableDatabase();
        Cursor cursor = myDb.rawQuery(selectQuery, null);
        if (cursor.moveToNext()) {

            do {
                String test;
                ItemsAdapter model = new ItemsAdapter(
                        cursor.getInt(cursor.getColumnIndex("id")),
                        cursor.getString(cursor.getColumnIndex("name")),
                        cursor.getString(cursor.getColumnIndex("lastName")),
                        cursor.getString(cursor.getColumnIndex("marks")),
                        cursor.getString(cursor.getColumnIndex("course")),
                        cursor.getString(cursor.getColumnIndex("credit"))

                );
                arrayList.add(model);
            } while (cursor.moveToNext());

        }
        myDb.close();
        return arrayList;
    }

    public ArrayList<ItemsAdapter> getDatabyId(String orderBy, Integer id) {
        ArrayList<ItemsAdapter> arrayList = new ArrayList<>();

        String selectQuery = "SELECT * FROM " + TABLE_NAME + " WHERE ID = " + id + " ORDER BY " + orderBy ;

        SQLiteDatabase myDb = this.getWritableDatabase();
        Cursor cursor = myDb.rawQuery(selectQuery, null);
        if (cursor.moveToNext()) {

            do {
                String test;
                ItemsAdapter model = new ItemsAdapter(
                        cursor.getInt(cursor.getColumnIndex("id")),
                        cursor.getString(cursor.getColumnIndex("name")),
                        cursor.getString(cursor.getColumnIndex("lastName")),
                        cursor.getString(cursor.getColumnIndex("marks")),
                        cursor.getString(cursor.getColumnIndex("course")),
                        cursor.getString(cursor.getColumnIndex("credit"))

                );
                arrayList.add(model);
            } while (cursor.moveToNext());

        }


        myDb.close();
        return arrayList;
    }

    public ArrayList<ItemsAdapter> getDatabyCourse(String orderBy, String course) {
        ArrayList<ItemsAdapter> arrayList = new ArrayList<>();

        String selectQuery = "SELECT * FROM " + TABLE_NAME + " WHERE course = '" +  course + "' ORDER BY " + orderBy ;

        SQLiteDatabase myDb = this.getWritableDatabase();
        Cursor cursor = myDb.rawQuery(selectQuery, null);
        if (cursor.moveToNext()) {

            do {
                String test;
                ItemsAdapter model = new ItemsAdapter(
                        cursor.getInt(cursor.getColumnIndex("id")),
                        cursor.getString(cursor.getColumnIndex("name")),
                        cursor.getString(cursor.getColumnIndex("lastName")),
                        cursor.getString(cursor.getColumnIndex("marks")),
                        cursor.getString(cursor.getColumnIndex("course")),
                        cursor.getString(cursor.getColumnIndex("credit"))

                );
                arrayList.add(model);
            } while (cursor.moveToNext());

        }


        myDb.close();
        return arrayList;
    }

    public boolean verifyAllData(String orderBy) {

        String selectQuery = "SELECT * FROM " + TABLE_NAME + " ORDER BY " + orderBy ;

        SQLiteDatabase myDb = this.getWritableDatabase();
        Cursor cursor = myDb.rawQuery(selectQuery, null);

        if (cursor.getCount() <= 0 ) {
            cursor.close();
            return false;
        }
        return true;
    }

    public boolean verifyDataById(String orderBy, Integer id) {

        String selectQuery = "SELECT * FROM " + TABLE_NAME + " WHERE ID = '" +  id+ "' ORDER BY " + orderBy ;

        SQLiteDatabase myDb = this.getWritableDatabase();
        Cursor cursor = myDb.rawQuery(selectQuery, null);

        if (cursor.getCount() <= 0 ) {
            cursor.close();
            return false;
        }
        return true;
    }

    public boolean verifyDataByCourse(String orderBy, String course) {

        String selectQuery = "SELECT * FROM " + TABLE_NAME + " WHERE course = '" +  course + "' ORDER BY " + orderBy ;

        SQLiteDatabase myDb = this.getWritableDatabase();
        Cursor cursor = myDb.rawQuery(selectQuery, null);

        if (cursor.getCount() <= 0 ) {
                cursor.close();
                return false;
            }
        return true;
    }

}
