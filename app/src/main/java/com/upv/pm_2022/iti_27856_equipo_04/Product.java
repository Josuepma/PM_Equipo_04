package com.upv.pm_2022.iti_27856_equipo_04;

public class Product {

    private String name;
    private int id;
    private int brandId;
    private double price;

    public Product(String name, int brandId, double price){
        this.setName(name);
        this.setBrandId(brandId);
        this.setPrice(price);
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBrandId(int brandId) {
        this.brandId = brandId;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public int getBrandId() {
        return brandId;
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
                ", brandId=" + brandId +
                ", price=" + price +
                '}';
    }
}
