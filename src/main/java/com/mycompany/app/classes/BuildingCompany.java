package com.mycompany.app.classes;

import com.mycompany.app.classes.People.Client;
import com.mycompany.app.classes.People.Person;
import com.mycompany.app.classes.People.Worker;
import com.mycompany.app.classes.projects.types.Structure;
import com.mycompany.app.classes.services.*;
import com.mycompany.app.enums.TypeOfPerson;

import java.util.Arrays;

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
    DefaultDataService instanceSrv = new DefaultDataService();
    StructureService structureSrc = new StructureService();
    ProjectService projectSrv = new ProjectService();
    FileService fileSrv = new FileService();
    PersonService personSrv = new PersonService();

    public void startProgram() {
        instanceSrv.instantiateAll();
        do {
            menuSrv.printMenu("Menu", new String[]{"AMD", "Print Objects", "My profile", "Begin Project", "Manage project", "Read error reports", "Exit"});
            inputSrv.setIntAns(Arrays.asList(AMD_OPTION, PRINT_OBJECTS_OPTION, MY_PROFILE_OPTION, BEGIN_PROJECT_OPTION, MANAGE_PROJECT_OPTION, ERROR_REPORT_OPTION ,EXIT_OPTION));
            handleOptions();
        } while (inputSrv.getAns() != EXIT_OPTION);
    }

    private void handleOptions() {
        Structure structure = null;
        Person person = null;
        switch (inputSrv.getAns()) {
            case AMD_OPTION:
                do {
                menuSrv.printMenu("AMD",new String[]{"Person","Structure", "Exit"});
                inputSrv.setIntAns(Arrays.asList( PERSON_OPTION,  STRUCTURE_OPTION, EXIT_OPTION));
                    switch (inputSrv.getAns()){
                        case  PERSON_OPTION:
                            person = personSrv.createPerson();
                            if (person.getType() == TypeOfPerson.CLIENT)
                                DefaultDataService.getClients().add((Client) person);
                            else
                                DefaultDataService.getWorkers().add((Worker) person);

                            System.out.println(DefaultDataService.getWorkers().toArray()[DefaultDataService.getWorkers().size()-1]);
                            break;
                        case  STRUCTURE_OPTION:
                            //TODO AMD structures
                            break;
                    }
                }while (inputSrv.getAns() != EXIT_OPTION);
                break;
            case PRINT_OBJECTS_OPTION:
                do {
                    menuSrv.printMenu("Objects",new String[]{"Exit"});
                    //TODO
                    inputSrv.setIntAns(Arrays.asList(EXIT_OPTION));
                }while (inputSrv.getAns() != 0);
                break;
            case BEGIN_PROJECT_OPTION:
                    do {
                        menuSrv.printMenu("Projects", new String[]{"Begin project", "Exit"});
                        inputSrv.setIntAns(Arrays.asList(BEGIN_PROJECT_OPTION_SUBMENU ,EXIT_PROJECT_SUBMENU));
                        if (inputSrv.getAns() == 0){
                            structure = structureSrc.createStructure();
                            DefaultDataService.getStructures().add(structure);
                        }
                    }while (inputSrv.getAns() != EXIT_PROJECT_SUBMENU);
                break;
            case MANAGE_PROJECT_OPTION:
                projectSrv.createProject();
                break;
            case ERROR_REPORT_OPTION:
                fileSrv.readFile("logs/app.log");
                break;
        }
    }
}