package com.example.myapplication.business.plant;

import com.example.myapplication.business.utils.Constants;

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

    public static String[] getLightNeedsOptions() {
       return new String[]{Constants.EMPTY_OPTION, PlantLightNeeds.LOW.toString(), PlantLightNeeds.MEDIUM.toString(), PlantLightNeeds.IMPORTANT.toString()};
    }

    public static String[] getResistanceOptions() {
        return new String[]{Constants.EMPTY_OPTION, PlantResistance.WEAK.toString(), PlantResistance.STRONG.toString()};
    }

    public static String[] getSizeOptions() {
        return new String[]{Constants.EMPTY_OPTION,PlantSize.TINY.toString(), PlantSize.SMALL.toString(), PlantSize.MEDIUM.toString(), PlantSize.LARGE.toString(), PlantSize.HUGE.toString()};
    }
}
