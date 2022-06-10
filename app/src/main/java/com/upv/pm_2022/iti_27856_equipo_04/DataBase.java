package com.upv.pm_2022.iti_27856_equipo_04;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DataBase extends SQLiteOpenHelper {

    private String[] create = new String[]{
            "CREATE TABLE Brand" +
                    "(id INTEGER PRIMARY KEY, name TEXT)",
            "CREATE TABLE Product" +
                    "(id INTEGER PRIMARY KEY, name TEXT," +
                    "id_marca REFERENCES Brand(id))",
            "CREATE TABLE Store" +
                    "(id INTEGER PRIMARY KEY, name TEXT, address TEXT)",
            "CREATE TABLE Price" +
                    "(id INTEGER PRIMARY KEY, price DECIMAL(5,2), id_store REFERENCES Store(id)," +
                    "id_product REFERENCES Product(id))",
            "CREATE TABLE Comparative" +
                    "(id INTEGER PRIMARY KEY, difference DECIMAL(5,2),date DATETIME, " +
                    "id_product_1 REFERENCES Product(id),id_product_2 REFERENCES Product(id))"

    };

    String[] delete = new String[]{
            "DROP TABLE IF EXISTS Comparative",
            "DROP TABLE IF EXISTS Store",
            "DROP TABLE IF EXISTS Prices",
            "DROP TABLE IF EXISTS Product",
            "DROP TABLE IF EXISTS Brand"
    };

    public DataBase(@Nullable Context context, @Nullable String name, @Nullable CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        for (int i = 0; i < create.length; i++){
            sqLiteDatabase.execSQL(create[i]);
        }
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
        for (int j = 0; j < delete.length; j++){
            sqLiteDatabase.execSQL(delete[j]);
        }
    }

}
