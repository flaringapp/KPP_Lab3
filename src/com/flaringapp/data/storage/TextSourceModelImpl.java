package com.flaringapp.data.storage;

import com.flaringapp.app.Constants;
import com.flaringapp.data.models.Text;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TextSourceModelImpl implements TextSourceModel {

    @Override
    public Text readText(String fileName) throws FileNotFoundException {
        File file = new File(Constants.INPUT_FILES_DIR, fileName);

        List<String> lines = new ArrayList<>();

        Scanner scanner = new Scanner(file);

        while (scanner.hasNext()) {
            lines.add(scanner.nextLine());
        }

        scanner.close();

        return new Text(lines);
    }
}
