package com.flaringapp.data.interactor;

public interface ConsoleConsumer {

    void onTextEntered(String text);

    void onWordsLengthEntered(int length);
    void onWordsLengthInvalidFormat();

}
