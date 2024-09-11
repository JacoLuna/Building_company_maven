package com.mycompany.app.classes.People;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mycompany.app.classes.interfaces.Identifiable;
import com.mycompany.app.classes.interfaces.JsonStorable;
import com.mycompany.app.classes.interfaces.Printable;
import com.mycompany.app.enums.Countries;
import com.mycompany.app.enums.TypeOfPerson;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDate;

public abstract class Person implements Printable, Identifiable, JsonStorable {
    protected static ObjectMapper objectMapper = new ObjectMapper();
    protected static final Logger CONSOLE_ERROR = LogManager.getLogger("ConsoleErrorLogger");
    protected static final Logger CONSOLE = LogManager.getLogger("ConsoleLogger");
    protected TypeOfPerson type;
    private static int globalId;
    protected int id;
    public String name;
    public String lastName;
    protected Countries country;
    LocalDate BDay;

    static {
        globalId = 0;
    }

    public Person() {
    }
    public Person(String name, String lastName, Countries country, LocalDate BDay) {
        setId();
        this.name = name;
        this.lastName = lastName;
        this.country = country;
        this.BDay = BDay;
    }

    public static int getGlobalId() {
        return globalId;
    }
    @Override
    public final int getId() {
        return id;
    }

    public TypeOfPerson getType() {
        return type;
    }
    public Countries getCountry(){
        return country;
    }
    public LocalDate getBDay(){
        return BDay;
    }
    //START SETTERS
    private static void setGlobalId() {
        Person.globalId++;
    }
    @Override
    public final void setId() {
        int value = getGlobalId();
        this.id = value++;
        setGlobalId();
    }

    //END SETTER

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return getId() == person.getId() &&
                type.equals(person.type) &&
                name.equals(person.name) &&
                lastName.equals(person.lastName) &&
                country.equals( person.country) &&
                BDay.equals(person.BDay);
    }

    @Override
    public int hashCode() {
        return 21 * getId() + type.hashCode() + name.hashCode() + lastName.hashCode() + country.hashCode() + BDay.hashCode() ;
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

//                ", project=" + ArraysList.toString(projects) +
//                '}';
    }
}
