package com.upv.pm_2022.iti_27856_equipo_04;

public class Brand {
    private String name;
    private int id;

    public Brand(String name){
        this.setName(name);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName(){
        return this.name;
    }

    public int getId() {
        return this.id;
    }
}
