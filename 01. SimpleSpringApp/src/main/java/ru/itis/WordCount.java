package ru.itis;

import java.util.Map;

public interface WordCount {
    void start();

    void save();

    void refresh();

    Map<String, Integer> map(String fileName);
}
