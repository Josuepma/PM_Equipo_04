package com.upv.pm_2022.iti_27856_equipo_04;

public class Product {

    private String name;
    private int id;
    private Brand brand;
    private double price;

    public Product(String name, Brand brand, double price){
        this.setName(name);
        this.setBrand(brand);
        this.setPrice(price);
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBrand(Brand brandId) {
        this.brand = brand;
    }

    public void setPrice(double price) {
        this.price = price;
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

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", brand=" + brand +
                ", price=" + price +
                '}';
    }
}
