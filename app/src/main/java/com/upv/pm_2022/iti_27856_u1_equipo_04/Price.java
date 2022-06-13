package com.upv.pm_2022.iti_27856_u1_equipo_04;

public class Price {
    private int id;
    private double price;
    private Store store;
    private Product product;

    public Price(double price,Store store, Product product){
        setPrice(price);
        setStore(store);
        setProduct(product);
    }

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

    @Override
    public String toString() {
        return "Price{" +
                "id=" + id +
                ", price=" + price +
                ", store=" + store +
                ", product=" + product +
                '}';
    }
}
