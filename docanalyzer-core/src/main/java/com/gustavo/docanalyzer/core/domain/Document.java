package com.gustavo.docanalyzer.core.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Document {
    private Long id;
    private String name;
    private int wordCount;
    private LocalDateTime uploadDate;
    private User user;
    private String content;

    public Map<String, Integer> analyzeWordFrequency() {
        Map<String, Integer> wordFrequency = Arrays.stream(content.toLowerCase().split("\\s+"))
                .collect(Collectors.toMap(word -> word, word -> 1, Integer::sum));

        return wordFrequency.entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public String findLongestWord() {
        return Arrays.stream(content.split("\\W+"))
                .max(Comparator.comparingInt(String::length))
                .orElse("");
    }
}
