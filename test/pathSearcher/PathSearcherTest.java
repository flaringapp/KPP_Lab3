package pathSearcher;

import com.flaringapp.data.pathSearcher.PathSearcher;
import javafx.util.Pair;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public abstract class PathSearcherTest {

    PathSearcher pathSearcher = providePathSearcher();

    protected abstract PathSearcher providePathSearcher();

    protected abstract List<Pair<String, List<String>>> provideInputsToPathsData();

    @Test
    public void testFindPathsInText() {

        List<Pair<String, List<String>>> data = provideInputsToPathsData();
        List<String> expectedPaths = data.stream()
                .map(Pair::getValue)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());

        List<String> actualPaths = data.stream()
                .map(Pair::getKey)
                .map(pathSearcher::findPathsInString)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());

        Assertions.assertEquals(expectedPaths, actualPaths);
    }
}
