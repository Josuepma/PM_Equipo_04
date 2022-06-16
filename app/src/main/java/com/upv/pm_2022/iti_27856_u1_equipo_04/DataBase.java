package com.upv.pm_2022.iti_27856_u1_equipo_04;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;

import androidx.annotation.Nullable;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class DataBase extends SQLiteOpenHelper {

    private String[] create = new String[]{
            "CREATE TABLE Brand" +
                    "(id INTEGER PRIMARY KEY, " +
                    "name TEXT)",
            "CREATE TABLE Product" +
                    "(id INTEGER PRIMARY KEY, " +
                    "name TEXT," +
                    "id_brand REFERENCES Brand(id))",
            "CREATE TABLE Store" +
                    "(id INTEGER PRIMARY KEY, " +
                    "name TEXT, " +
                    "address TEXT)",
            "CREATE TABLE Price" +
                    "(id INTEGER PRIMARY KEY, " +
                    "price DECIMAL(5,2), " +
                    "date DATETIME DEFAULT CURRENT_TIMESTAMP," +
                    "id_store REFERENCES Store(id)," +
                    "id_product REFERENCES Product(id))",
            "CREATE TABLE Comparative" +
                    "(id INTEGER PRIMARY KEY, " +
                    "difference DECIMAL(5,2)," +
                    "date DATETIME DEFAULT CURRENT_TIMESTAMP, " +
                    "id_price_1 REFERENCES Price(id)," +
                    "id_price_2 REFERENCES Price(id))"
    };

    public String[] delete = new String[]{
            "DROP TABLE IF EXISTS Comparative",
            "DROP TABLE IF EXISTS Price",
            "DROP TABLE IF EXISTS Store",
            "DROP TABLE IF EXISTS Product",
            "DROP TABLE IF EXISTS Brand"
    };

    public static Boolean exportToCsv(Context context){
        String state = Environment.getExternalStorageState();
        if (!Environment.MEDIA_MOUNTED.equals(state)) {
            return false;
        }else{
            File exportDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
            if (!exportDir.exists()){
                exportDir.mkdirs();
            }
            File file;
            PrintWriter printWriter = null;
            try{
                file = new File(exportDir, "Brand.csv");
                file.createNewFile();
                printWriter = new PrintWriter(new FileWriter(file));
                ArrayList<Brand> brands = Brand.getAll(context);
                for (int i = 0; i < brands.size();i++){
                    printWriter.println(brands.get(i).toStringCsv());
                }
                printWriter.close();


                file = new File(exportDir, "Product.csv");
                file.createNewFile();
                printWriter = new PrintWriter(new FileWriter(file));
                ArrayList<Product> products = Product.getAll(context);
                for (int i = 0; i < products.size();i++){
                    printWriter.println(products.get(i).toStringCsv());
                }
                printWriter.close();

                file = new File(exportDir, "Store.csv");
                file.createNewFile();
                printWriter = new PrintWriter(new FileWriter(file));
                ArrayList<Store> stores = Store.getAll(context);
                for (int i = 0; i < stores.size();i++){
                    printWriter.println(stores.get(i).toStringCsv());
                }
                printWriter.close();

                file = new File(exportDir, "Price.csv");
                file.createNewFile();
                printWriter = new PrintWriter(new FileWriter(file));
                ArrayList<Price> prices = Price.getAll(context);
                for (int i = 0; i < prices.size();i++){
                    printWriter.println(prices.get(i).toStringCsv());
                }
                printWriter.close();

                file = new File(exportDir, "Comparative.csv");
                file.createNewFile();
                printWriter = new PrintWriter(new FileWriter(file));
                ArrayList<Comparative> comparatives = Comparative.getAll(context);
                for (int i = 0; i < comparatives.size();i++){
                    printWriter.println(comparatives.get(i).toStringCsv());
                }
                printWriter.close();
            } catch (IOException | SQLException e) {
                e.printStackTrace();
                return false;
            }
        }
        return true;
    }

    public static Boolean importFromCsv(Context context){
        DataBase usdbh = new DataBase(context);
        SQLiteDatabase db = usdbh.getWritableDatabase();
        for (int i = 0; i < usdbh.delete.length;i++)
            db.execSQL(usdbh.delete[i]);
        for (int i = 0; i < usdbh.create.length;i++)
            db.execSQL(usdbh.create[i]);

        String state = Environment.getExternalStorageState();
        if (!Environment.MEDIA_MOUNTED.equals(state)) {
            return false;
        }else {
            File importDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
            if (!importDir.exists()) {
                importDir.mkdirs();
            }
            try{
                FileReader file = new FileReader(new File(importDir, "Brand.csv"));
                BufferedReader buffer = new BufferedReader(file);
                String line = "";
                while((line = buffer.readLine())!=null){
                    String[] obj = line.split(",");
                    Brand.insert(context, new Brand(
                            Integer.parseInt(obj[0]),
                            obj[1]
                    ));
                }
                file.close();
                buffer.close();

                file = new FileReader(new File(importDir, "Product.csv"));
                buffer = new BufferedReader(file);
                line = "";
                while((line = buffer.readLine())!=null){
                    String[] obj = line.split(",");
                    Product.insert(context, new Product(
                            Integer.parseInt(obj[0]),
                            obj[1],
                            Brand.get(context,Integer.parseInt(obj[2]))
                    ));
                }
                file.close();
                buffer.close();

                file = new FileReader(new File(importDir, "Store.csv"));
                buffer = new BufferedReader(file);
                line = "";
                while((line = buffer.readLine())!=null){
                    String[] obj = line.split(",");
                    Store.insert(context, new Store(
                            Integer.parseInt(obj[0]),
                            obj[1],
                            obj[2]
                    ));
                }
                file.close();
                buffer.close();

                file = new FileReader(new File(importDir, "Price.csv"));
                buffer = new BufferedReader(file);
                line = "";
                while((line = buffer.readLine())!=null){
                    String[] obj = line.split(",");
                    Price.insert(context, new Price(
                            Integer.parseInt(obj[0]),
                            Double.parseDouble(obj[1]),
                            obj[2],
                            Store.get(context,Integer.parseInt(obj[3])),
                            Product.get(context,Integer.parseInt(obj[4]))
                    ));
                }
                file.close();
                buffer.close();

                file = new FileReader(new File(importDir, "Comparative.csv"));
                buffer = new BufferedReader(file);
                line = "";
                while((line = buffer.readLine())!=null){
                    String[] obj = line.split(",");
                    Comparative.insert(context, new Comparative(
                            Integer.parseInt(obj[0]),
                            Double.parseDouble(obj[1]),
                            obj[2],
                            Price.get(context,Integer.parseInt(obj[3])),
                            Price.get(context,Integer.parseInt(obj[4]))
                    ));
                }
                file.close();
                buffer.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return true;
    }

    public DataBase(@Nullable Context context, @Nullable String name, @Nullable CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public DataBase(@Nullable Context context){//al chile que hueva ponerlo por separado
        super(context,"merch.db",null,1);
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
