package com.mycompany.app.classes.interfaces;

import com.mycompany.app.classes.projects.Project;

import java.util.LinkedList;

public interface IHasProjects {
    LinkedList<Project> projects = new LinkedList<>();
    public LinkedList<Project> getProjects();
    public void addProject(Project project);
    public void removeProject(Project project);
    public void removeProject(Integer projectIndex);
    public Project getProject(Integer projectIndex);
    public int getProject(Project project);
    public void setProject(int projectIndex, Project project);
    public void clearProjects();
}
