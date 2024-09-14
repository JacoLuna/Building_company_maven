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
    private static final int AMD_OPTION = 1;
    private static final int PRINT_OBJECTS_OPTION = 2;
    private static final int MY_PROFILE_OPTION = 3;
    private static final int BEGIN_PROJECT_OPTION = 4;
    private static final int MANAGE_PROJECT_OPTION = 5;
    private static final int ERROR_REPORT_OPTION = 6;
    private static final int EXIT_OPTION = 0;

    private static final int  PERSON_OPTION = 1;
    private static final int  STRUCTURE_OPTION = 2;
    private static final int  INFORMATION_OPTION = 1;
    private static final int  PROJECT_OPTION = 1;

    private static final int PRINT_PEOPLE_OPTION = 1;
    private static final int PRINT_STRUCTURE_OPTION = 2;

    //BEGIN_PROJECT_OPTION submenu
    private static final int BEGIN_PROJECT_OPTION_SUBMENU = 1;

    MenuService menuSrv = new MenuService();
    InputService inputSrv = new InputService();
    DataService dataSrv = new DataService();
    StructureService structureSrc = new StructureService();
    ProjectService projectSrv = new ProjectService();
    PersonService personSrv = new PersonService();
    JsonService<Person> jsonService = new JsonService<>();

    Map<Integer, Runnable> menuActions = new HashMap<>();
    public void startProgram() {
        int ans;
        dataSrv.instantiateAll();

        menuActions.put(AMD_OPTION, () -> handleAMDOption());
        menuActions.put(PRINT_OBJECTS_OPTION, () -> handlePrintObjectsOption());
        menuActions.put(BEGIN_PROJECT_OPTION, () -> handlePrintObjectsOption());
        menuActions.put(MANAGE_PROJECT_OPTION, () -> projectSrv.createProject());
        menuActions.put(ERROR_REPORT_OPTION, () -> FileService.readFile("logs/app.log"));

        Collection<Worker> list = DataService.getWorkers();
        Worker worker = (Worker) list.toArray()[0];
        JsonService.writeJson("src\\main\\java\\com\\mycompany\\app\\files\\test.json", list);

    }

    private void handleAMDOption(){
        Person person = null;
        int ans;
        do {
            menuSrv.printMenu("AMD",new String[]{"Exit", "Person","Structure"});
            ans = inputSrv.setInput(Arrays.asList(EXIT_OPTION, PERSON_OPTION,  STRUCTURE_OPTION), Integer.class);
            switch (ans){
                case  PERSON_OPTION:
                    person = personSrv.createPerson();
                    if (person.getType() == TypeOfPerson.CLIENT)
                        DataService.getClients().add((Client) person);
                    else
                        DataService.getWorkers().add((Worker) person);
                    break;
                case  STRUCTURE_OPTION:
                    //TODO AMD structures
                    break;
            }
        }while (ans != EXIT_OPTION);
    }

    private void handlePrintObjectsOption(){
        int ans;
        do {
            menuSrv.printMenu("Objects",new String[]{"Exit", "People", "Structures"});
            //TODO
            ans = inputSrv.setInput(Arrays.asList(EXIT_OPTION, PRINT_PEOPLE_OPTION, PRINT_STRUCTURE_OPTION), Integer.class);
            switch (ans){
                case PRINT_PEOPLE_OPTION :
                    personSrv.filterPeople();
                    break;
                case PRINT_STRUCTURE_OPTION:
                    break;
            }
        }while (ans != 0);
    }

    private void handleProjectoptions(){
        Structure structure = null;
        int ans;
        do {
            menuSrv.printMenu("Projects", new String[]{"Exit", "Begin project"});
            ans = inputSrv.setInput(Arrays.asList(EXIT_OPTION, BEGIN_PROJECT_OPTION_SUBMENU), Integer.class);
            if (ans == 0){
                structure = structureSrc.createStructure();
                DataService.getStructures().add(structure);
            }
        }while (ans != EXIT_OPTION);
    }
}


/*
 * String path = "src\\main\\java\\com\\mycompany\\app\\files\\test.json";
 * Person personTest = (Person) DefaultDataService.getWorkers().toArray()[0];
 * System.out.println(personTest.toString());
 * jsonService.writeJsonFile(personTest, path);
 * Person employeeTest = (Person) personTest.readJsonFile(path);
 * employeeTest.printInformation();
 */