package textProcessor;

import com.flaringapp.app.InstanceResolver;
import com.flaringapp.data.models.Text;
import com.flaringapp.data.pathSearcher.PathSearcher;
import com.flaringapp.data.textProcessor.TextProcessor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class TextProcessorTest {

    private static final List<String> INPUT = Arrays.asList(
            "This is a sentence. This is a 2nd sentence! And question sentence?",
            "And let's test some",
            "multiline.",
            "Some more multiline",
            "question sentence?"
    );

    private static final List<String> SPLIT_RESULT = Arrays.asList(
            "This is a sentence.",
            "This is a 2nd sentence!",
            "And question sentence?",
            "And let's test some\nmultiline.",
            "Some more multiline\nquestion sentence?"
    );

    private static final List<String> SINGLE_LINE_RESULT = Arrays.asList(
            "This is a sentence.",
            "This is a 2nd sentence!",
            "And question sentence?"
    );

    private static final List<String> QUOTATION_SENTENCES_RESULT = Arrays.asList(
            "And question sentence?",
            "Some more multiline\nquestion sentence?"
    );

    private static final int DISTINCT_WORDS_LENGTH_INPUT = 8;
    private static final List<String> DISTINCT_WORDS_RESULT = Arrays.asList(
            "sentence",
            "question"
    );

    TextProcessor processor = InstanceResolver.resolveTextProcessor();
    PathSearcher pathSearcher = InstanceResolver.resolvePathSearcher();

    @Test
    public void testSplitTextToSentences() {
        List<String> sentences = processor.splitTextToSentences(new Text(INPUT));
        Assertions.assertEquals(sentences, SPLIT_RESULT);
    }

    @Test
    public void testFilterSingleListSentences() {
        List<String> sentences = processor.splitTextToSentences(new Text(INPUT));
        List<String> singleLineSentences = processor.filterSingleLineSentences(sentences);
        Assertions.assertEquals(singleLineSentences, SINGLE_LINE_RESULT);
    }

    @Test
    public void testFindPathsInSentences() {
        List<String> sentences = processor.splitTextToSentences(new Text(INPUT));
        List<String> words = processor.findDistinctWordsWithLength(sentences, DISTINCT_WORDS_LENGTH_INPUT);

        List<String> expectedWords = sentences.stream()
                .map(sentence -> pathSearcher.findPathsInString(sentence))
                .flatMap(Collection::stream)
                .collect(Collectors.toList());

        Assertions.assertEquals(words, expectedWords);
    }

    @Test
    public void testFilterQuotationSentences() {
        List<String> sentences = processor.splitTextToSentences(new Text(INPUT));
        List<String> singleLineSentences = processor.filterQuotationSentences(sentences);
        Assertions.assertEquals(singleLineSentences, QUOTATION_SENTENCES_RESULT);
    }

    @Test
    public void testFindDistinctWordsByLength() {
        List<String> sentences = processor.splitTextToSentences(new Text(INPUT));
        List<String> words = processor.findDistinctWordsWithLength(sentences, DISTINCT_WORDS_LENGTH_INPUT);
        Assertions.assertEquals(words, DISTINCT_WORDS_RESULT);
    }

}
