package com.mycompany.app.classes.services;

import com.mycompany.app.classes.Utils;
import com.mycompany.app.classes.projects.types.*;
import com.mycompany.app.enums.TypeOfProject;
import com.mycompany.app.enums.TypeOfSoil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StructureService {

    InputService inputSrv = new InputService();
    private MenuService menuSrv = new MenuService();
    
    public Structure createStructure() {
        String[] typeOfProjects = Arrays.stream(TypeOfProject.values()).map(Enum::name).toArray(String[]::new);
        String[] typeOfSoil = Arrays.stream(TypeOfSoil.values()).map(Enum::name).toArray(String[]::new);
        int projectIndex;
        long squareMeters;
        Structure structure;

        projectIndex = getProjectIndex(typeOfProjects);
        if (projectIndex != -1) {
            squareMeters = getSquareMeters();
            structure = createStructure(typeOfProjects[projectIndex], squareMeters, typeOfSoil);
            if (structure != null) {
                structure.printInformation();
            }
        } else {
            structure = null;
        }
        return structure;
    }

    private int getProjectIndex(String[] typeOfProjects) {
        int projectIndex;
        do {
            String prompt = "What type of project do you want to do?";
            menuSrv.printMenu(prompt, typeOfProjects, prompt.length() * 2);
            projectIndex = inputSrv.setInput(Utils.createIndexList(typeOfProjects.length),Integer.class);
        } while (projectIndex == -1);
        return projectIndex;
    }

    private long getSquareMeters() {
        long squareMeters;
        do {
            squareMeters = inputSrv.setInput("size of the project in square meters :", 0, 100, Integer.class);
        } while (squareMeters == -1);
        return squareMeters;
    }
    private int getSoilIndex(String[] typeOfSoil) {
        int soilIndex;
        do {
            String prompt = "what type of soil will be used?";
            menuSrv.printMenu(prompt, typeOfSoil,prompt.length()*2);
            soilIndex = inputSrv.setInput(Utils.createIndexList(typeOfSoil.length), Integer.class);
        } while (soilIndex == -1);
        return soilIndex;
    }

    private Structure createStructure(String projectType, long squareMeters, String[] typeOfSoil) {
        return switch (projectType) {
            case "HOUSE" -> createHouse(squareMeters);
            case "APARTMENT" -> createApartmentBuilding(squareMeters);
            case "POOL" -> createPool(squareMeters);
            case "GARDEN" -> createGarden(squareMeters, typeOfSoil);
            default -> null;
        };
    }

    private House createHouse(long squareMeters) {
        int rooms = inputSrv.setInput("how many rooms will the house have?", 0, 100, Integer.class);
        int bathrooms = inputSrv.setInput("how many bathrooms will the house have?", 0, 100, Integer.class);
        return new House(squareMeters, rooms, bathrooms);
    }

    private ApartmentBuilding createApartmentBuilding(long squareMeters) {
        int storeys = inputSrv.setInput("how many storeys will the Apartment Building have?", 0, 100, Integer.class);
        String prompt = "will the building have an MRP?";
        menuSrv.printMenu(prompt, new String[]{"Yes", "No"},prompt.length()*2);
        boolean MRP = inputSrv.setInput( Arrays.asList(0, 1), Integer.class) == 0;
        return new ApartmentBuilding(squareMeters, storeys, MRP);
    }

    private Pool createPool(long squareMeters) {
        float depth = inputSrv.setInput("how deep will the pool be?", 0.0f, 100.0f, Float.class);
        float temperature = inputSrv.setInput("how is the temperature going to be?", 20.0f, 30.0f, Float.class);
        return new Pool(squareMeters, depth, temperature);
    }

    private Garden createGarden(long squareMeters, String[] typeOfSoil) {
        int soilIndex = getSoilIndex(typeOfSoil);
        String prompt = "how many square meters of soil will be used?";
        float squareMetersOfSoil = inputSrv.setInput(prompt, 0.0f, (float)squareMeters, Float.class);
        return new Garden(squareMeters, TypeOfSoil.valueOf(typeOfSoil[soilIndex]), squareMetersOfSoil);
    }
}