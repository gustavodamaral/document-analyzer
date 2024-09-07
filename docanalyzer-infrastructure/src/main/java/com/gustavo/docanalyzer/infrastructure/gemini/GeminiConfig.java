package com.gustavo.docanalyzer.infrastructure.gemini;

import com.google.cloud.vertexai.VertexAI;
import org.springframework.ai.vertexai.gemini.VertexAiGeminiChatModel;
import org.springframework.ai.vertexai.gemini.VertexAiGeminiChatOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GeminiConfig {

    private static final String PROJECT_ID = "gen-lang-client-0396833014";
    private static final String LOCATION = "us-central1";

    @Bean
    public VertexAI vertexAi() {
        return new VertexAI(PROJECT_ID, LOCATION);
    }

    @Bean
    public VertexAiGeminiChatModel vertexAiGeminiChatModel(VertexAI vertexAi) {
        return new VertexAiGeminiChatModel(vertexAi,
                VertexAiGeminiChatOptions.builder()
                        .withModel("gemini-1.5-pro-001")
                        .withTemperature(0.3F)
                        .withTopK(1F)
                        .withTopP(0.2F)
                        .build()
        );
    }
}
