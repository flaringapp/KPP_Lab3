package com.flaringapp.app.demo;

import com.flaringapp.app.Constants;
import com.flaringapp.app.InstanceResolver;
import com.flaringapp.app.utils.PrinterUtils;
import com.flaringapp.data.models.Text;
import com.flaringapp.data.storage.TextSourceModel;
import com.flaringapp.data.textProcessor.TextProcessor;

import java.io.FileNotFoundException;
import java.util.List;

import static com.flaringapp.app.utils.PrinterUtils.*;

public class TextDemo implements Demo {

    private final TextSourceModel sourceModel = InstanceResolver.resolveTextSourceModel();
    private final TextProcessor textProcessor = InstanceResolver.resolveTextProcessor();

    @Override
    public void run() {
        Text text = loadText(Constants.INPUT_FILE_NAME);

        printTitle("Initial text: ");
        text.getLines().forEach(PrinterUtils::printLine);

        printTitle("Sentences: ");
        List<String> sentences = textProcessor.splitTextToSentences(text);
        printSentences(sentences);

        printTitle("Filtered single-line sentences: ");
        List<String> singleLineSentences = textProcessor.filterSingleLineSentences(sentences);
        printSentences(singleLineSentences);

        Text pathsText = loadText(Constants.systemTypedPathsDemoFile());

        printTitle("Text with paths: ");
        pathsText.getLines().forEach(PrinterUtils::printLine);

        printTitle("Paths in text: ");
        List<String> paths = textProcessor.findAllPathsInSentences(pathsText.getLines());
        printPaths(paths);
    }

    private Text loadText(String path) {
        Text text = new Text();
        try {
            text = sourceModel.readText(path);
        } catch (FileNotFoundException e) {
            printLine(e.getMessage());
            exit();
        }
        return text;
    }

    private void printSentences(List<String> sentences) {
        printList(sentences, "Sentence");
    }

    private void printPaths(List<String> paths) {
        printList(paths, "Path");
    }

    private void exit() {
        System.exit(0);
    }

}
