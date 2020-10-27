package com.flaringapp.data.pathSearcher;

import java.util.*;

import static com.flaringapp.app.Constants.NEW_LINE;
import static com.flaringapp.app.Constants.SPACE;

/**
 * @see <a href="https://docs.microsoft.com/ru-ru/dotnet/standard/io/file-path-formats</a>
 */
public class WindowsPathSearcherImpl extends PathSearcherImpl {

    private static final String FILE_SEPARATOR = "\\";
    private static final String DISK_NAME_SEPARATOR = ":\\";
    private static final String RELATIVE_PATH_BEGINNING = "..\\";

    @Override
    public List<String> findPathsInString(String string) {
        List<String> paths = new ArrayList<>();

        String[] lines = string.split(NEW_LINE);
        Arrays.stream(lines)
                .forEach(line -> paths.addAll(findPathsInLine(line)));

        return paths;
    }

    private List<String> findPathsInLine(String line) {
        List<String> paths = new ArrayList<>();
        List<String> currentPathParts = new ArrayList<>();
        List<String> currentUnconfirmedPathParts = new ArrayList<>();

        String[] words = line.split(SPACE);

        for (String word : words) {
            if (word.equals(FILE_SEPARATOR)) {
                processAddToPaths(paths, currentPathParts, currentUnconfirmedPathParts);
                continue;
            }

            int diskPostfixIndex = word.indexOf(DISK_NAME_SEPARATOR);
            if (diskPostfixIndex != -1) {
                if (checkIfWordBeginningIsValid(word, diskPostfixIndex)) {
                    processAddToPaths(paths, currentPathParts, currentUnconfirmedPathParts);
                    currentPathParts.add(word);
                }
                continue;
            }

            if (word.startsWith(FILE_SEPARATOR)) {
                processAddToPaths(paths, currentPathParts, currentUnconfirmedPathParts);
                currentPathParts.add(word);
                continue;
            }

            if (word.startsWith(RELATIVE_PATH_BEGINNING)) {
                processAddToPaths(paths, currentPathParts, currentUnconfirmedPathParts);
                currentPathParts.add(word);
                continue;
            }

            if (word.endsWith(FILE_SEPARATOR)) {
                processAddToPathsParts(currentPathParts, currentUnconfirmedPathParts);
                currentPathParts.add(word);
                processAddToPaths(paths, currentPathParts, currentUnconfirmedPathParts);
                continue;
            }

            if (word.contains(FILE_SEPARATOR)) {
                processAddToPathsParts(currentPathParts, currentUnconfirmedPathParts);
                currentPathParts.add(word);
                continue;
            }

            if (!currentPathParts.isEmpty()) {
                currentUnconfirmedPathParts.add(word);
            }
        }

        if (!currentPathParts.isEmpty()) {
            paths.add(String.join(SPACE, currentPathParts));
        }

        return paths;
    }

    private boolean checkIfWordBeginningIsValid(String word, int diskPostfixIndex) {
        boolean isValid = true;
        for (int i = 0; i < diskPostfixIndex; i++) {
            if (!Character.isLetter(word.charAt(i))) {
                isValid = false;
                break;
            }
        }
        return isValid;
    }

    private void processAddToPaths(
            List<String> paths,
            List<String> currentPathParts,
            List<String> currentUnconfirmedPathParts
    ) {
        if (!currentPathParts.isEmpty()) {
            paths.add(String.join(SPACE, currentPathParts));
            currentPathParts.clear();
        }
        currentUnconfirmedPathParts.clear();
    }

    private void processAddToPathsParts(
            List<String> currentPathParts,
            List<String> currentUnconfirmedPathParts
    ) {
        currentPathParts.addAll(currentUnconfirmedPathParts);
        currentUnconfirmedPathParts.clear();
    }

}
