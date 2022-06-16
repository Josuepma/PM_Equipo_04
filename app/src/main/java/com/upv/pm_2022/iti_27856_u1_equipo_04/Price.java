package com.upv.pm_2022.iti_27856_u1_equipo_04;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Price {
    private int id;
    private double price;
    private String date;
    private Store store;
    private Product product;
    private static final String TABLE = "Price";

    public Price(int id, double price, String date, Store store, Product product){
        setId(id);
        setPrice(price);
        setDate(date);
        setStore(store);
        setProduct(product);
    }

    public Price(double price, Store store, String date, Product product){
        setPrice(price);
        setDate(date);
        setStore(store);
        setProduct(product);
    }

    public Price(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public static void insert(Context context, Price price){
        DataBase usdbh = new DataBase(context);
        SQLiteDatabase db = usdbh.getWritableDatabase();
        if(db != null){
            ContentValues values = new ContentValues();
            values.put("price",price.getPrice());
            //values.put("date",price.getDate().toString());
            values.put("id_store",price.getStore().getId());
            values.put("id_product",price.getProduct().getId());
            db.insert(TABLE,null,values);
            price.date = Calendar.getInstance().getTime().toString();
            Toast.makeText(context, "dato " + price + " insertado", Toast.LENGTH_SHORT).show();
        }
    }

    public static Price get(Context context, int id){
        DataBase usdbh = new DataBase(context);
        SQLiteDatabase db = usdbh.getWritableDatabase();
        if(db != null){
            Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE + " WHERE id = " + id,null);
            if(cursor.getCount()!=0 && cursor.moveToFirst()){
                DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
                String date = null;
                date = cursor.getString(cursor.getColumnIndexOrThrow("date"));
                return new Price(
                        cursor.getInt(cursor.getColumnIndexOrThrow("id")),
                        cursor.getDouble(cursor.getColumnIndexOrThrow("price")),
                        date,
                        Store.get(context,cursor.getInt(cursor.getColumnIndexOrThrow("id_store"))),
                        Product.get(context,cursor.getInt(cursor.getColumnIndexOrThrow("id_product")))
                );
            }
        }
        return null;
    }

    public static ArrayList<Price> getAll(Context context){
        ArrayList<Price> prices = new ArrayList<>();
        DataBase usdbh = new DataBase(context);
        SQLiteDatabase db = usdbh.getWritableDatabase();
        if(db != null){
            Cursor cursor = db.rawQuery("SELECT * from " + TABLE,null);
            if (cursor.getCount()!=0){
                if (cursor.moveToFirst()){
                    do {
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        String date = null;
                        date = cursor.getString(cursor.getColumnIndexOrThrow("date"));
                        Price price = new Price();
                        price.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndexOrThrow("id"))));
                        price.setPrice(cursor.getDouble(cursor.getColumnIndexOrThrow("price")));
                        price.setDate(date);
                        price.setStore(Store.get(context,cursor.getInt(cursor.getColumnIndexOrThrow("id_store"))));
                        price.setProduct(Product.get(context,cursor.getInt(cursor.getColumnIndexOrThrow("id_product"))));
                        prices.add(price);
                    }while(cursor.moveToNext());
                }
            }
            cursor.close();
        }
        return prices;
    }

    @Override
    public String toString() {
        return price +
                " store " + store +
                " product " + product +
                " date of price: " + date + "\n";
    }

    public String toStringCsv(){
        return id + "," +
                price + "," +
                date + "," +
                store.getId() + "," +
                product.getId();
    }


}
