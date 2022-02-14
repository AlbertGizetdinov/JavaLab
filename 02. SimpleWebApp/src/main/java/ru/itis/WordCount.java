package ru.itis;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Stream;

public class WordCount {
    private final String folder;
    private final List<String> fileNamesList;

    public WordCount(String folder) {
        this.folder = folder;
        fileNamesList = listFilesForFolder(new File(folder));
    }

    private static Map<String, Integer> sortingMap(Map<String, Integer> unsortedMap) {

        List<Map.Entry<String, Integer>> list = new LinkedList<>(unsortedMap.entrySet());

        list.sort((o1, o2) -> o2.getValue().compareTo(o1.getValue()));

        Map<String, Integer> sortedMap = new LinkedHashMap<>();
        for (Map.Entry<String, Integer> entry : list) {
            sortedMap.put(entry.getKey(), entry.getValue());
        }

        return sortedMap;
    }

    private List<String> listFilesForFolder(File folder) {
        List<String> list = new ArrayList<>();
        for (File fileEntry : folder.listFiles()) {
            if (!fileEntry.isDirectory()) {
                list.add(fileEntry.getName());
            }
        }
        return list;
    }

    private void addToMap(Map<String, Integer> hashMap, String str) {
        if (hashMap.containsKey(str)) {
            hashMap.put(str, hashMap.get(str) + 1);
        } else {
            hashMap.put(str, 1);
        }
    }

    private void fillingMap(Map<String, Integer> hashMap, String fileName) {
        try {
            Stream.of(Files.readString(Path.of(fileName))
                            .toLowerCase()
                            .replaceAll("\n", " ")
                            .replaceAll("[^а-яА-Яa-zA-Z0-9 ]", "")
                            .split(" "))
                    .filter(s -> !s.equals(""))
                    .forEach(string -> this.addToMap(hashMap, string));
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public String start() {
        Map<String, Map<String, Integer>> resultMap = new HashMap<>();
        Map<String, Integer> map;
        String folderName;
        for (String file : fileNamesList) {
            map = new HashMap<>();
            folderName = folder + "/" + file;
            fillingMap(map, folderName);
            resultMap.put(file, sortingMap(map));
        }

        StringBuilder string = new StringBuilder();
        for (String file : fileNamesList) {
            string.append("<h1>").append(file).append("</h1>").append("<table border='1'>");
            for (HashMap.Entry<String, Integer> entry : sortingMap(resultMap.get(file)).entrySet()) {
                string.append("<tr>" + "<td>").append(entry.getKey()).append("</td>").append("<td>").append(entry.getValue()).append("</td>").append("</tr>");
            }
            string.append("</table>" + "<br>");
        }
        return string.toString();
    }
}
