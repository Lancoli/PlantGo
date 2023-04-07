package com.example.myapplication.logic.plant;

public enum PlantSize {
    TINY("très petite"),
    SMALL("petite"),
    MEDIUM("moyenne"),
    LARGE("grande"),
    HUGE("énorme");

    private String value;

    PlantSize(final String value) {
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