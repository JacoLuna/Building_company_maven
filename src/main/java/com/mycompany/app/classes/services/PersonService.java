package com.mycompany.app.classes.services;

import com.mycompany.app.classes.People.*;
import com.mycompany.app.classes.Utils;
import com.mycompany.app.classes.interfaces.Disectable;
import com.mycompany.app.classes.interfaces.IHasFilter;
import com.mycompany.app.enums.Countries;
import com.mycompany.app.enums.Experience;
import com.mycompany.app.enums.TypeOfPerson;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PersonService implements IHasFilter {
    public Person createPerson(){
        String name, lastName, country, prompt;
        LocalDate BDay;
        int personIndex, countryIndex;
        String[] typeOfPersonArray = Arrays.stream(TypeOfPerson.values()).map(Enum::name).toArray(String[]::new);
        String[] countries = Arrays.stream(Countries.values()).map(Enum::name).toArray(String[]::new);

        name = InputService.stringAns("Name:");
        lastName = InputService.stringAns("lastName:");
        MenuService.printMenu("Country: ", countries);
        country = countries[InputService.setInput(Utils.createIndexList(countries.length),Integer.class)];
        BDay = InputService.readValidDate("Birthday");
        prompt = "Type of person: ";
        MenuService.printMenu(prompt, typeOfPersonArray, prompt.length() * 2);
        personIndex = InputService.setInput(Utils.createIndexList(typeOfPersonArray.length),Integer.class);
        return switch (personIndex) {
            case 0 -> createEmployee(name, lastName, Countries.valueOf(country), BDay, TypeOfPerson.valueOf(typeOfPersonArray[personIndex]));
            case 1 -> createClient(name, lastName, Countries.valueOf(country), BDay);
            default -> null;
        };
    }

    private Worker createEmployee(String name,String lastName,Countries country,LocalDate BDay, TypeOfPerson typeOfPerson) {
        String[] experience = Arrays.stream(Experience.values()).map(Enum::name).toArray(String[]::new);
        float salary;
        int experienceIndex;
        Worker worker;
        String prompt = "experience level: ";
        MenuService.printMenu(prompt, experience, prompt.length() * 2);
        experienceIndex = InputService.setInput(Utils.createIndexList(experience.length), Integer.class);
        salary = InputService.setInput("Salary: ", 0.0f, 100000000.0f, Float.class);
        worker = new Worker(name,lastName,country,BDay,salary,Experience.valueOf(experience[experienceIndex]));
        worker.printInformation();
        return worker;
    }
    private Client createClient(String name, String lastName, Countries country, LocalDate BDay){
        Client client;
        boolean isEnterprise = false;
        String prompt = "is the client and enterprise: ";
        MenuService.printMenu(prompt, new String[]{"yes","no"}, prompt.length()*2);
        if (InputService.setInput(new ArrayList<>(){{add(0);add(1);}},Integer.class) == 0){
            isEnterprise = true;
        }
        client = new Client(name,lastName,country,BDay,isEnterprise);
        client.printInformation();
        return client;
    }

    public void filterPeople() {
        Map<String , String> map = Utils.filterByAttribute(Person.class);
        filterPerson(map.get("Attribute"), map.get("Value"))
            .forEach(person -> {
                System.out.println("Information of " + person.name);
                person.printInformation();
            });
    }

    private  <T> T[] concatArrays(T[] array1, T[] array2) {
        return Stream.concat(Arrays.stream(array1), Arrays.stream(array2))
                .toArray(size -> (T[]) Array.newInstance(array1.getClass().getComponentType(), size));
    }


    public List<Person> filterPerson(String attribute, Object value){
        List<Person> people = Stream.concat(DataService.getWorkers().stream(), DataService.getClients().stream()).toList();
        return people.stream().filter( person -> {
                    String input = StringUtils.lowerCase(value.toString());
                    switch (attribute){
                        case "type" -> {
                            String type = StringUtils.lowerCase(TypeOfPerson.valueOf(person.getType().name()).toString());
                            return  type.equals(input);
                        }
                        case "id" -> {
                            return (person.getId()+"").equals(value);
                        }
                        case "name" -> {
                            String name = StringUtils.lowerCase(person.name);
                            return  name.equals(input);
                        }
                        case "lastName" -> {
                            return person.lastName.equals(value);
                        }
                        case "country" -> {
                            String country = StringUtils.lowerCase(Countries.valueOf(person.getCountry().name).toString());
                            return country.equals(input);
                        }
                        case "BDay" -> {
                            return person.getBDay().equals(value);
                        }
                        case "experience" ->{
                            if (person.getType() == TypeOfPerson.WORKER){
                                Worker worker = (Worker)person;
                                String experience = StringUtils.lowerCase( Experience.valueOf(worker.getExperience().name()).toString());
                                return experience.equals(input);
                            }else return false;
                        }
                        case "salary" ->{
                            if (person.getType() == TypeOfPerson.WORKER){
                                Worker worker = (Worker)person;
                                return worker.getSalary() == (float)value;
                            }else return false;
                        }
                        case "isEnterprise" ->{
                            if (person.getType() == TypeOfPerson.CLIENT){
                                Client client = (Client) person;
                                return switch ((String) value) {
                                    case "true", "1" -> client.isEnterprise();
                                    case "false", "0" -> !client.isEnterprise();
                                    default -> false;
                                };
                            }else
                                return false;
                        }
                        case "amountOfProjects" -> {
                            if (person.getType() == TypeOfPerson.CLIENT){
                                Client client = (Client) person;
                                return client.getAmountOfProjects() == (int) value;
                            }else return false;
                        }
                        default -> {
                            return false;
                        }
                    }
                }
        ).collect(Collectors.toList());
    }
}
