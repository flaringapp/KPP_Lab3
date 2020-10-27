package com.flaringapp.data.pathSearcher;

import java.util.ArrayList;
import java.util.List;

import static com.flaringapp.app.Constants.SPACE;

public class UnixPathSearcherImpl extends PathSearcherImpl {

    private static final String FILE_SEPARATOR = "/";
    private static final String HOME_DIRECtORY_PREFIX = "~/";

    @Override
    public List<String> findPathsInString(String string) {
        List<String> paths = new ArrayList<>();
        String[] words = string.split(SPACE);

        for (String word : words) {
            if (word.startsWith(FILE_SEPARATOR) || word.startsWith(HOME_DIRECtORY_PREFIX)) {
                paths.add(word);
            }
        }

        return paths;
    }

}
