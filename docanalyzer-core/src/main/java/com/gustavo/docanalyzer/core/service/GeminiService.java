package com.gustavo.docanalyzer.core.service;

import com.gustavo.docanalyzer.core.domain.Document;
import com.gustavo.docanalyzer.core.domain.GeminiSynonym;
import com.gustavo.docanalyzer.core.exception.DocumentNotFoundException;
import com.gustavo.docanalyzer.core.mapper.GeminiSynonymMapper;
import com.gustavo.docanalyzer.infrastructure.db.entity.DocumentEntity;
import com.gustavo.docanalyzer.infrastructure.db.repository.DocumentRepository;
import com.gustavo.docanalyzer.infrastructure.gemini.GeminiApi;
import com.gustavo.docanalyzer.infrastructure.s3.S3Api;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class GeminiService {

    private static final String FIND_SYNONYMS_PROMPT =
            "Give up to 3 best suggestions to replace the word %s in the following context: \"%s\" " +
                    "The suggestions must be appropriate to replace the word in this context without changing the meaning. " +
                    "The response must be in a single line, separated by commas with no spaces. No additional text.";


    private final GeminiApi geminiApi;
    private final S3Api s3Api;
    private final DocumentRepository documentRepository;
    private final GeminiSynonymMapper geminiSynonymMapper;

    public GeminiSynonym getSynonymSuggestions(Long documentId) {
        DocumentEntity documentEntity = documentRepository.findById(documentId).orElseThrow(() ->
                new DocumentNotFoundException("Document not found"));

        String content = s3Api.getDocumentContentByMetadata(documentEntity.getS3Key());

        Document document = new Document();
        document.setContent(content);

        String longestWord = document.findLongestWord();
        String context = document.getContextForLongestWord();

        String geminiResponse = geminiApi.generation(String.format(FIND_SYNONYMS_PROMPT, longestWord, context));

        return geminiSynonymMapper.toGeminiSynonym(longestWord, geminiResponse.trim());
    }

}
