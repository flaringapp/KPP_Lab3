import com.flaringapp.app.Constants;
import com.flaringapp.app.InstanceResolver;
import com.flaringapp.data.textProcessor.TextProcessor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public class TextProcessorPathsTest {

    public static final List<String> PATHS_WINDOWS_INPUT = Arrays.asList(
            "This is a path c:\\test.csa",
            "And a multiline\n",
            "sentence with local path \\asd\\qs\\s",
            "And even such path ..\\test\\b.txt",
            "And relative path asd\\asd\\a.txt"
    );

    private static final List<String> PATHS_WINDOWS_RESULT = Arrays.asList(
            "c:\\test.csa",
            "\\asd\\qs\\s",
            "..\\test\\b.txt",
            "asd\\asd\\a.txt"
    );

    TextProcessor processor = InstanceResolver.resolveTextProcessor();

    @BeforeAll
    static void init() {
        Constants.IS_UNIX = false;
    }

    @Test
    public void testFindAllPathsInSentences() {
        Constants.IS_UNIX = false;
        List<String> paths = processor.findAllPathsInSentences(PATHS_WINDOWS_INPUT);
        Assertions.assertEquals(paths, PATHS_WINDOWS_RESULT);
    }

}
