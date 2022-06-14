package com.upv.pm_2022.iti_27856_u1_equipo_04;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import java.util.ArrayList;

public class Product {

    private String name;
    private int id;
    private Brand brand;
    private static final String TABLE = "Product";

    public Product(int id,String name, Brand brand){
        this.setId(id);
        this.setName(name);
        this.setBrand(brand);
    }

    public Product(String name, Brand brand){
        this.setName(name);
        this.setBrand(brand);
    }

    public Product(){

    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public int getId() {
        return id;
    }

    public Brand getBrand() {
        return brand;
    }

    public String getName() {
        return name;
    }

    public static void insert(Context context,Product product){
        DataBase usdbh = new DataBase(context);
        SQLiteDatabase db = usdbh.getWritableDatabase();
        if(db != null){
            //System.out.println(product.getBrand());
            ContentValues values = new ContentValues();
            values.put("name",product.getName());
            values.put("id_brand",product.getBrand().getId());
            db.insert(TABLE,null,values);
            Toast.makeText(context, "dato " + product + " insertado", Toast.LENGTH_SHORT).show();
        }
    }

    public static Product get(Context context, int id){
        DataBase usdbh = new DataBase(context);
        SQLiteDatabase db = usdbh.getWritableDatabase();
        if(db != null){
            Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE + " WHERE id = " + id,null);
            if(cursor.getCount()!=0 && cursor.moveToFirst()){
                return new Product(
                        cursor.getInt(cursor.getColumnIndexOrThrow("id")),
                        cursor.getString(cursor.getColumnIndexOrThrow("name")),
                        Brand.get(context,Integer.parseInt(cursor.getString(cursor.getColumnIndexOrThrow("id_brand"))))
                );
            }
        }
        return null;
    }

    public static ArrayList<Product> getAll(Context context){
        ArrayList<Product> products = new ArrayList<>();
        DataBase usdbh = new DataBase(context);
        SQLiteDatabase db = usdbh.getWritableDatabase();
        if(db != null){
            Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE,null);
            if (cursor.getCount()!=0){
                if (cursor.moveToFirst()){
                    do {
                        Product product = new Product();
                        product.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndexOrThrow("id"))));
                        product.setName(cursor.getString(cursor.getColumnIndexOrThrow("name")));
                        product.setBrand(Brand.get(context,cursor.getInt(cursor.getColumnIndexOrThrow("id_brand"))));
                        products.add(product);
                    }while(cursor.moveToNext());
                }
            }else{
                products = null;
            }
            cursor.close();
        }
        return products;
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", brand=" + brand +
                '}';
    }
}
