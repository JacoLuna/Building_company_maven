package com.mycompany.app.enums;

public enum TypeOfProject {
    HOUSE("HOUSE",50000),
    APARTMENT("APARTMENT",50000),
    POOL("POOL",50000),
    GARDEN("GARDEN",50000);

    public final String name;
    public final float baseCost;

    TypeOfProject(String name, float baseCost) {
        this.name = name;
        this.baseCost = baseCost;
    }
}
