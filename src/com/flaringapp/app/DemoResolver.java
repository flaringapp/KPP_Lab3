package com.flaringapp.app;

import com.flaringapp.app.demo.Demo;
import com.flaringapp.app.demo.RegexDemo;
import com.flaringapp.app.demo.TextDemo;
import com.flaringapp.app.utils.Callable;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import static com.flaringapp.app.utils.PrinterUtils.printLine;

public class DemoResolver {

    private static final Map<String, Callable<Demo>> dependencies = new HashMap<String, Callable<Demo>>() {{
        put("1", TextDemo::new);
        put("2", RegexDemo::new);
    }};

    public static Demo resolveDemo() {
        try (Scanner scanner = new Scanner(System.in)) {
            String param;
            do {
                printSelectDemo();
                param = resolveCorrectDemoParam(scanner);
                if (param == null) {
                    printLine("\nEnter a correct number please!");
                    continue;
                }
                return dependencies.get(param).call();
            } while (true);
        }
    }

    private static void printSelectDemo() {
        printLine("Please select demo:");
        printLine("\t1. Text processing demo");
        printLine("\t2. Text regex demo");
    }

    private static String resolveCorrectDemoParam(Scanner scanner) {
        printLine("\nPlease enter the number of the demo");
        String param = scanner.nextLine();

        if (!dependencies.containsKey(param)) return null;
        return param;
    }

}
