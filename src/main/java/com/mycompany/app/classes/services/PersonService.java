package com.mycompany.app.classes.services;

import com.mycompany.app.classes.People.*;
import com.mycompany.app.enums.Countries;
import com.mycompany.app.enums.Experience;
import com.mycompany.app.enums.TypeOfPerson;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PersonService {

    InputService inputSrv = new InputService();
    MenuService menuSrv = new MenuService();
    public Person createPerson(){
        String name, lastName, country, prompt;
        LocalDate BDay;
        int personIndex, countryIndex;
        String[] typeOfPersonArray = Arrays.stream(TypeOfPerson.values()).map(Enum::name).toArray(String[]::new);
        String[] countries = Arrays.stream(Countries.values()).map(Enum::name).toArray(String[]::new);

        name = inputSrv.stringAns("Name:");
        lastName = inputSrv.stringAns("lastName:");
        menuSrv.printMenu("Country: ", countries);
        country = countries[inputSrv.setIntAns(createIndexList(countries.length))];
        BDay = inputSrv.readValidDate("Birthday");
        prompt = "Type of person: ";
        menuSrv.printMenu(prompt, typeOfPersonArray, prompt.length() * 2);
        personIndex = inputSrv.setIntAns(createIndexList(typeOfPersonArray.length));
        return switch (personIndex) {
            case 0, 1 -> createEmployee(name, lastName, Countries.valueOf(country), BDay, TypeOfPerson.valueOf(typeOfPersonArray[personIndex]));
            case 2 -> createClient(name, lastName, Countries.valueOf(country), BDay);
            default -> null;
        };
    }
    private List<Integer> createIndexList(int length) {
        List<Integer> indexList = new ArrayList<>();
        for (int i = 0; i < length; i++) {
            indexList.add(i);
        }
        return indexList;
    }

    private Worker createEmployee(String name,String lastName,Countries country,LocalDate BDay, TypeOfPerson typeOfPerson) {
        String[] experience = Arrays.stream(Experience.values()).map(Enum::name).toArray(String[]::new);
        float salary;
        int experienceIndex;
        Worker worker;
        String prompt = "experience level: ";
        menuSrv.printMenu(prompt, experience, prompt.length() * 2);
        experienceIndex = inputSrv.setIntAns(createIndexList(experience.length));
        salary = inputSrv.setFloatAns("Salary: ", 0, 100000000);
        worker = new Worker(name,lastName,country,BDay,salary,Experience.valueOf(experience[experienceIndex]));
        worker.printInformation();
        return worker;
    }
    private Client createClient(String name, String lastName, Countries country, LocalDate BDay){
        Client client;
        boolean isEnterprise = false;
        String prompt = "is the client and enterprise: ";
        menuSrv.printMenu(prompt, new String[]{"yes","no"}, prompt.length()*2);
        if (inputSrv.setIntAns(new ArrayList<>(){{add(0);add(1);}}) == 0){
            isEnterprise = true;
        }
        client = new Client(name,lastName,country,BDay,isEnterprise);
        client.printInformation();
        return client;
    }
}
