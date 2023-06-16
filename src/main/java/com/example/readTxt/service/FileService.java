package com.example.readTxt.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class FileService {

    public Map<String,Long> getFrequencyList(MultipartFile file) throws IOException {
        String content = new String(file.getBytes(), StandardCharsets.UTF_8);

        String[] wordsTable = content.split("\\P{L}+");
        Map<String, Long> counted = Arrays.stream(wordsTable)
                .filter(word -> word.length()>0)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        return counted.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,(w1,w2) -> w1, LinkedHashMap::new));
    }
}
