package com.mycompany.app.classes.projects.types;

import com.mycompany.app.classes.Utils;
import com.mycompany.app.enums.TypeOfProject;
import com.mycompany.app.enums.TypeOfSoil;

public final class Garden extends Structure {
    TypeOfSoil typeOfSoil;
    float squareMetersOfSoil;

    public Garden(){
    }

    public TypeOfSoil getTypeOfSoil() {
        return typeOfSoil;
    }

    public float getSquareMetersOfSoil() {
        return squareMetersOfSoil;
    }

    public Garden(int id, long squareMeters, TypeOfSoil typeOfSoil, float squareMetersOfSoil) {
        super(TypeOfProject.GARDEN, squareMeters);
        this.typeOfSoil = typeOfSoil;
        this.squareMetersOfSoil = squareMetersOfSoil;
    }
    public Garden(long squareMeters, TypeOfSoil typeOfSoil, float squareMetersOfSoil) {
        super(TypeOfProject.GARDEN, squareMeters);
        this.typeOfSoil = typeOfSoil;
        this.squareMetersOfSoil = squareMetersOfSoil;
    }

    @Override
    public void printInformation() {
        Utils.CONSOLE.info("Garde containing:" + typeOfSoil.label + " soil " + squareMetersOfSoil + " square meters of soil" + super.generalInfo());
    }
}
