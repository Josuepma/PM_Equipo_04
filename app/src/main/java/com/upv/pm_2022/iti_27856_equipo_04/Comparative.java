package com.upv.pm_2022.iti_27856_equipo_04;

public class Comparative {

    private int id;
    private double difference;
    private Product Product1;
    private Product Product2;

    public Comparative(Product product1, Product product2) {
        Product1 = product1;
        Product2 = product2;
        Double difference = Math.abs(product1.getPrice() - product2.getPrice());
        this.setDifference(difference);
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDifference(double difference) {
        this.difference = difference;
    }

    public void setProduct1(Product product1) {
        Product1 = product1;
    }

    public void setProduct2(Product product2) {
        Product2 = product2;
    }

    public int getId() {
        return id;
    }

    public Product getProduct1() {
        return Product1;
    }

    public Product getProduct2() {
        return Product2;
    }

    public double getDifference() {
        return difference;
    }

    @Override
    public String toString() {
        return "Comparative{" +
                "id=" + id +
                ", difference=" + difference +
                ", Product1=" + Product1 +
                ", Product2=" + Product2 +
                '}';
    }
}
