package com.flaringapp.app;

import java.util.Scanner;

public final class ScannerWrapper {

    private static Scanner scanner;

    public static Scanner scanner() {
        if (scanner == null) scanner = new Scanner(System.in);
        return scanner;
    }

}
