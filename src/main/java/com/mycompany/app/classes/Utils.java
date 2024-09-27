package com.mycompany.app.classes;
import com.mycompany.app.classes.services.InputService;
import com.mycompany.app.classes.services.MenuService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.Field;
import java.util.*;

public class Utils {
    static public final Logger CONSOLE_ERROR = LogManager.getLogger("ConsoleErrorLogger");
    static public final Logger CONSOLE = LogManager.getLogger("ConsoleLogger");

    public static List<Integer> createIndexList(int length) {
        List<Integer> indexList = new ArrayList<>();
        for (int i = 0; i < length; i++) {
            indexList.add(i);
        }
        return indexList;
    }

    public static <T> Map<String, String> filterByAttribute(Class<T> clazz) {
        int attIndex;
        String answer, prompt;

        String[] attributesString = getFieldsNames(clazz);
        List<Integer> attributesIndex = getFieldsOrder(clazz);

        prompt = "Filter";
        MenuService.printMenu(prompt, attributesString, prompt.length()*2);
        attIndex = InputService.setInput(attributesIndex, Integer.class);
        prompt = "Insert the " + attributesString[attIndex];
        answer = InputService.stringAns(prompt);

        Map<String, String> map = new HashMap<String, String>();

        map.put("Attribute", attributesString[attIndex]);
        map.put("Value", answer);
        return map;
    }

    public static <T> String[] getFieldsNames(Class<T> clazz){
        ArrayList<Field> atts = new ArrayList<>();
        atts.addAll(Arrays.asList(clazz.getDeclaredFields()));

        List<String> attributes = new ArrayList<>();

        for (Field field : atts) {
            attributes.add(field.getName());
        }
        String[] attributesString = new String[attributes.size()];

        for (int i = 0; i < attributes.size(); i++) {
            attributesString[i] = attributes.get(i);
        }
        return attributesString;
    }

    public static <T> List<Integer> getFieldsOrder(Class<T> clazz){
        ArrayList<Field> atts = new ArrayList<>();

        atts.addAll(Arrays.asList(clazz.getDeclaredFields()));

        List<Integer> attributesIndex = new ArrayList<>();

        for (int i = 0; i < atts.size(); i++) {
            attributesIndex.add(i);
        }
        return attributesIndex;
    }
}
