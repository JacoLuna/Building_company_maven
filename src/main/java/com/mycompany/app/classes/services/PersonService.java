package com.mycompany.app.classes.services;

import com.mycompany.app.enums.TypeOfPerson;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PersonService {

    InputService inputSrv = new InputService();
    MenuService menuSrv = new MenuService();
    public void createPerson(TypeOfPerson typeOfPerson){
        String name, lastName, country;
        LocalDate BDay;
        int personIndex;
        String[] typeOfPersonArray = Arrays.stream(TypeOfPerson.values()).map(Enum::name).toArray(String[]::new);

        name = inputSrv.stringAns("Name:");
        lastName = inputSrv.stringAns("lastName:");
        menuSrv.printMenu("Country: ", new String[]{"Argentina", "Brazil", "UU.SS", "Spain", "Portugal", "China", "India"});
        inputSrv.setIntAns(new ArrayList<>(){{add(0);add(1);add(2);add(3);add(4);add(5);add(6);add(7);}});
        switch (inputSrv.getAns()){
            case 0 -> country = "Argentina";
            case 1 -> country = "Brazil";
            case 2 -> country = "UU.SS";
            case 3 -> country = "Spain";
            case 4 -> country = "Portugal";
            case 5 -> country = "China";
            case 6 -> country = "India";
        }
        BDay = inputSrv.readValidDate();
        String prompt = "Type of person: ";
        menuSrv.printMenu(prompt, typeOfPersonArray, prompt.length() * 2);
        personIndex = inputSrv.setIntAns(createIndexList(typeOfPersonArray.length));
        switch (personIndex){
            case 0:
                break;
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
        }
}
    private List<Integer> createIndexList(int length) {
        List<Integer> indexList = new ArrayList<>();
        for (int i = 0; i < length; i++) {
            indexList.add(i);
        }
        return indexList;
    }
}
