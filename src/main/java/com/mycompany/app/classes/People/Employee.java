package com.mycompany.app.classes.People;

import java.time.LocalDate;

public class Employee extends Person {
    int salary;
    String experience;
    public Employee(String name, String lastName, String country, LocalDate BDay, int salary, String experience) {
        super(name, lastName, country, BDay);
        this.salary = salary;
        this.experience = experience;
    }

    @Override
    public String printInformation() {
        return "name: " + name +
                "lastName: " + lastName +
                "country: " + country +
                "BDay: " + BDay +
                "salary: " + salary +
                "experience " + experience;
    }
}
