package com.gustavo.docanalyzer.api.controller.user.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserNoUploadResponse {
    private String email;
    private LocalDateTime registrationDate;
}

