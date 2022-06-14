package com.upv.pm_2022.iti_27856_u1_equipo_04;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import java.util.ArrayList;

public class Store {
    private int id;
    private String name;
    private String address;
    private static final String TABLE = "Store";

    public Store(int id,String name,String address){
        setId(id);
        setName(name);
        setAddress(address);
    }

    public Store(String name,String address){
        setName(name);
        setAddress(address);
    }

    public Store() {}

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public static void insert(Context context, Store store){
        DataBase usdbh = new DataBase(context);
        SQLiteDatabase db = usdbh.getWritableDatabase();
        if(db != null){
            ContentValues values = new ContentValues();
            values.put("name",store.getName());
            values.put("address",store.getAddress());
            db.insert(TABLE,null,values);
            Toast.makeText(context, "dato " + store + " insertado", Toast.LENGTH_SHORT).show();
        }
    }

    public static Store get(Context context, int id){
        DataBase usdbh = new DataBase(context);
        SQLiteDatabase db = usdbh.getWritableDatabase();
        if(db != null){
            Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE + " WHERE id = " + id,null);
            if(cursor.getCount()!=0){
                return new Store(
                        cursor.getInt(cursor.getColumnIndexOrThrow("id")),
                        cursor.getString(cursor.getColumnIndexOrThrow("name")),
                        cursor.getString(cursor.getColumnIndexOrThrow("address"))
                );
            }
        }
        return null;
    }

    public static ArrayList<Store> getAll(Context context){
        ArrayList<Store> stores = new ArrayList<>();
        DataBase usdbh = new DataBase(context);
        SQLiteDatabase db = usdbh.getWritableDatabase();
        if(db != null){
            Cursor cursor = db.rawQuery("SELECT * from " + TABLE,null);
            if (cursor.getCount()!=0){
                if (cursor.moveToFirst()){
                    do {
                        Store store = new Store();
                        store.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndexOrThrow("id"))));
                        store.setName(cursor.getString(cursor.getColumnIndexOrThrow("name")));
                        store.setName(cursor.getString(cursor.getColumnIndexOrThrow("address")));
                        stores.add(store);
                    }while(cursor.moveToNext());
                }
            }else{
                stores = null;
            }
            cursor.close();
        }
        return stores;
    }

    @Override
    public String toString() {
        return "Store{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
