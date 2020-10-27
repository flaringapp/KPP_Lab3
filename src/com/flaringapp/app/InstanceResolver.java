package com.flaringapp.app;

import com.flaringapp.data.interactor.ConsoleConsumer;
import com.flaringapp.data.interactor.ConsoleInteractor;
import com.flaringapp.data.interactor.ConsoleInteractorImpl;
import com.flaringapp.data.pathSearcher.PathSearcher;
import com.flaringapp.data.pathSearcher.UnixPathSearcherImpl;
import com.flaringapp.data.pathSearcher.WindowsPathSearcherImpl;
import com.flaringapp.data.storage.TextSourceModel;
import com.flaringapp.data.storage.TextSourceModelImpl;
import com.flaringapp.data.textProcessor.TextProcessor;
import com.flaringapp.data.textProcessor.TextProcessorImpl;

public class InstanceResolver {

    public static TextSourceModel resolveTextSourceModel() {
        return new TextSourceModelImpl();
    }

    public static TextProcessor resolveTextProcessor() {
        return new TextProcessorImpl();
    }

    public static PathSearcher resolvePathSearcher() {
        if (Constants.IS_UNIX) return new UnixPathSearcherImpl();
        else return new WindowsPathSearcherImpl();
    }

    public static ConsoleInteractor resolveConsoleInteractor(ConsoleConsumer consumer) {
        return new ConsoleInteractorImpl(consumer);
    }

}
