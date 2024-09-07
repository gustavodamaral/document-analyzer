package com.gustavo.docanalyzer.api.controller.document;

import com.gustavo.docanalyzer.api.controller.document.mapper.DocumentResponseMapper;
import com.gustavo.docanalyzer.api.controller.document.response.SynonymsResponse;
import com.gustavo.docanalyzer.api.controller.document.response.WordFreqResponse;
import com.gustavo.docanalyzer.core.domain.GeminiSynonym;
import com.gustavo.docanalyzer.core.exception.DocumentNotFoundException;
import com.gustavo.docanalyzer.core.service.DocumentService;
import com.gustavo.docanalyzer.core.service.GeminiService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/documents")
public class DocumentsController {

    private final DocumentService documentService;
    private final GeminiService geminiService;
    private final DocumentResponseMapper documentResponseMapper;

    @GetMapping("/{documentId}/synonyms")
    public ResponseEntity<SynonymsResponse> getLongestWordSynonyms(@PathVariable Long documentId) {
        try {
            GeminiSynonym synonymSuggestions = geminiService.getSynonymSuggestions(documentId);
            SynonymsResponse response = documentResponseMapper.toSynonymsResponse(synonymSuggestions);
            return ResponseEntity.ok(response);
        } catch (DocumentNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/{documentId}/word-frequency")
    public ResponseEntity<WordFreqResponse> getWordFrequency(
            @PathVariable Long documentId,
            @RequestParam(defaultValue = "10") int topN) {

        try {
            Map<String, Integer> wordFrequency = documentService.getWordFrequencyForDocument(documentId, topN);
            WordFreqResponse response = documentResponseMapper.toWordFreqResponse(documentId, wordFrequency);

            return ResponseEntity.ok(response);
        } catch (DocumentNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

}
