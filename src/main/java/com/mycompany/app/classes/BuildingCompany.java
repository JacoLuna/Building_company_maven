package com.mycompany.app.classes;

import com.mycompany.app.classes.People.*;
import com.mycompany.app.classes.projects.types.Structure;
import com.mycompany.app.classes.services.*;
import com.mycompany.app.enums.Countries;
import com.mycompany.app.enums.Experience;
import com.mycompany.app.enums.TypeOfPerson;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class BuildingCompany {
    private static final int AMD_OPTION = 0;
    private static final int PRINT_OBJECTS_OPTION = 1;
    private static final int MY_PROFILE_OPTION = 2;
    private static final int BEGIN_PROJECT_OPTION = 3;
    private static final int MANAGE_PROJECT_OPTION = 4;
    private static final int ERROR_REPORT_OPTION = 5;
    private static final int EXIT_OPTION = 6;

    private static final int  PERSON_OPTION = 0;
    private static final int  STRUCTURE_OPTION = 1;
    private static final int  INFORMATION_OPTION = 0;
    private static final int  PROJECT_OPTION = 1;

    //BEGIN_PROJECT_OPTION submenu
    private static final int BEGIN_PROJECT_OPTION_SUBMENU = 0;
    private static final int EXIT_PROJECT_SUBMENU = 1;

    MenuService menuSrv = new MenuService();
    InputService inputSrv = new InputService();
    DataService instanceSrv = new DataService();
    StructureService structureSrc = new StructureService();
    ProjectService projectSrv = new ProjectService();
    FileService fileSrv = new FileService();
    PersonService personSrv = new PersonService();
    JsonService<Person> jsonService = new JsonService<>();

    Map<Integer, Runnable> menuActions = new HashMap<>();
    public void startProgram() {
        instanceSrv.instantiateAll();

        menuActions.put(AMD_OPTION, () -> handleAMDOption());
        menuActions.put(PRINT_OBJECTS_OPTION, () -> handlePrintObjectsOption());
        menuActions.put(BEGIN_PROJECT_OPTION, () -> handlePrintObjectsOption());
        menuActions.put(MANAGE_PROJECT_OPTION, () -> projectSrv.createProject());
        menuActions.put(ERROR_REPORT_OPTION, () -> fileSrv.readFile("logs/app.log"));

        List<Person> list = instanceSrv.filterPerson("name", "jaco");
        for (Person value: list){
            value.printInformation();
        }
        do {
            menuSrv.printMenu("Menu", new String[]{"AMD", "Print Objects", "My profile", "Begin Project", "Manage project", "Read error reports", "Exit"});
            inputSrv.setIntAns(Arrays.asList(AMD_OPTION, PRINT_OBJECTS_OPTION, MY_PROFILE_OPTION, BEGIN_PROJECT_OPTION, MANAGE_PROJECT_OPTION, ERROR_REPORT_OPTION ,EXIT_OPTION));
            menuActions.get(inputSrv.getAns()).run();
//            menuActions.getOrDefault(inputSrv.getAns(), () -> System.out.println("Invalid option")).run();
        } while (inputSrv.getAns() != EXIT_OPTION);
    }

    private void handleAMDOption(){
        Person person = null;
        do {
            menuSrv.printMenu("AMD",new String[]{"Person","Structure", "Exit"});
            inputSrv.setIntAns(Arrays.asList( PERSON_OPTION,  STRUCTURE_OPTION, EXIT_OPTION));
            switch (inputSrv.getAns()){
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
        }while (inputSrv.getAns() != EXIT_OPTION);
    }

    private void handlePrintObjectsOption(){
        do {
            menuSrv.printMenu("Objects",new String[]{"Exit"});
            //TODO
            inputSrv.setIntAns(Arrays.asList(EXIT_OPTION));
        }while (inputSrv.getAns() != 0);
    }

    private void handleProjectoptions(){
        Structure structure = null;
        do {
            menuSrv.printMenu("Projects", new String[]{"Begin project", "Exit"});
            inputSrv.setIntAns(Arrays.asList(BEGIN_PROJECT_OPTION_SUBMENU ,EXIT_PROJECT_SUBMENU));
            if (inputSrv.getAns() == 0){
                structure = structureSrc.createStructure();
                DataService.getStructures().add(structure);
            }
        }while (inputSrv.getAns() != EXIT_PROJECT_SUBMENU);
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