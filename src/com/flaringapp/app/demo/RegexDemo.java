package com.flaringapp.app.demo;

import com.flaringapp.app.InstanceResolver;
import com.flaringapp.data.interactor.ConsoleConsumer;
import com.flaringapp.data.interactor.ConsoleInteractor;
import com.flaringapp.data.models.Text;
import com.flaringapp.data.textProcessor.TextProcessor;

import java.util.List;

import static com.flaringapp.app.utils.PrinterUtils.*;

public class RegexDemo implements Demo, ConsoleConsumer {

    private final ConsoleInteractor consoleInteractor = InstanceResolver.resolveConsoleInteractor(this);
    private final TextProcessor textProcessor = InstanceResolver.resolveTextProcessor();

    private String text = "";

    @Override
    public void run() {
        printLine("Regex demo. Please enter the text and words length to search for in all quotation sentences.");
        consoleInteractor.requestInputText();
    }

    @Override
    public void onTextEntered(String text) {
        this.text = text;
        consoleInteractor.requestInputWordsLength();
    }

    @Override
    public void onWordsLengthEntered(int length) {
        List<String> sentences = textProcessor.splitTextToSentences(new Text(text));

        printTitle("Entered text:");
        printLine(text);

        printTitle("Entered words length: " + length);

        List<String> quotationSentences = textProcessor.filterQuotationSentences(sentences);

        printTitle("Quotation sentences:");
        printList(quotationSentences, "Sentence");

        List<String> distinctWordsWithLength = textProcessor.findDistinctWordsWithLength(quotationSentences, length);

        printTitle("Distinct words with length:");
        printList(distinctWordsWithLength, "Word");
    }

    @Override
    public void onWordsLengthInvalidFormat() {
        printTitle("Invalid words length format! Please input correct value");
        consoleInteractor.requestInputWordsLength();
    }

}
