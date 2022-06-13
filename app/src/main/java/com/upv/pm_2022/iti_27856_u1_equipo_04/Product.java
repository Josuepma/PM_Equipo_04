package com.upv.pm_2022.iti_27856_u1_equipo_04;

public class Product {

    private String name;
    private int id;
    private Brand brand;

    public Product(String name, Brand brand){
        this.setName(name);
        this.setBrand(brand);
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

    public int getId() {
        return id;
    }

    public Brand getBrand() {
        return brand;
    }

    public String getName() {
        return name;
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
