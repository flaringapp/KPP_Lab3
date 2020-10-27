package com.flaringapp.data.textProcessor;

import com.flaringapp.data.models.Text;

import java.util.List;

public interface TextProcessor {

    List<String> splitTextToSentences(Text text);

    // Task 1
    List<String> filterSingleLineSentences(List<String> sentences);

    List<String> findAllPathsInSentences(List<String> sentences);

    // Task 2
    List<String> filterQuotationSentences(List<String> sentences);

    List<String> findDistinctWordsWithLength(List<String> sentences, int length);

}
