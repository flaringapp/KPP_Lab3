package com.flaringapp.data.textProcessor;

import com.flaringapp.app.InstanceResolver;
import com.flaringapp.data.models.Text;
import com.flaringapp.data.pathSearcher.PathSearcher;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class TextProcessorImpl implements TextProcessor {

    private static final Character DOT = '.';
    private static final Character QUOTATION_MARK = '?';
    private static final Character EXCLAMATION_MARK = '!';

    private static final String QUOTATION_MARK_S = QUOTATION_MARK.toString();

    private final List<Character> SentenceDelimiters = Arrays.asList(DOT, QUOTATION_MARK, EXCLAMATION_MARK);

    private static final String NEW_LINE = "\n";

    private final PathSearcher pathSearcher = InstanceResolver.resolvePathSearcher();

    @Override
    public List<String> splitTextToSentences(Text text) {
        List<String> sentences = new ArrayList<>();

        List<String> uncompletedSentenceParts = new ArrayList<>();

        text.getLines().forEach(line -> {
            int lastSentenceEndIndex = -1;

            for (int i = 0; i < line.length(); i++) {
                if (SentenceDelimiters.contains(line.charAt(i))) {
                    String sentenceBeginning = String.join("", uncompletedSentenceParts);
                    String sentenceEnding = line.substring(lastSentenceEndIndex + 1, i + 1);

                    sentences.add(sentenceBeginning + sentenceEnding);

                    lastSentenceEndIndex = i;
                    uncompletedSentenceParts.clear();
                }
            }

            if (lastSentenceEndIndex == -1) {
                uncompletedSentenceParts.add(line);
                uncompletedSentenceParts.add(NEW_LINE);
            } else if (lastSentenceEndIndex < line.length() - 1) {
                uncompletedSentenceParts.add(
                        line.substring(lastSentenceEndIndex + 1).trim()
                );
                uncompletedSentenceParts.add(NEW_LINE);
            }
        });

        return sentences;
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

    @Override
    public List<String> filterQuotationSentences(List<String> sentences) {
        return sentences.stream()
                .filter(sentence -> sentence.endsWith(QUOTATION_MARK_S))
                .collect(Collectors.toList());
    }

    @Override
    public List<String> findDistinctWordsWithLength(List<String> sentences, int length) {
        String regex = generateWordInSentenceLengthRegex(length);
        Pattern pattern = Pattern.compile(regex);
        return sentences.stream()
                .map(sentence -> findWordsWithLength(sentence, pattern))
                .flatMap(Collection::stream)
                .distinct()
                .collect(Collectors.toList());
    }

    private List<String> findWordsWithLength(String sentence, Pattern pattern) {
        List<String> words = new ArrayList<>();

        Matcher matcher = pattern.matcher(sentence);
        while (matcher.find()) {
            words.add(matcher.group());
        }

        return words;
    }

    private String generateWordInSentenceLengthRegex(int length) {
        return "(^[\\w\\d]{" + length + "}(?=\\W))|" +
                "((?<=\\W)[\\w\\d]{" + length + "}(?=\\W))|" +
                "((?<=\\W)[\\w\\d]{" + length + "})$";
    }
}
