package com.mycompany.app;

import com.mycompany.app.classes.BuildingCompany;

import java.io.FileWriter;
import java.io.IOException;

public class App {
    public static void main(String[] args) {
        BuildingCompany bc = new BuildingCompany();
        bc.startProgram();
    }
}