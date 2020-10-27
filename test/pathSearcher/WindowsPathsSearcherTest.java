package pathSearcher;

import com.flaringapp.data.pathSearcher.PathSearcher;
import com.flaringapp.data.pathSearcher.WindowsPathSearcherImpl;
import javafx.util.Pair;

import java.util.*;

public class WindowsPathsSearcherTest extends PathSearcherTest {

    @Override
    protected PathSearcher providePathSearcher() {
        return new WindowsPathSearcherImpl();
    }

    @Override
    protected List<Pair<String, List<String>>> provideInputsToPathsData() {
        return Arrays.asList(
                new Pair<>(
                        "This is a path c:\\test.csa",
                        Collections.singletonList("c:\\test.csa")
                ),
                new Pair<>(
                        "And a multiline\n",
                        Collections.emptyList()
                ),
                new Pair<>(
                        "sentence with local path \\asd\\qs\\s",
                        Collections.singletonList("\\asd\\qs\\s")
                ),
                new Pair<>(
                        "And even such path ..\\test\\b.txt",
                        Collections.singletonList("..\\test\\b.txt")
                ),
                new Pair<>(
                        "And relative path asd\\asd\\a.txt",
                        Collections.singletonList("asd\\asd\\a.txt")
                )
        );
    }
}
