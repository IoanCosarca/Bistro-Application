package com.ntt.bistroapplication.console;

import java.util.Scanner;

/**
 * Console class for printing non-specific messages.
 */
public class MainConsole {
    public static void startExecution()
    {
        Scanner scanner = new Scanner(System.in);
        printMessage("The tables have been created and initialized!");
        String str = "";
        while (!str.equals("Begin"))
        {
            printMessage("Type \"Begin\" to start!");
            str = scanner.next();
        }
    }

    /**
     * The printing method that takes a String as parameter.
     * @param message the message to be printed on the console
     */
    public static void printMessage(String message) {
        System.out.println(message);
    }
}
