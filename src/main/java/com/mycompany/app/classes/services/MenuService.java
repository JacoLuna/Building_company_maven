package com.mycompany.app.classes.services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;

public class MenuService {
    public static final Logger CONSOLE_ERROR = LogManager.getLogger("ConsoleErrorLogger");
    public static final Logger CONSOLE = LogManager.getLogger("ConsoleLogger");
    InputService inputSrv = new InputService();
    Scanner keyboard = new Scanner(System.in);

    public void printFrame(String word, int frame) {
        int middleSection = frame - word.length() - 1;
        printLine(frame);
        printMiddleSection(word, middleSection);
        printLine(frame);
    }
    public void printFrame(String word) {
        printFrame(word, 30);
    }
    private void printLine(int frame) {
        for (int i = 0; i < frame; i++) {
            System.out.print("-" + ((i == frame - 1) ? "\n" : ""));
        }
    }
    private void printMiddleSection(String word, int middleSection) {
        for (int i = 0; i < middleSection; i++) {
            if (i == (middleSection / 2)) {
                System.out.print(word);
            } else {
                if (i == middleSection - 1 || i == 0) {
                    System.out.print(((i == middleSection - 1) ? "  |\n" : "|"));
                } else {
                    System.out.print(" ");
                }
            }
        }
    }
    public void printMenu(String title, String[] ans) {
        printFrame(title);
        CONSOLE.info(buildMenuString(ans));
    }
    public void printMenu(String title, String[] ans, int frame) {
        printFrame(title, frame);
        CONSOLE.info(buildMenuString(ans));
    }
    public void printMenu(String[] ans) {
        CONSOLE.info(buildMenuString(ans));
    }
    private String buildMenuString(String[] ans) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < ans.length; i++) {
            sb.append("-").append(i).append(" ").append(ans[i]).append("\n");
        }
        return sb.toString();
    }
}
