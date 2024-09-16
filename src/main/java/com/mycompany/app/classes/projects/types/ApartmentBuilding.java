package com.mycompany.app.classes.projects.types;

import com.mycompany.app.classes.Utils;
import com.mycompany.app.enums.TypeOfProject;

public final class ApartmentBuilding extends Structure {
    int storeys;
    boolean MRP; //multipurpose room

    public ApartmentBuilding(){
    }

    public int getStoreys() {
        return storeys;
    }

    public boolean isMRP() {
        return MRP;
    }

    public ApartmentBuilding(int id, long squareMeters, int storeys, boolean MRP) {
        super(TypeOfProject.APARTMENT, squareMeters);
        this.storeys = storeys;
        this.MRP = MRP;
    }
    public ApartmentBuilding(long squareMeters, int storeys, boolean MRP) {
        super(TypeOfProject.APARTMENT, squareMeters);
        this.storeys = storeys;
        this.MRP = MRP;
    }

    @Override
    public void printInformation() {
        Utils.CONSOLE.info("Apartment building containing:" + storeys + " storeys " + ((MRP)?"a multipurpose room":"") + super.generalInfo());
    }
}
