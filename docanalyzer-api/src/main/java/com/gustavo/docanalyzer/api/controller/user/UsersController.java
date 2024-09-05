package com.gustavo.docanalyzer.api.controller.user;

import com.gustavo.docanalyzer.api.controller.user.mapper.UserResponseMapper;
import com.gustavo.docanalyzer.api.controller.user.response.UserNoUploadListResponse;
import com.gustavo.docanalyzer.api.controller.user.response.UserNoUploadResponse;
import com.gustavo.docanalyzer.core.domain.User;
import com.gustavo.docanalyzer.core.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController()
@RequiredArgsConstructor
@RequestMapping("/users")
public class UsersController {

    private final UserService service;
    private final UserResponseMapper mapper;

    @GetMapping("/no-upload")
    public ResponseEntity<UserNoUploadListResponse> usersNoUploadGet(LocalDate startDate, LocalDate endDate) {
        List<User> users = service.getUsersByNoUploadWithinPeriod(startDate, endDate);
        List<UserNoUploadResponse> userResponses = mapper.toResponseList(users);
        UserNoUploadListResponse response = new UserNoUploadListResponse(userResponses);
        return ResponseEntity.ok(response);
    }

}
