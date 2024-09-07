package com.gustavo.docanalyzer.core.mapper;

import com.gustavo.docanalyzer.core.domain.GeminiSynonym;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface GeminiSynonymMapper {
    GeminiSynonym toGeminiSynonym(String word, String synonyms);
}
