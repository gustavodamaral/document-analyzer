package com.gustavo.docanalyzer.api.controller.document.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WordFreqResponse {
    private Long documentId;
    private Map<String, Integer> wordFrequency;
}
