package com.flaringapp.data.models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Text {

    private final List<String> lines;

    public Text() {
        this.lines = new ArrayList<>();
    }

    public Text(List<String> lines) {
        this.lines = lines;
    }

    public Text(String line) {
        this(Collections.singletonList(line));
    }

    public List<String> getLines() {
        return lines;
    }
}
