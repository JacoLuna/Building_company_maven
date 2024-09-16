package com.mycompany.app.classes.projects.types;

import com.mycompany.app.classes.interfaces.Printable;
import com.mycompany.app.classes.services.DataService;
import com.mycompany.app.enums.TypeOfProject;

import java.util.Comparator;
import java.util.Optional;
import java.util.Set;

public abstract class Structure implements Printable {
    protected int id;
    protected TypeOfProject name;
    protected float price;
    public long squareMeters;

    public Structure(){
    }
    public Structure(TypeOfProject name, long squareMeters) {
        this.name = name;
        this.squareMeters = squareMeters;
        this.price = name.baseCost * squareMeters;
        setId();
    }

    public final int getId() {
        return id;
    }
    public TypeOfProject getName() {
        return name;
    }
    public float getPrice() {
        return price;
    }
    public long getSquareMeters() {
        return squareMeters;
    }
    //START SETTERS
    public void setId() {
        this.id = getLastId() + 1;
    }
    //END SETTER
    public int getLastId(){
        Set<Structure> structures;
        structures = (Set<Structure>) DataService.getStructures();
        Optional<Structure> lastStructure = structures.stream().max(Comparator.comparing(Structure::getId));
        return lastStructure.map(structure -> structure.id).orElse(0);
    }

    protected String generalInfo(){
        return " and " + squareMeters + " square meters" + " with a price of " + price + "$";
    }

    public static void printThing(Printable thing){
        thing.printInformation();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Structure structure = (Structure) o;
        return getId() == structure.getId() && squareMeters == structure.squareMeters && name == structure.name && price == structure.price;
    }

    @Override
    public int hashCode() {
        return 21 * name.hashCode() + (int)squareMeters;
    }

    @Override
    public String toString() {
        return "Structure{" +
                "id=" + id +
                ", name=" + name +
                ", squareMeters=" + squareMeters +
                ", price=" + price +
                '}';
    }
}
