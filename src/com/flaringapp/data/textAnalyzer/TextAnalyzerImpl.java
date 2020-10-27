package com.flaringapp.data.textAnalyzer;

import com.flaringapp.app.InstanceResolver;
import com.flaringapp.data.models.Text;
import com.flaringapp.data.pathSearcher.PathSearcher;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class TextAnalyzerImpl implements TextAnalyzer {

    private static final String DOT = "\\.";
    private static final String NEW_LINE = "\n";

    private final PathSearcher pathSearcher = InstanceResolver.resolvePathSearcher();

    @Override
    public List<String> splitTextToSentences(Text text) {
        String[] sentences = String.join("\n", text.getLines())
                .split(DOT);
        return Arrays.asList(sentences);
    }

    @Override
    public List<String> filterSingleLineSentences(List<String> sentences) {
        return sentences.stream()
                .filter(sentence -> !sentence.contains(NEW_LINE))
                .collect(Collectors.toList());
    }

    @Override
    public List<String> findAllPathsInSentences(List<String> sentences) {
        return sentences.stream()
                .map(pathSearcher::findPathsInString)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }
}
