package com.upv.pm_2022.iti_27856_u1_equipo_04;

public class Comparative {

    private int id;
    private double difference;
    private Price price1;
    private Price price2;

    public Comparative(Price price1, Price price2) {
        setPrice1(price1);
        setPrice2(price2);
        Double difference = Math.abs(price1.getPrice() - price2.getPrice());
        this.setDifference(difference);
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

    @Override
    public String toString() {
        return "Comparative{" +
                "id=" + id +
                ", difference=" + difference +
                ", price1=" + price1 +
                ", price2=" + price2 +
                '}';
    }
}
