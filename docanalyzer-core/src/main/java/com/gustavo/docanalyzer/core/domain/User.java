package com.gustavo.docanalyzer.core.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private Long id;
    private String email;
    private LocalDateTime registrationDate;
    private Set<Team> teams;
    private Set<Document> documents;
}
