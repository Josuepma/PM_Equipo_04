package com.upv.pm_2022.iti_27856_u1_equipo_04;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class Brand {
    private String name;
    private int id;
    private static final String TABLE = "Brand";

    public Brand(int id, String name){
        this.setId(id);
        this.setName(name);
    }

    public Brand(String name){
        this.setName(name);
    }

    public Brand(){}

    public void setName(String name) {
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName(){
        return this.name;
    }

    public int getId() {
        return this.id;
    }

    public static Brand getBrand(Context context, int id){
        DataBase usdbh = new DataBase(context);
        SQLiteDatabase db = usdbh.getWritableDatabase();
        if(db != null){
            Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE + " WHERE id = " + id,null);
            if(cursor.getCount()!=0){
                return new Brand(
                    cursor.getInt(cursor.getColumnIndexOrThrow("id")),
                    cursor.getString(cursor.getColumnIndexOrThrow("name"))
                );
            }
        }
        return null;
    }

    public static ArrayList<Brand> getBrands(Context context){
        ArrayList<Brand> brands = new ArrayList<>();
        DataBase usdbh = new DataBase(context);
        SQLiteDatabase db = usdbh.getWritableDatabase();
        if(db != null){
            Cursor cursor = db.rawQuery("SELECT * from " + TABLE,null);
            if (cursor.getCount()!=0){
                if (cursor.moveToFirst()){
                    do {
                        Brand b = new Brand();
                        b.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndexOrThrow("id"))));
                        b.setName(cursor.getString(cursor.getColumnIndexOrThrow("name")));
                        brands.add(b);
                    }while(cursor.moveToNext());
                }
            }else{
                brands = null;
            }
            cursor.close();
        }
        return brands;
    }

    @Override
    public String toString() {
        return "Brand{" +
                "name='" + name + '\'' +
                ", id=" + id +
                '}';
    }
}
