package com.mycompany.app.enums;

public enum Countries {
    ARGENTINA("ARGENTINA"),
    BRAZIL("BRAZIL"),
    UUSS("UUSS"),
    SPAIN("SPAIN"),
    PORTUGAL("PORTUGAL"),
    CHINA("CHINA"),
    INDIA("INDIA");

    public final String name;

    Countries(String name){
        this.name = name;
    }
}
