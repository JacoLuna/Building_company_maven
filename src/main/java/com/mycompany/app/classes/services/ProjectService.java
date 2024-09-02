package com.mycompany.app.classes.services;

import com.mycompany.app.classes.projects.Project;

import java.time.LocalDate;

public class ProjectService {
    InputService inputSrv = new InputService();
    public Project createProject(){
        LocalDate startingDate, projectedEnd;
        String projectName;
        System.out.println("Starting date");
        startingDate = inputSrv.readValidDate();
        System.out.println("projected date");
        projectedEnd = inputSrv.readValidDate();
        projectName = inputSrv.stringAns("Introduce the name of the project");
        //TODO print list of available clients
        //TODO connect project with structure
        return null;
    }
}
