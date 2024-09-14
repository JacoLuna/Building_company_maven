package com.mycompany.app.classes.services;

import com.mycompany.app.classes.People.Worker;
import org.apache.commons.io.FileUtils;

import java.io.*;
import java.nio.charset.Charset;

public class FileService {

    public static String readFile(String path){
        StringBuilder sb = new StringBuilder();
        String line;
        try (BufferedReader br = new BufferedReader(new FileReader(path))){
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
        } catch (FileNotFoundException ex) {
            System.out.println("An error occurred." + ex.getMessage());
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return sb.toString();
    }
    public static void writeFile(String path, String content){
        try {
            File file = new File(path);
            FileUtils.write(file, content, Charset.defaultCharset());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
