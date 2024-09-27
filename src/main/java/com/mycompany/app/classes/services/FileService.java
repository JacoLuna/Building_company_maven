package com.mycompany.app.classes.services;

import com.mycompany.app.classes.People.Worker;
import org.apache.commons.io.FileUtils;

import java.io.*;
import java.nio.charset.Charset;

public class FileService {

    public static final String folder = "src\\main\\java\\com\\mycompany\\app\\files";
    public static String readFile(String path, boolean localFile){
        StringBuilder sb = new StringBuilder();
        String line;
        try (BufferedReader br = new BufferedReader(new FileReader(localFile?folder + "\\" + path : path))){
            while ((line = br.readLine()) != null) {
                sb.append(line).append("\n");
            }
        } catch (FileNotFoundException ex) {
            System.out.println("An error occurred." + ex.getMessage());
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return sb.toString();
    }
    public static void writeFile(String path, String content, boolean localFile){
        try {
            File file = new File(localFile?folder + "\\" + path : path);
            FileUtils.write(file, content, Charset.defaultCharset());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
