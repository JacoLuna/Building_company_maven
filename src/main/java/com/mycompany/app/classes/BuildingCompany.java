package com.mycompany.app.classes;

import com.google.gson.Gson;
import com.mycompany.app.classes.People.*;
import com.mycompany.app.classes.projects.types.Structure;
import com.mycompany.app.classes.services.*;
import com.mycompany.app.enums.Countries;
import com.mycompany.app.enums.Experience;
import com.mycompany.app.enums.TypeOfPerson;

import java.time.LocalDate;
import java.util.*;

public final class BuildingCompany {
    private static final int PEOPLE_OPTION = 1;
    private static final int STRUCTURE_OPTION = 2;
    private static final int MY_PROFILE_OPTION = 3;
    private static final int BEGIN_PROJECT_OPTION = 4;
    private static final int MANAGE_PROJECT_OPTION = 5;
    private static final int ERROR_REPORT_OPTION = 6;
    private static final int EXIT_OPTION = 0;

    private static final int ADD_PEOPLE_OPTION = 1;
    private static final int FILTER_PEOPLE_OPTION = 2;

    private static final int ADD_STRUCTURE_OPTION = 1;
    private static final int FILTER_STRUCTURE_OPTION = 2;

    MenuService menuSrv = new MenuService();
    InputService inputSrv = new InputService();
    DataService dataSrv = new DataService();
    StructureService structureSrv = new StructureService();
    ProjectService projectSrv = new ProjectService();
    PersonService personSrv = new PersonService();

    Map<Integer, Runnable> menuActions = new HashMap<>();
    public void startProgram() {
        int ans;
        dataSrv.instantiateAll();

        menuActions.put(PEOPLE_OPTION, this::handlePeopleOption);
        menuActions.put(STRUCTURE_OPTION, this::handleStructureOption);
        menuActions.put(BEGIN_PROJECT_OPTION, () -> DataService.getStructures().add(structureSrv.createStructure()));
        menuActions.put(MANAGE_PROJECT_OPTION, () -> DataService.getProjects().add(projectSrv.createProject()));
        menuActions.put(ERROR_REPORT_OPTION, () -> FileService.readFile("logs/app.log", false));

        do {
            menuSrv.printMenu("Menu", new String[]{"Exit","People", "Structures", "My profile", "Begin Project", "Manage project", "Read error reports"});
            ans = inputSrv.
                    setInput(Arrays.asList(EXIT_OPTION,PEOPLE_OPTION, STRUCTURE_OPTION, MY_PROFILE_OPTION, BEGIN_PROJECT_OPTION, MANAGE_PROJECT_OPTION, ERROR_REPORT_OPTION), 
                             Integer.class);
            if (ans != EXIT_OPTION)
                menuActions.get(ans).run();
        } while (ans != EXIT_OPTION);
    }

    private void handlePeopleOption(){
        Person person = null;
        int ans;
        do {
            menuSrv.printMenu("AMD",new String[]{"Exit", "Add people", "Filter people"});
            ans = inputSrv.setInput(Arrays.asList(EXIT_OPTION, ADD_PEOPLE_OPTION, FILTER_PEOPLE_OPTION), Integer.class);
            switch (ans){
                case  ADD_PEOPLE_OPTION:
                    person = personSrv.createPerson();
                    if (person.getType() == TypeOfPerson.CLIENT)
                        DataService.addClient((Client) person);
                    else
                        DataService.addWorker((Worker) person);
                    break;
                case FILTER_PEOPLE_OPTION :
                    personSrv.filterPeople();
                    break;
            }
        }while (ans != EXIT_OPTION);
    }

    private void handleStructureOption(){
        int ans;
        do {
            menuSrv.printMenu("Objects",new String[]{"Exit", "Add Structure", "Filter Structures", "Structures"});
            //TODO
            ans = inputSrv.setInput(Arrays.asList(EXIT_OPTION, ADD_STRUCTURE_OPTION, FILTER_STRUCTURE_OPTION), Integer.class);
            switch (ans){
                case ADD_STRUCTURE_OPTION:
                    structureSrv.createStructure();
                    break;
                case FILTER_STRUCTURE_OPTION:
                    break;
            }
        }while (ans != 0);
    }
}