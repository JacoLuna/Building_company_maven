package com.mycompany.app.classes.services;

import com.mycompany.app.classes.Exceptions.MenuException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class InputService {
    private static final Logger LOGGER = LogManager.getLogger(Logger.class);
    private static final Logger FILE = LogManager.getLogger("FileOnlyLogger");
    private static final Logger CONSOLE_ERROR = LogManager.getLogger("ConsoleErrorLogger");
    private static final Logger CONSOLE = LogManager.getLogger("ConsoleLogger");
    public Scanner keyboard = new Scanner(System.in);
    private int intAns;
    private float floatAns;
    private long longAns;

    public int getAns(){
        return intAns;
    }
    public int setIntAns(String prompt, List<Integer> ansArray) {
        boolean isValid = false;
        do {
            try {
                    System.out.print(prompt);
                    intAns = keyboard.nextInt();
                    if (!ansArray.contains(intAns)){
                        throw new MenuException("ERROR Option not available");
                    }
                    isValid = true;
            }catch (InputMismatchException | MenuException e){
                CONSOLE_ERROR.error(e);
                keyboard.nextLine();
            }catch (Exception e){
                  LOGGER.error(e);
                  return -1;
            }
        } while (!isValid);
        return intAns;
    }
    public int setIntAns(List<Integer> ansArray) {
        boolean isValid = false;
        do {
            try {
                intAns = keyboard.nextInt();
                if (!ansArray.contains(intAns)){
                    throw new MenuException("ERROR Option not available");
                }
                isValid = true;
            }catch (InputMismatchException | MenuException e){
                CONSOLE_ERROR.error(e);
                keyboard.nextLine();
            } catch (Exception e){
                LOGGER.error("Unexpected error " + e);
            }
        }while (!isValid);
        return intAns;
    }
    public int setIntAns(String prompt, int minValue, int maxValue) {
        boolean isValid = false;
        do {
            CONSOLE.info(prompt);
            try {
                    intAns = keyboard.nextInt();
                    if (intAns < minValue || intAns > maxValue ){
                        throw new MenuException("ERROR Option not available");
                    }
                    isValid = true;
            }catch (InputMismatchException | MenuException e){
                CONSOLE_ERROR.error(e);
                keyboard.nextLine();
            }catch (Exception e){
                LOGGER.error("Unexpected error " + e);
            }
        } while (!isValid);
        return intAns;
    }
    public int setIntAns(int minValue, int maxValue) {
        boolean isValid = false;
        do {
            try {
                intAns = keyboard.nextInt();
                if (intAns < minValue || intAns > maxValue ){
                    throw new MenuException("ERROR Option not available");
                }
                isValid = true;
            }catch (InputMismatchException | MenuException e){
                CONSOLE_ERROR.error(e);
                keyboard.nextLine();
            }catch (Exception e){
                LOGGER.error("Unexpected error " + e);
            }
            }while (!isValid);
        return intAns;
    }

    public float setFloatAns(String prompt, List<Float> ansArray) {
        try {
            do {
                CONSOLE.info(prompt);
                floatAns = keyboard.nextFloat();
                if (!ansArray.contains(floatAns)) {
                    System.out.println("ERROR Option not available");
                    floatAns = -1;
                }
            } while (intAns == -1);
        } catch (Exception e) {
            LOGGER.error(e);
        }
        return floatAns;
    }
    public float setFloatAns(List<Float> ansArray) {
        try {
            floatAns = keyboard.nextFloat();
            if (!ansArray.contains(floatAns)) {
                floatAns = -1;
                System.out.println("ERROR Option not available");
            }
        } catch (Exception e) {
            LOGGER.error(e);
        }
        return floatAns;
    }
    public float setFloatAns(String prompt, float minValue, float maxValue) {
        boolean isValid = false;
        do {
            try {
                CONSOLE.info(prompt);
                floatAns = keyboard.nextFloat();
                if (floatAns < minValue || floatAns > maxValue) {
                    throw new MenuException("ERROR Option not available");
                }
                isValid = true;
            }catch (InputMismatchException | MenuException e){
                LOGGER.error(e);
                keyboard.nextLine();
            }catch (Exception e){
                LOGGER.error("Unexpected error " + e);
            }
        }while (!isValid);
        return floatAns;
    }
    public float setFloatAns(float minValue, float maxValue) {
        boolean isValid = false;
        do {
            try {
                floatAns = keyboard.nextFloat();
                if (floatAns < minValue || floatAns > maxValue) {
                    throw new MenuException("ERROR Option not available");
                }
                isValid = true;
            }catch (InputMismatchException | MenuException e){
                LOGGER.error(e);
                keyboard.nextLine();
            }catch (Exception e){
                LOGGER.error("Unexpected error " + e);
            }
        }while (!isValid);
        return floatAns;
    }

    public long setLongAns(List<Long> ansArray) {
        try {
            longAns = keyboard.nextLong();
            if (!ansArray.contains(longAns)) {
                System.out.println("ERROR Option not available");
                longAns = -1;
            }
        } catch (Exception e) {
                LOGGER.error(e);
        }
        return longAns;
    }
    public long setLongAns(long minValue, long maxValue) {
        try {
            longAns = keyboard.nextLong();
            if (longAns < minValue && longAns > maxValue) {
                System.out.println("ERROR Option not available");
                longAns = -1;
            }
        } catch (Exception e) {
                LOGGER.error(e);
        }
        return longAns;
    }

    public String stringAns(String prompt){
        System.out.println(prompt);
        return keyboard.nextLine();
    }
    public LocalDate readValidDate(String prompt) {
        System.out.println(prompt);
        return readValidDate();
    }
    public LocalDate readValidDate() {
        int year, month, day;
        boolean validDate = false;
        LocalDate date = null;

        while (!validDate) {
            try {
                year = setIntAns("Enter year: ", LocalDate.MIN.getYear(), LocalDate.MAX.getYear());
                month = setIntAns("Enter month (1-12): ", 1, 12);
                day = setIntAns("Enter day: ", 1, 31);
                date = LocalDate.of(year, month, day);
                validDate = true;
            } catch (DateTimeException | IllegalArgumentException e) {
                System.out.println("Invalid date. Please try again.");
            }
        }
        return date;
    }
}
