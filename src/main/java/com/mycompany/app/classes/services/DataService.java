package com.mycompany.app.classes.services;

import com.mycompany.app.classes.People.Client;
import com.mycompany.app.classes.People.Person;
import com.mycompany.app.classes.People.Worker;
import com.mycompany.app.classes.projects.Project;
import com.mycompany.app.classes.projects.types.*;
import com.mycompany.app.enums.*;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DataService {
    static Set<Client> clients;
    static Set<Worker> workers;
    static Set<Structure> structures;
    static ArrayList<Project> projects;

    public void instantiateAll(){
        clients = createClients();
        workers = createWorkers();
        structures = createStructures();
        projects = createProjects();
    }
    private Set<Client> createClients(){
        return new HashSet<>() {{
            add(new Client("Jaco", "perez", Countries.CHINA, LocalDate.of(1990, 8, 5), false));
        }};
    }
    private Set<Worker> createWorkers(){
        return new HashSet<>(){{
            add(new Worker("Jaco","luna",Countries.ARGENTINA, LocalDate.of(1990, 8, 5), 1500 , Experience.JUNIOR));
            add(new Worker("Jaco","Pierini",Countries.ARGENTINA, LocalDate.of(1990, 8, 5), 1500 , Experience.JUNIOR));
            add(new Worker("Jaco","lona",Countries.ARGENTINA, LocalDate.of(1990, 8, 5), 1500 , Experience.JUNIOR));
            add(new Worker("Tomas","Piorini",Countries.ARGENTINA, LocalDate.of(1990, 8, 5), 1500 , Experience.JUNIOR));
        }};
    }
    private Set<Structure> createStructures(){
        return new HashSet<>(){{
            add(new ApartmentBuilding(30, 10, true));
            add(new Garden(15, TypeOfSoil.PEAT, 10));
            add(new House(30, 5, 2));
            add(new Pool(30, 10, 24));
        }};
    }

    private ArrayList<Project> createProjects(){
        return new ArrayList<>(){{
            add(new Project(LocalDate.of(2000,11,1), LocalDate.of(2001,11,1), TypeOfProject.HOUSE, "test project", (Client) clients.toArray()[0]));
        }};
    }
    public static Collection<Client> getClients() {
        return clients;
    }
    public static Collection<Worker> getWorkers() {
        return workers;
    }
    public static Collection<Structure> getStructures() {
        return structures;
    }

    public static ArrayList<Project> getProjects() {
        return projects;
    }

    public List<Person> filterPerson(String attribute, Object value){
        List<Person> people = Stream.concat(workers.stream(), clients.stream()).toList();
        return people.stream().filter( person -> {
                    String input = StringUtils.lowerCase(value.toString());
                    switch (attribute){
                        case "type" -> {
                            String type = StringUtils.lowerCase(TypeOfPerson.valueOf(person.getType().name()).toString());
                            return  type.equals(input);
                        }
                        case "id" -> {
                            return (person.getId()+"").equals(value);
                        }
                        case "name" -> {
                            String name = StringUtils.lowerCase(person.name);
                            return  name.equals(input);
                        }
                        case "lastName" -> {
                            return person.lastName.equals(value);
                        }
                        case "country" -> {
                            String country = StringUtils.lowerCase(Countries.valueOf(person.getCountry().name).toString());
                            return country.equals(input);
                        }
                        case "BDay" -> {
                            return person.getBDay().equals(value);
                        }
                        case "experience" ->{
                            if (person.getType() == TypeOfPerson.WORKER){
                                Worker worker = (Worker)person;
                                String experience = StringUtils.lowerCase( Experience.valueOf(worker.getExperience().name()).toString());
                                return experience.equals(input);
                            }else return false;
                        }
                        case "salary" ->{
                            if (person.getType() == TypeOfPerson.WORKER){
                                Worker worker = (Worker)person;
                                return worker.getSalary() == (float)value;
                            }else return false;
                        }
                        case "isEnterprise" ->{
                            if (person.getType() == TypeOfPerson.CLIENT){
                                Client client = (Client) person;
                                if ((boolean)value)
                                    return client.isEnterprise();
                                else
                                    return !client.isEnterprise();
                            }else return false;
                        }
                        case "amountOfProjects" -> {
                            if (person.getType() == TypeOfPerson.CLIENT){
                                Client client = (Client) person;
                                return client.getAmountOfProjects() == (int) value;
                            }else return false;
                        }
                        default -> {
                            return false;
                        }
                    }
                }
        ).collect(Collectors.toList());
    }
}
