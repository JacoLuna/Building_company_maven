package com.mycompany.app.classes.services;

import com.mycompany.app.classes.People.Client;
import com.mycompany.app.classes.People.Person;
import com.mycompany.app.classes.People.Worker;
import com.mycompany.app.classes.Utils;
import com.mycompany.app.classes.projects.Project;
import com.mycompany.app.classes.projects.types.*;
import com.mycompany.app.enums.*;
import org.apache.commons.io.file.PathUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class DataService implements Runnable{
    private static Set<Client> clients = new HashSet<>();
    private static Set<Worker> workers = new HashSet<>();
    private static Set<Structure> structures = new HashSet<>();
    private static ArrayList<Project> projects;

    public void instantiateAll(){
        clients = createClients();
        workers = createWorkers();
        structures = createStructures();
        projects = createProjects();
    }    @Override
    public void run() {
        instantiateAll();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        Utils.CONSOLE.info("The program is loaded");
    }
    private Set<Client> createClients(){
        String file = "clients.json";
        if (!checkEmptyPath(file)){
            clients.addAll(JsonService.readJsonList(file, Client.class));
        }else {
            clients = new HashSet<>() {{
                add(new Client("Jaco", "perez", Countries.CHINA, LocalDate.of(1990, 8, 5), false));
            }};
            JsonService.writeJson(file, clients);
        }
        return  clients;
    }
    private Set<Worker> createWorkers(){
        String file = "workers.json";
        if (!checkEmptyPath(file)){
            workers.addAll(JsonService.readJsonList(file, Worker.class));
        }else {
            workers = new HashSet<>(){{
                add(new Worker("Jaco","luna",Countries.ARGENTINA, LocalDate.of(1990, 8, 5), 1500 , Experience.JUNIOR));
                add(new Worker("Jaco","Pierini",Countries.ARGENTINA, LocalDate.of(1990, 8, 5), 1500 , Experience.JUNIOR));
                add(new Worker("Jaco","lona",Countries.ARGENTINA, LocalDate.of(1990, 8, 5), 1500 , Experience.JUNIOR));
                add(new Worker("Tomas","Piorini",Countries.ARGENTINA, LocalDate.of(1990, 8, 5), 1500 , Experience.JUNIOR));
            }};
            JsonService.writeJson(file, workers);
        }
        return workers;
    }
    private Set<Structure> createStructures(){
        String file = "structures.json";
        if (!checkEmptyPath(file)){
            structures.addAll(JsonService.readJsonList(file, Structure.class));
        }else {
            structures = new HashSet<>(){{
                add(new ApartmentBuilding(30, 10, true));
                add(new Garden(15, TypeOfSoil.PEAT, 10));
                add(new House(30, 5, 2));
                add(new Pool(30, 10, 24));
            }};
            JsonService.writeJson(file, structures);
        }
        return structures;
    }

    private ArrayList<Project> createProjects(){
        return new ArrayList<>(){{
            add(new Project(LocalDate.of(2000,11,1), LocalDate.of(2001,11,1), TypeOfProject.HOUSE, "test project", (Client) clients.toArray()[0]));
        }};
    }

    private boolean checkEmptyPath(String objectName){
        boolean isEmpty = true;
        try {
            isEmpty = PathUtils.isEmpty(Paths.get(FileService.folder + "\\" + JsonService.folder + "\\" + objectName));
        }catch (IOException e){
            System.out.println(e);
        }
        return  isEmpty;
    }

    // START GETTERS
    public static Set<Client> getClients() {
        return clients;
    }
    public static Set<Worker> getWorkers() {
        return workers;
    }
    public static Set<Structure> getStructures() {
        return structures;
    }
    //END GETTERS

    //START ADD TO SETS
    public static void addProject(Project project){
        projects.add(project);
        JsonService.writeJson("projects.json",projects);
    }
    public static void addWorker(Worker worker){
        workers.add(worker);
        JsonService.writeJson("workers.json",workers);
    }
    public static void addClient(Client client){
        clients.add(client);
        JsonService.writeJson("clients.json",clients);
    }

    //END ADD TO SETS
    public static ArrayList<Project> getProjects() {
        return projects;
    }
}
