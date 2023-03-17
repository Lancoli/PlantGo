package com.example.myapplication;

public class Plant {
    private int id;
    private String name;
    private String size;
    private String resistance;
    private String lightNeeds;

    public Plant(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getResistance() {
        return resistance;
    }

    public void setResistance(String resistance) {
        this.resistance = resistance;
    }

    public String getLightNeeds() {
        return lightNeeds;
    }

    public void setLightNeeds(String lightNeeds) {
        this.lightNeeds = lightNeeds;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
