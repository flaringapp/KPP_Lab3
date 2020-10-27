package com.flaringapp.app;

import com.flaringapp.data.models.Text;
import com.flaringapp.data.storage.TextSourceModel;
import com.flaringapp.data.textAnalyzer.TextAnalyzer;

import java.io.FileNotFoundException;
import java.util.List;

public class TextDemo {

    private final TextSourceModel sourceModel = InstanceResolver.resolveTextSourceModel();
    private final TextAnalyzer textAnalyzer = InstanceResolver.resolveTextAnalyzer();

    void run() {
        Text text = loadText(Constants.INPUT_FILE_NAME);

        printTitle("Initial text: ");
        text.getLines().forEach(this::printLine);

        printTitle("Sentences: ");
        List<String> sentences = textAnalyzer.splitTextToSentences(text);
        printSentences(sentences);

        printTitle("Filtered single-line sentences: ");
        List<String> singleLineSentences = textAnalyzer.filterSingleLineSentences(sentences);
        printSentences(singleLineSentences);

        Text pathsText = loadText(Constants.systemTypedPathsDemoFile());

        printTitle("Text with paths: ");
        pathsText.getLines().forEach(this::printLine);

        printTitle("Paths in text: ");
        List<String> paths = textAnalyzer.findAllPathsInSentences(pathsText.getLines());
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
        for (int i = 0; i < sentences.size(); i++) {
            printLine("Sentence " + (i + 1));
            printLine(sentences.get(i));
        }
    }

    private void printPaths(List<String> paths) {
        for (int i = 0; i < paths.size(); i++) {
            printLine("Path " + (i + 1));
            printLine(paths.get(i));
        }
    }

    private void printTitle(String line) {
        System.out.println("\n" + line + "\n");
    }

    private void printLine(String line) {
        System.out.println(line);
    }

    private void newLine() {
        System.out.println();
    }

    private void exit() {
        System.exit(0);
    }

}
