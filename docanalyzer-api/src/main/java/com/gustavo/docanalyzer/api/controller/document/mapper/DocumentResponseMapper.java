package com.gustavo.docanalyzer.api.controller.document.mapper;

import com.gustavo.docanalyzer.api.controller.document.response.SynonymsResponse;
import com.gustavo.docanalyzer.api.controller.document.response.WordFreqResponse;
import com.gustavo.docanalyzer.core.domain.GeminiSynonym;
import org.mapstruct.Mapper;

import java.util.Map;

@Mapper(componentModel = "spring")
public interface DocumentResponseMapper {

    WordFreqResponse toWordFreqResponse(Long documentId, Map<String, Integer> wordFrequency);

    SynonymsResponse toSynonymsResponse(GeminiSynonym geminiSynonym);
}
