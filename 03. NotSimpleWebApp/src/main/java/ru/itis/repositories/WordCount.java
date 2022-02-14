package ru.itis.repositories;

import java.util.Map;

public interface WordCount {
    Map<String, Map<String, Integer>> start();

    void save();

    void refresh();

    Map<String, Integer> map(String fileName);
}
