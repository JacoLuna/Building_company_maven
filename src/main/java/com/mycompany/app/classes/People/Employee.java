package com.mycompany.app.classes.People;

import com.mycompany.app.classes.interfaces.Clonable;
import com.mycompany.app.enums.Countries;
import com.mycompany.app.enums.Experience;

import java.time.LocalDate;

public class Employee extends Person implements Clonable<Employee> {
    float salary;
    Experience experience;
    public Employee(String name, String lastName, Countries country, LocalDate BDay, float salary, Experience experience) {
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

    @Override
    public void clone(Employee employee) {
        this.id = employee.getId();
        this.name = employee.name;
        this.type = employee.type;
        this.lastName = employee.lastName;
        this.country = employee.country;
        this.BDay = employee.BDay;
    }
}
