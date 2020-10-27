package com.flaringapp.data.interactor;

import java.util.Scanner;

import static com.flaringapp.app.utils.PrinterUtils.printLine;

public class ConsoleInteractorImpl implements ConsoleInteractor {

    private final ConsoleConsumer consumer;

    private final Scanner scanner = new Scanner(System.in);

    public ConsoleInteractorImpl(ConsoleConsumer consumer) {
        this.consumer = consumer;
    }

    @Override
    public void requestInputText() {
        printLine("Enter text: ");
        String text = scanner.nextLine();
        consumer.onTextEntered(text);
    }

    @Override
    public void requestInputWordsLength() {
        printLine("Enter words length to search: ");
        String text = scanner.nextLine();

        if (!text.chars().allMatch(Character::isDigit)) {
            consumer.onWordsLengthInvalidFormat();
            return;
        }

        int value;
        try {
            value = Integer.parseInt(text);
        } catch (Exception e) {
            consumer.onWordsLengthInvalidFormat();
            return;
        }
        consumer.onWordsLengthEntered(value);
    }
}
