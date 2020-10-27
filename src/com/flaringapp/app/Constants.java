package com.flaringapp.app;

public class Constants {

    public static boolean IS_UNIX = true;

    public static final String INPUT_FILES_DIR = "input";

    public static final String INPUT_FILE_NAME = "text.txt";
    public static final String UNIX_PATHS_FILE_NAME = "text_unix_paths.txt";
    public static final String WINDOWS_PATHS_FILE_NAME = "test_windows_paths.txt";

    public static final String SPACE = " ";
    public static final String NEW_LINE = "\n";

    public static String systemTypedPathsDemoFile() {
        if (IS_UNIX) return UNIX_PATHS_FILE_NAME;
        else return WINDOWS_PATHS_FILE_NAME;
    }

}
