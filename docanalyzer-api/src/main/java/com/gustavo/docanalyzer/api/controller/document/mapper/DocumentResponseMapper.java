package com.gustavo.docanalyzer.api.controller.document.mapper;

import com.gustavo.docanalyzer.api.controller.document.response.WordFreqResponse;
import org.mapstruct.Mapper;

import java.util.Map;

@Mapper(componentModel = "spring")
public interface DocumentResponseMapper {
    WordFreqResponse toResponse(Long documentId, Map<String, Integer> wordFrequency);
}
