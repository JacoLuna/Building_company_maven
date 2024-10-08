package com.mycompany.app.classes.projects.types;

import com.mycompany.app.classes.Utils;
import com.mycompany.app.enums.TypeOfProject;

public final class House extends Structure {
    int rooms;
    int bathrooms;

    public House(){
    }

    public int getRooms() {
        return rooms;
    }

    public int getBathrooms() {
        return bathrooms;
    }

    public House(int id, long squareMeters, int rooms, int bathrooms) {
        super(TypeOfProject.HOUSE, squareMeters);
        this.rooms = rooms;
        this.bathrooms = bathrooms;
    }
    public House(long squareMeters, int rooms, int bathrooms) {
        super(TypeOfProject.HOUSE, squareMeters);
        this.rooms = rooms;
        this.bathrooms = bathrooms;
    }

    @Override
    public void printInformation() {
        Utils.CONSOLE.info("House containing:" + rooms + " rooms " + bathrooms + " bathrooms" + super.generalInfo());
    }

    @Override
    public String toString() {
        return "House{" +
                "rooms=" + rooms +
                ", bathrooms=" + bathrooms +
                ", id=" + id +
                ", name=" + name +
                ", price=" + price +
                ", squareMeters=" + squareMeters +
                '}';
    }
}
