package com.flaringapp.data.textAnalyzer;

import com.flaringapp.data.models.Text;

import java.util.List;

public interface TextAnalyzer {

    List<String> splitTextToSentences(Text text);

    List<String> filterSingleLineSentences(List<String> sentences);

    List<String> findAllPathsInSentences(List<String> sentences);

}
