package com.mycompany.app.enums;

public enum TypeOfSoil {
    SANDY("Sandy"),
    CLAY("Clay"),
    SILT("Silt"),
    PEAT("Peat"),
    LOAM("Loam"),
    CHALK("Chalk");

    public final String label;

    TypeOfSoil(String label) {
        this.label = label;
    }
}
