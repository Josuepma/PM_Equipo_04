package com.upv.pm_2022.iti_27856_equipo_04;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DataBase extends SQLiteOpenHelper {

    private String sqlCreate = "CREATE TABLE Brand" +
            "(id INTEGER PRIMARY KEY, name TEXT)";
    private String sqlCreate2 = "CREATE TABLE Product" +
            "(id INTEGER PRIMARY KEY, name TEXT,price DECIMAL(5,2), id_marca REFERENCES Brand(id))";
    private String sqlCreate3 = "CREATE TABLE Comparative" +
            "(id INTEGER PRIMARY KEY, difference DECIMAL(5,2),date DATETIME, " +
            "id_product_1 REFERENCES Product(id),id_product_2 REFERENCES Product(id))";

    public DataBase(@Nullable Context context, @Nullable String name, @Nullable CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(sqlCreate);
        sqLiteDatabase.execSQL(sqlCreate2);
        sqLiteDatabase.execSQL(sqlCreate3);
    }

    @Override
    public void onOpen(SQLiteDatabase sqLiteDatabase) {
        super.onOpen(sqLiteDatabase);
        if (!sqLiteDatabase.isReadOnly()) {
            // Enable foreign key constraints
            sqLiteDatabase.execSQL("PRAGMA foreign_keys=ON;");
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS Comparacion");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS Product");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS Brand");
    }

}
