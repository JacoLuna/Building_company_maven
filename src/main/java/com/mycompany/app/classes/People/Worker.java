package com.mycompany.app.classes.People;

import com.mycompany.app.classes.Utils;
import com.mycompany.app.enums.Countries;
import com.mycompany.app.enums.Experience;
import com.mycompany.app.enums.TypeOfPerson;

import java.time.LocalDate;

public class Worker extends Person{
    private float salary;
    private Experience experience;

    public Worker(){
    }

    public Worker(String name, String lastName, Countries country, LocalDate BDay, float salary, Experience experience) {
        super(name, lastName, country, BDay);
        this.salary = salary;
        this.experience = experience;
        super.type = TypeOfPerson.WORKER;
    }

    public float getSalary() {
        return salary;
    }

    public Experience getExperience() {
        return experience;
    }

    @Override
    public void printInformation() {
        Utils.CONSOLE.info("name: " + name +
                " lastName: " + lastName +
                " country: " + country +
                " salary: " + salary +
                " experience: " + experience.tittle);
    }
}
