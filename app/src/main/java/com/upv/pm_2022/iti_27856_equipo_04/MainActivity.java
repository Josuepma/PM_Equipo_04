package com.upv.pm_2022.iti_27856_equipo_04;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TableLayout;


public class MainActivity extends AppCompatActivity {

    private final String dbName = "merch.db";
    private DataBase usdbh;
    private SQLiteDatabase db;
    private Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        usdbh = new DataBase(this, dbName, null, 1);
        db = usdbh.getWritableDatabase();
    }
}