package com.mycompany.app.classes.services;

import com.mycompany.app.classes.People.Client;
import com.mycompany.app.classes.People.Person;
import com.mycompany.app.classes.People.Worker;
import com.mycompany.app.classes.projects.Project;
import com.mycompany.app.classes.projects.types.*;
import com.mycompany.app.enums.TypeOfProject;
import com.mycompany.app.enums.TypeOfSoil;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

public class DefaultDataService {
    static Person[] clients = new Client[5];
    static Person[] workers = new Worker[5];
    static Collection<Structure> structures;
    static ArrayList<Project> projects;

    public void instantiateAll(){
        createClients();
        createWorkers();
        structures = createStructures();
        projects = createProjects();
    }
    public void createClients(){
        for (int i = 0; i < 5; i++) {
            clients[i] = new Client("clients_name_" + i,"clients_lastName_" + i,"clients_country_" + i, LocalDate.of(1990, 8, 5),false);
        }
    }
    public void createWorkers(){
        for (int i = 0; i < 5; i++) {
            workers[i] = new Worker("worker_name_" + i,"worker_lastName_" + i,"worker_country_" + i, LocalDate.of(1990, 8, 5), 1500 ,"junior");
        }

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
            add(new Project(LocalDate.of(2000,11,1), LocalDate.of(2001,11,1), TypeOfProject.HOUSE, "test project", (Client) clients[0]));
        }};
    }

    public static Person[] getClients() {
        return clients;
    }

    public static Person[] getWorkers() {
        return workers;
    }

    public static Collection<Structure> getStructures() {
        return structures;
    }

    public static ArrayList<Project> getProjects() {
        return projects;
    }
}
