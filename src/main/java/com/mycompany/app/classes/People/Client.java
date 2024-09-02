package com.mycompany.app.classes.People;

import com.mycompany.app.classes.interfaces.IHasProjects;
import com.mycompany.app.classes.projects.Project;
import com.mycompany.app.enums.Countries;
import com.mycompany.app.enums.TypeOfPerson;

import java.time.LocalDate;
import java.util.LinkedList;

public class Client extends Person implements IHasProjects {
    boolean isEnterprise;
    int amountOfProjects;
    public Client(String name, String lastName, Countries country, LocalDate BDay, boolean enterprise) {
        super(name, lastName, country, BDay);
        super.type = TypeOfPerson.CLIENT;
        this.isEnterprise = enterprise;
        this.amountOfProjects = 0;
    }

    @Override
    public String printInformation() {
        return "name: " + name +
                "lastName: " + lastName +
                "country: " + country +
                "BDay: " + BDay +
                "enterprise: " + ((isEnterprise)?"yes":"no");
    }

    @Override
    public String toString() {
        return "Client{" +
                "isEnterprise=" + isEnterprise +
                ", amountOfProjects=" + amountOfProjects +
                ", type='" + type + '\'' +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", country='" + country + '\'' +
                ", BDay=" + BDay +
                '}';
    }

    @Override
    public LinkedList<Project> getProjects() {
        return null;
    }

    @Override
    public void addProject(Project project) {

    }

    @Override
    public void removeProject(Project project) {

    }

    @Override
    public void removeProject(Integer projectIndex) {

    }

    @Override
    public Project getProject(Integer projectIndex) {
        return null;
    }

    @Override
    public int getProject(Project project) {
        return 0;
    }

    @Override
    public void setProject(int projectIndex, Project project){
        if (!getProject(projectIndex).equals(project)){
            projects.set(projectIndex, project);
        }else {
            //TODO project exception msg
        }
    }

    @Override
    public void clearProjects(){}

}
