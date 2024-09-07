package com.gustavo.docanalyzer.core.service;

import com.gustavo.docanalyzer.core.domain.Document;
import com.gustavo.docanalyzer.core.exception.DocumentNotFoundException;
import com.gustavo.docanalyzer.infrastructure.db.entity.DocumentEntity;
import com.gustavo.docanalyzer.infrastructure.db.repository.DocumentRepository;
import com.gustavo.docanalyzer.infrastructure.s3.S3Api;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class DocumentService {

    private static final Set<String> EXCLUDED_WORDS = Set.of("the", "me", "you", "i", "of", "and", "a", "we");
    private final S3Api s3Api;
    private final DocumentRepository documentRepository;

    public Map<String, Integer> getWordFrequencyForDocument(Long documentId, int topN) {
        DocumentEntity documentEntity = documentRepository.findById(documentId).orElseThrow(() ->
                new DocumentNotFoundException("Document not found"));

        String content = s3Api.getDocumentContentByMetadata(documentEntity.getS3Key());

        Document document = new Document();
        document.setContent(content);

        Map<String, Integer> wordFrequency = document.analyzeWordFrequency();

        return wordFrequency.entrySet()
                .stream()
                .filter(word -> !EXCLUDED_WORDS.contains(word.getKey()))
                .sorted((Map.Entry.<String, Integer>comparingByValue().reversed()))
                .limit(topN)
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e1,
                        LinkedHashMap::new
                ));
    }

}
