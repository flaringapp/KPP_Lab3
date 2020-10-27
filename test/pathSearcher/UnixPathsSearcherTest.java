package pathSearcher;

import com.flaringapp.data.pathSearcher.PathSearcher;
import com.flaringapp.data.pathSearcher.UnixPathSearcherImpl;
import javafx.util.Pair;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class UnixPathsSearcherTest extends PathSearcherTest {

    @Override
    protected PathSearcher providePathSearcher() {
        return new UnixPathSearcherImpl();
    }

    @Override
    protected List<Pair<String, List<String>>> provideInputsToPathsData() {
        return Arrays.asList(
                new Pair<>(
                        "This is /Some/path/to/file.txt",
                        Collections.singletonList("/Some/path/to/file.txt")
                ),
                new Pair<>(
                        "sentence with local path ~/asd/qs/s",
                        Collections.singletonList("~/asd/qs/s")
                )
        );
    }
}
