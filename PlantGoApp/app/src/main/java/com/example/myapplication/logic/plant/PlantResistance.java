package com.example.myapplication.logic.plant;

public enum PlantResistance {
    WEAK("fragile"),
    STRONG("très résistance");

    private String value;

    PlantResistance(final String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return this.getValue();
    }
}
