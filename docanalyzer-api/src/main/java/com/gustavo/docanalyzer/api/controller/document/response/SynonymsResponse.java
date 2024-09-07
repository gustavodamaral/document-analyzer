package com.gustavo.docanalyzer.api.controller.document.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SynonymsResponse {
    private String word;
    private String synonyms;
}
