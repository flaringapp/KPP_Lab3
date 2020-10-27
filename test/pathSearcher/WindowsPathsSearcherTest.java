package pathSearcher;

import com.flaringapp.data.pathSearcher.PathSearcher;
import com.flaringapp.data.pathSearcher.WindowsPathSearcherImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

public class WindowsPathsSearcherTest {

    private static final Map<String, List<String>> INPUTS_TO_PATHS = new HashMap<String, List<String>>() {{
        put(
                "This is a path c:\\test.csa",
                Collections.singletonList("c:\\test.csa")
        );
        put(
                "And a multiline\n",
                Collections.emptyList()
        );
        put(
                "sentence with local path \\asd\\qs\\s",
                Collections.singletonList("\\asd\\qs\\s")
        );
        put(
                "And even such path ..\\test\\b.txt",
                Collections.singletonList("..\\test\\b.txt")
        );
        put(
                "And relative path asd\\asd\\a.txt",
                Collections.singletonList("asd\\asd\\a.txt")
        );
    }};

    private final PathSearcher pathSearcher = new WindowsPathSearcherImpl();

    @Test
    public void testFindPathsInText() {
        INPUTS_TO_PATHS.keySet().forEach(this::testFindPathInString);
    }

    private void testFindPathInString(String string) {
        List<String> paths = pathSearcher.findPathsInString(string);
        List<String> expectedPaths = INPUTS_TO_PATHS.get(string);
        Assertions.assertEquals(expectedPaths, paths);
    }

}
