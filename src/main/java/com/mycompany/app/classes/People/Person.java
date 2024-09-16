package com.mycompany.app.classes.People;

import com.mycompany.app.classes.interfaces.*;
import com.mycompany.app.classes.projects.types.Structure;
import com.mycompany.app.classes.services.DataService;
import com.mycompany.app.enums.Countries;
import com.mycompany.app.enums.TypeOfPerson;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Stream;

public abstract class Person implements Printable {
    protected int id;
    protected TypeOfPerson type;
    public String name;
    public String lastName;
    protected Countries country;
    public LocalDate BDay;

    public Person() {
    }

    public Person(String name, String lastName, Countries country, LocalDate BDay) {
        this.name = name;
        this.lastName = lastName;
        this.country = country;
        this.BDay = BDay;
        setId();
    }

    //START GETTERS
    public int getId() {
        return id;
    }
    public TypeOfPerson getType() {
        return type;
    }
    public String getName() {
        return name;
    }
    public String getLastName() {
        return lastName;
    }
    public Countries getCountry() {
        return country;
    }
    public LocalDate getBDay() {
        return BDay;
    }
    //END GETTERS

    //START SETTERS
    public void setId() {
        this.id = getLastId(getType()) + 1;
    }
    //END SETTER

    public int getLastId(TypeOfPerson typeOfPerson){
        Set<Worker> workers;
        Set<Client> clients;
        if (typeOfPerson == TypeOfPerson.CLIENT){
            clients = (Set<Client>) DataService.getClients();
            Optional<Client> lastPerson = clients.stream().max(Comparator.comparing(Person::getId));
            return lastPerson.get().id;
        }
        else{
            workers = (Set<Worker>) DataService.getWorkers();
            Optional<Worker> lastPerson = workers.stream().max(Comparator.comparing(Person::getId));
            return lastPerson.get().id;
        }
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return getId() == person.getId() &&
                type.equals(person.type) &&
                name.equals(person.name) &&
                lastName.equals(person.lastName) &&
                country.equals(person.country) &&
                BDay.equals(person.BDay);
    }

    @Override
    public int hashCode() {
        return 21 * getId() + type.hashCode() + name.hashCode() + lastName.hashCode() + country.hashCode() + BDay.hashCode();
    }

    @Override
    public String toString() {
        return "Person{" +
                "type='" + type + '\'' +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", country='" + country.name + '\'' +
                ", BDay=" + BDay +
                '}';
    }
}
