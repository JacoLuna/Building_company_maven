package com.mycompany.app.classes.People;

import com.mycompany.app.classes.interfaces.*;
import com.mycompany.app.enums.Countries;
import com.mycompany.app.enums.TypeOfPerson;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public abstract class Person implements Printable, Disectable {
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

    //END SETTER

//    public <Person> void parseToJson(Person person) {
//        ObjectMapper objectMapper = new ObjectMapper();
//        try {
//            objectMapper.writeValue(new File("src\\main\\java\\com\\mycompany\\app\\files\\test.json"), person);
//        }catch (Exception e){
//            e.getStackTrace();
//        }
//    }

    public List<String> getAttributes() {
        Field[] fields = Person.class.getDeclaredFields();
        List<String> attributes = new ArrayList<>();
        for (int i = 4; i < fields.length; i++) {
            attributes.add(fields[i].getName());
        }
        return attributes;
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
