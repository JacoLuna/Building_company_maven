package com.mycompany.app.classes.services;

import com.mycompany.app.classes.People.Client;
import com.mycompany.app.classes.People.Person;
import com.mycompany.app.classes.People.Worker;
import com.mycompany.app.classes.projects.Project;
import com.mycompany.app.classes.projects.types.*;
import com.mycompany.app.enums.Countries;
import com.mycompany.app.enums.Experience;
import com.mycompany.app.enums.TypeOfProject;
import com.mycompany.app.enums.TypeOfSoil;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

public class DefaultDataService {
    static Collection<Client> clients;
    static Collection<Worker> workers;
    static Collection<Structure> structures;
    static ArrayList<Project> projects;

    public void instantiateAll(){
        clients = createClients();
        workers = createWorkers();
        structures = createStructures();
        projects = createProjects();
    }
    public Collection<Client> createClients(){
        return new ArrayList<Client>(){{
            add(new Client("clients_name_","clients_lastName_", Countries.CHINA, LocalDate.of(1990, 8, 5),false));
        }};
    }
    public Collection<Worker> createWorkers(){
        return new ArrayList<Worker>(){{
            add(new Worker("worker_name_","worker_lastName_",Countries.ARGENTINA, LocalDate.of(1990, 8, 5), 1500 , Experience.JUNIOR));
        }};
    }
    public Collection<Structure> createStructures(){
        return new ArrayList<Structure>(){{
            add(new ApartmentBuilding(30, 10, true));
            add(new Garden(15, TypeOfSoil.PEAT, 10));
            add(new House(30, 5, 2));
            add(new Pool(30, 10, 24));
        }};
    }

    public ArrayList<Project> createProjects(){
        return new ArrayList<Project>(){{
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
}
