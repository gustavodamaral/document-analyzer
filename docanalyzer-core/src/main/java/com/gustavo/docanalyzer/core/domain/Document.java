package com.gustavo.docanalyzer.core.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Document {
    private Long id;
    private String name;
    private LocalDateTime uploadDate;
    private User user;
    private String content;

    public Map<String, Integer> analyzeWordFrequency() {
        Map<String, Integer> wordFrequency = Arrays.stream(content.toLowerCase().split("[\\s.,:;!?]+"))
                .collect(Collectors.toMap(word -> word, word -> 1, Integer::sum));

        return wordFrequency.entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public String findLongestWord() {
        return Arrays.stream(content.split("[\\s.,:;!?]+"))
                .max(Comparator.comparingInt(String::length))
                .orElse("");
    }

    public String getContextForLongestWord() {
        int wordsAround = 10;

        String regex = String.format("(?:\\b[\\w'-]+\\b[\\s,.]*){0,%d}\\b%s\\b(?:[\\s,.]*\\b[\\w'-]+\\b){0,%d}", wordsAround, this.findLongestWord(), wordsAround);

        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(content);

        if (matcher.find()) {
            return matcher.group();
        }

        return "";
    }
}
