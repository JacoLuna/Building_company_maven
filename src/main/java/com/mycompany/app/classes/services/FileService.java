package com.mycompany.app.classes.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mycompany.app.classes.People.Worker;
import com.mycompany.app.classes.interfaces.JsonStorable;

import java.io.*;

public class FileService {

    public void readFile(String path){
        try (BufferedReader br = new BufferedReader(new FileReader(path))){
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (FileNotFoundException ex) {
            System.out.println("An error occurred." + ex.getMessage());
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    public void writeFile(String path, String content){
        try {
            FileWriter file = new FileWriter(path);
            file.write(content);
            file.flush();
            file.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
