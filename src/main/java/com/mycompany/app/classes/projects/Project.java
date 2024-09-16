package com.mycompany.app.classes.projects;

import com.mycompany.app.classes.Exceptions.WorkerException;
import com.mycompany.app.classes.People.Client;
import com.mycompany.app.classes.People.Worker;
import com.mycompany.app.classes.Utils;
import com.mycompany.app.classes.interfaces.Printable;
import com.mycompany.app.classes.services.MenuService;
import com.mycompany.app.enums.TypeOfProject;
import com.mycompany.app.enums.WorkerExceptionCode;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;

public class Project implements Printable {
    int id;
    LocalDate startingDate;
    LocalDate projectedEnd;
    LocalDate endingDate;
    TypeOfProject projectType;
    String projectName;
    Client client;
    ArrayList<Worker> workers;

    public Project(LocalDate startingDate, LocalDate projectedEnd, TypeOfProject projectType, String projectName, Client client) {
        this.startingDate = startingDate;
        this.projectedEnd = projectedEnd;
        this.projectType = projectType;
        this.projectName = projectName;
        this.client = client;
        this.workers = new ArrayList<>();
    }

    public ArrayList<Worker> getWorkers() {
        return workers;
    }

    public void setWorkers(ArrayList<Worker> workers) {
        workers.forEach(w -> addWorker(w));
    }
    public void addWorker(Worker worker){
        try {
            if (!this.workers.isEmpty()){
                for (Worker wk : this.workers){
                    if (wk.equals(worker)){
                        throw new WorkerException(WorkerExceptionCode.EXISTING_WORKER.codeNumber);
                    }
                }
            }
            this.workers.add(worker);
        }
        catch (WorkerException workerException){
            Utils.CONSOLE_ERROR.error(workerException.getMessage());
        }
    }
    public void setEndingDate(LocalDate endingDate) {
        if (startingDate.isBefore(endingDate)){
            this.endingDate = endingDate;
        }else {
            System.out.println("The ending date must be after the starting date");
        }
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Project project = (Project) object;
        return Objects.equals(startingDate, project.startingDate) && Objects.equals(projectedEnd, project.projectedEnd) && Objects.equals(endingDate, project.endingDate) && projectType == project.projectType && Objects.equals(projectName, project.projectName) && Objects.equals(client, project.client) && Objects.equals(workers, project.workers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(startingDate, projectedEnd, endingDate, projectType, projectName, client, workers);
    }

    @Override
    public String toString() {
        return "Project{" +
                "startingDate=" + startingDate +
                ", projectedEnd=" + projectedEnd +
                ", endingDate=" + endingDate +
                ", projectType=" + projectType +
                ", projectName='" + projectName + '\'' +
                ", client=" + client +
                '}';
    }

    @Override
    public void printInformation() {
        MenuService menuSrc = new MenuService();
        menuSrc.printFrame("Project","Project".length());
        menuSrc.printFrame(startingDate.toString(), 100);
        menuSrc.printFrame(projectedEnd.toString(),100);
        if (endingDate != null){
            menuSrc.printFrame(endingDate.toString(),100);
        }
        menuSrc.printFrame(projectType.toString(),100);
        menuSrc.printFrame(projectName,100);
        menuSrc.printFrame(client.toString(),100);
        menuSrc.printFrame("workers", 100);
        if (workers != null){
            for (Worker worker : workers){
                Utils.CONSOLE.info(worker.name + " " + worker.lastName);
            }
        }
        Utils.CONSOLE.info("Project{" +
                    "startingDate=" + startingDate +
                    ", projectedEnd=" + projectedEnd +
                    ", endingDate=" + endingDate +
                    ", projectType=" + projectType +
                    ", projectName='" + projectName + '\'' +
                    ", client=" + client +
                    '}');
    }
}
