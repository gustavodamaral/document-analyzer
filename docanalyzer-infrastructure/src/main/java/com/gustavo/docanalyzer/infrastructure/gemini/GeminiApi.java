package com.gustavo.docanalyzer.infrastructure.gemini;

import org.springframework.ai.vertexai.gemini.VertexAiGeminiChatModel;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class GeminiApi {

    private final VertexAiGeminiChatModel chatModel;

    public String generation(String userInput) {
        return this.chatModel.call(userInput);
    }

}
