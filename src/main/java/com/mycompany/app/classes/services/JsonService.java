package com.mycompany.app.classes.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mycompany.app.classes.People.Person;

import java.io.File;

public class JsonService <T extends Person>{

    ObjectMapper objectMapper = new ObjectMapper();
    public void writeJsonFile(T data, String path){
//        System.out.println(data.toString());
        data.printInformation();
        try {
            objectMapper.writeValue(new File(path), data);
        }catch (Exception e){
            e.getStackTrace();
        }
    }
}
