package com.mycompany.app.classes.People;

import com.mycompany.app.enums.Countries;
import com.mycompany.app.enums.Experience;

import java.time.LocalDate;
import java.util.Dictionary;

public final class Associate extends Employee{
    Dictionary<Integer, Worker> workerDictionary;
    Dictionary<Integer, Client> clientDictionary;

    public Associate(String name, String lastName, Countries country, LocalDate BDay, float salary, Experience experience) {
        super(name, lastName, country, BDay, salary, experience);
    }
    @Override
    public String printInformation() {
        return "";
    }
}
