package com.mycompany.app.classes;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

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

}
