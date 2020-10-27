package com.flaringapp.app;

import com.flaringapp.data.pathSearcher.PathSearcher;
import com.flaringapp.data.pathSearcher.UnixPathSearcherImpl;
import com.flaringapp.data.pathSearcher.WindowsPathSearcherImpl;
import com.flaringapp.data.storage.TextSourceModel;
import com.flaringapp.data.storage.TextSourceModelImpl;
import com.flaringapp.data.textAnalyzer.TextAnalyzer;
import com.flaringapp.data.textAnalyzer.TextAnalyzerImpl;

public class InstanceResolver {

    public static TextSourceModel resolveTextSourceModel() {
        return new TextSourceModelImpl();
    }

    public static TextAnalyzer resolveTextAnalyzer() {
        return new TextAnalyzerImpl();
    }

    public static PathSearcher resolvePathSearcher() {
        if (Constants.IS_UNIX) return new UnixPathSearcherImpl();
        else return new WindowsPathSearcherImpl();
    }

}
