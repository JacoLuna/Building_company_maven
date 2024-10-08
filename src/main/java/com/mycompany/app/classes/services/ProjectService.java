package com.mycompany.app.classes.services;

import com.mycompany.app.classes.People.Client;
import com.mycompany.app.classes.People.Person;
import com.mycompany.app.classes.People.Worker;
import com.mycompany.app.classes.Utils;
import com.mycompany.app.classes.projects.Project;
import com.mycompany.app.enums.TypeOfProject;

import javax.xml.crypto.Data;
import java.awt.*;
import java.time.LocalDate;
import java.util.*;
import java.util.List;

public class ProjectService {
    public Project createProject(){
        String[] typeOfProjects = Arrays.stream(TypeOfProject.values()).map(Enum::name).toArray(String[]::new);
        Project project = null;
        LocalDate startingDate, projectedEnd;
        String projectName;
        int projectIndex, ans;
        Client client;
        ArrayList<Worker> workers = new ArrayList<>();
        startingDate = InputService.readValidDate("Starting date");
        projectedEnd = InputService.readValidDate("Projected date");
        projectName = InputService.stringAns("Introduce the name of the project");
        projectIndex = getProjectIndex(typeOfProjects);
        client = (Client) getPerson(DataService.getClients());
        project = new Project(startingDate, projectedEnd, TypeOfProject.valueOf(typeOfProjects[projectIndex]), projectName, client);
        do {
            MenuService.printMenu("Add worker",new String[]{"Finish Project", "Add worker"});
            ans = InputService.setInput(List.of(0,1),Integer.class);
            if (ans == 1)
                project.addWorker((Worker) getPerson(DataService.getWorkers()));
            else if(project.getWorkers().isEmpty())
                Utils.CONSOLE_ERROR.error("1 worker is required at least");
        }while (ans != 0 || project.getWorkers().isEmpty());
        System.out.println(project.toString());
        return project;
    }

    private int getProjectIndex(String[] typeOfProjects) {
        int projectIndex;
        do {
            String prompt = "What type of project do you want to do?";
            MenuService.printMenu(prompt, typeOfProjects, prompt.length() * 2);
            projectIndex = InputService.setInput(Utils.createIndexList(typeOfProjects.length),Integer.class);
        } while (projectIndex == -1);
        return projectIndex;
    }

    private <T extends Person>Person getPerson(Collection<T> people){
        Person person = null;
        int selectedId;
        List<Integer> Ids = new ArrayList<>();
        people.forEach(p -> {
            Ids.add(p.getId());
            Utils.CONSOLE.info(p.getId() + " " + p.getName() + " " + p.getLastName());
        });
        String prompt = "Insert the id : ";
        selectedId = InputService.setInput(prompt, Ids, Integer.class);
        person = (Person)people.stream().filter(p -> p.getId() == selectedId).toArray()[0];
        return person;
    }
}
