package com.flaringapp.app.utils;

import java.util.List;

public class PrinterUtils {

    public static void printList(List<String> list, String name) {
        for (int i = 0; i < list.size(); i++) {
            printLine(name + " " + (i + 1));
            printLine(list.get(i));
        }
    }

    public static void printTitle(String line) {
        System.out.println("\n" + line + "\n");
    }

    public static void printLine(String line) {
        System.out.println(line);
    }

    public static void newLine() {
        System.out.println();
    }

}
