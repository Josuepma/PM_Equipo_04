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
import java.util.Date;

public class Comparative {

    private int id;
    private double difference;
    private Price price1;
    private Price price2;
    private String date;
    private static final String TABLE = "Comparative";

    public Comparative(int id,double difference, String date ,Price price1, Price price2) {
        setId(id);
        setDifference(difference);
        setDate(date);
        setPrice1(price1);
        setPrice2(price2);
    }

    public Comparative(Price price1, Price price2) {
        setPrice1(price1);
        setPrice2(price2);
        Double difference = Math.abs(price1.getPrice() - price2.getPrice());
        setDifference(difference);
    }

    public Comparative(){

    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDifference(double difference) {
        this.difference = difference;
    }

    public int getId() {
        return id;
    }

    public double getDifference() {
        return difference;
    }

    public Price getPrice1() {
        return price1;
    }

    public void setPrice1(Price price1) {
        this.price1 = price1;
    }

    public Price getPrice2() {
        return price2;
    }

    public void setPrice2(Price price2) {
        this.price2 = price2;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public static void insert(Context context, Comparative comparative){
        DataBase usdbh = new DataBase(context);
        SQLiteDatabase db = usdbh.getWritableDatabase();
        if(db != null){
            ContentValues values = new ContentValues();
            values.put("difference",comparative.getDifference());
            //values.put("date",comparative.getDate().toString());
            values.put("id_price_1",comparative.getPrice1().getId());
            values.put("id_price_2",comparative.getPrice2().getId());
            db.insert(TABLE,null,values);
            Toast.makeText(context, "dato " + comparative + " insertado", Toast.LENGTH_SHORT).show();
        }
    }

    public static Comparative get(Context context, int id){
        DataBase usdbh = new DataBase(context);
        SQLiteDatabase db = usdbh.getWritableDatabase();
        if(db != null){
            Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE + " WHERE id = " + id,null);
            if(cursor.getCount()!=0 && cursor.moveToFirst()){
                String date = cursor.getString(cursor.getColumnIndexOrThrow("date"));
                return new Comparative(
                        cursor.getInt(cursor.getColumnIndexOrThrow("id")),
                        cursor.getDouble(cursor.getColumnIndexOrThrow("difference")),
                        date,
                        Price.get(context,cursor.getInt(cursor.getColumnIndexOrThrow("id_price_1"))),
                        Price.get(context,cursor.getInt(cursor.getColumnIndexOrThrow("id_price_2")))
                );
            }
        }
        return null;
    }

    public static ArrayList<Comparative> getAll(Context context){
        ArrayList<Comparative> comparatives = new ArrayList<>();
        DataBase usdbh = new DataBase(context);
        SQLiteDatabase db = usdbh.getWritableDatabase();
        if(db != null){
            Cursor cursor = db.rawQuery("SELECT * from " + TABLE,null);
            if (cursor.getCount()!=0 && cursor.moveToFirst()){
                do {
                    String date = cursor.getString(cursor.getColumnIndexOrThrow("date"));
                    Comparative comparative = new Comparative();
                    comparative.setId(cursor.getInt(cursor.getColumnIndexOrThrow("id")));
                    comparative.setDifference(cursor.getDouble(cursor.getColumnIndexOrThrow("difference")));
                    comparative.setDate(date);
                    comparative.setPrice1(Price.get(context,cursor.getInt(cursor.getColumnIndexOrThrow("id_price_1"))));
                    comparative.setPrice2(Price.get(context,cursor.getInt(cursor.getColumnIndexOrThrow("id_price_2"))));
                    comparatives.add(comparative);
                }while(cursor.moveToNext());
            }
            cursor.close();
        }
        return comparatives;
    }

    @Override
    public String toString() {
        return "difference " + difference + "\n" +
                "price1: " + price1 +
                "price2: " + price2 +
                " comparative made in" + date;
    }
}
