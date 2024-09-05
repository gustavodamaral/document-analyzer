package com.gustavo.docanalyzer.api.controller.document;

import com.gustavo.docanalyzer.api.controller.document.mapper.DocumentResponseMapper;
import com.gustavo.docanalyzer.api.controller.document.response.WordFreqResponse;
import com.gustavo.docanalyzer.core.exception.DocumentNotFoundException;
import com.gustavo.docanalyzer.core.service.DocumentService;
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
    private final DocumentResponseMapper documentResponseMapper;

    @GetMapping("/{documentId}/word-frequency")
    public ResponseEntity<WordFreqResponse> getWordFrequency(
            @PathVariable Long documentId,
            @RequestParam(defaultValue = "10") int topN) {

        try {
            Map<String, Integer> wordFrequency = documentService.getWordFrequencyForDocument(documentId, topN);
            WordFreqResponse response = documentResponseMapper.toResponse(documentId, wordFrequency);

            return ResponseEntity.ok(response);
        } catch (DocumentNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

}
