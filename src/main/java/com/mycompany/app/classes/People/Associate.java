package com.mycompany.app.classes.People;

import java.time.LocalDate;
import java.util.Dictionary;

public final class Associate extends Employee{
    Dictionary<Integer, Worker> workerDictionary;
    Dictionary<Integer, Receptionist> receptionistDictionary;

    public Associate(String name, String lastName, String country, LocalDate BDay, int salary, String experience) {
        super(name, lastName, country, BDay, salary, experience);
    }
    @Override
    public String printInformation() {
        return "";
    }
}
