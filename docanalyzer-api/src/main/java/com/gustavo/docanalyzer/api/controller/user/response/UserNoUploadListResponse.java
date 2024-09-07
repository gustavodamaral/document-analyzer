package com.gustavo.docanalyzer.api.controller.user.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserNoUploadListResponse {
    private int usersCount;
    private List<UserNoUploadResponse> users;
}

