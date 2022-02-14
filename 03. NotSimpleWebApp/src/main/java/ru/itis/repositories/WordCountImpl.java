package ru.itis.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.PreparedStatement;
import java.util.*;
import java.util.Map.Entry;
import java.util.stream.Stream;

@Component
public class WordCountImpl implements WordCount {
    //language=SQL
    private static final String SQL_INSERT = "insert into word_count(word, amount, file_name) " +
            "values (?, ?, ?)";
    //language=SQL
    private static final String SQL_SELECT = "select word, amount from word_count " +
            "where file_name = ? order by amount";
    //language=SQL
    private static final String SQL_REFRESH = "truncate word_count";

    private final ResultSetExtractor<Map<String, Integer>> resultSetExtractor = resultSet -> {
        Map<String, Integer> map = new HashMap<>();
        while (resultSet.next()) {
            map.put(resultSet.getString("word"), resultSet.getInt("amount"));
        }
        return map;
    };
    private final JdbcTemplate jdbcTemplate;
    private String folder;
    private List<String> fileNamesList;

    @Autowired
    public WordCountImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
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

    public void setFolder(String folder) {
        this.folder = folder;
        fileNamesList = listFilesForFolder(new File(folder));
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

    @Override
    public Map<String, Map<String, Integer>> start() {
        refresh();
        save();
        Map<String, Map<String, Integer>> resultMap = new HashMap<>();
        for (String file : fileNamesList) {
            resultMap.put(file, sortingMap(map(file)));
        }
        return resultMap;
    }

    @Override
    public void save() {
        Map<String, Integer> map;
        String fileName;
        for (String file : fileNamesList) {
            map = new HashMap<>();
            fileName = folder + "/" + file;
            fillingMap(map, fileName);
            for (Entry<String, Integer> entry : map.entrySet()) {
                jdbcTemplate.update(connection -> {
                    PreparedStatement statement = connection.prepareStatement(SQL_INSERT);

                    statement.setString(1, entry.getKey());
                    statement.setInt(2, entry.getValue());
                    statement.setString(3, file);

                    return statement;
                });
            }
        }
    }

    @Override
    public void refresh() {
        jdbcTemplate.update(SQL_REFRESH);
    }

    @Override
    public Map<String, Integer> map(String fileName) {
        return jdbcTemplate.query(SQL_SELECT, resultSetExtractor, fileName);
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
}