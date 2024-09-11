package com.mycompany.app.classes.projects.types;

import com.mycompany.app.enums.TypeOfProject;
import com.mycompany.app.enums.TypeOfSoil;

public final class Garden extends Structure {
    TypeOfSoil typeOfSoil;
    float squareMetersOfSoil;

    public Garden(long squareMeters, TypeOfSoil typeOfSoil, float squareMetersOfSoil) {
        super(TypeOfProject.GARDEN, squareMeters);
        this.typeOfSoil = typeOfSoil;
        this.squareMetersOfSoil = squareMetersOfSoil;
    }

    @Override
    public void printInformation() {
        CONSOLE.info("Garde containing:" + typeOfSoil.label + " soil " + squareMetersOfSoil + " square meters of soil" + super.generalInfo());
    }
}
