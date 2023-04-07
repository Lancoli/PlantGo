package com.example.myapplication.logic.plant;

public enum PlantLightNeeds {
    LOW("Faible"),
    MEDIUM("Moyen"),
    IMPORTANT("Fort");

    private String value;

    PlantLightNeeds(final String value) {
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
