package com.mycompany.app.classes.services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;
import static com.diogonunes.jcolor.Attribute.BRIGHT_MAGENTA_BACK;
import static com.diogonunes.jcolor.Attribute.BRIGHT_WHITE_BACK;
import static com.diogonunes.jcolor.Attribute.BLACK_TEXT;
import static com.diogonunes.jcolor.Ansi.colorize;

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
                System.out.print(colorize(word , BLACK_TEXT(), BRIGHT_MAGENTA_BACK()));
            } else {
                if (i == middleSection - 1 || i == 0) {
                    if (i == middleSection - 1){
                        for (int j = 0; j < 3; j++) {
                            if (j != 2)
                                System.out.print(colorize(" " , BLACK_TEXT(), BRIGHT_MAGENTA_BACK()));
                            else
                                System.out.print(("|\n"));
                        }
                    }else {
                        System.out.print(("|"));
                    }
                } else {
                    System.out.print(colorize(" " , BLACK_TEXT(), BRIGHT_MAGENTA_BACK()));
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
