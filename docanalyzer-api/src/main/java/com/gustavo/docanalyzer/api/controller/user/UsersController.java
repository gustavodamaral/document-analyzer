package com.gustavo.docanalyzer.api.controller.user;

import com.gustavo.docanalyzer.api.controller.user.mapper.UserResponseMapper;
import com.gustavo.docanalyzer.api.controller.user.response.UserNoUploadListResponse;
import com.gustavo.docanalyzer.api.exception.InvalidDateRangeException;
import com.gustavo.docanalyzer.core.domain.User;
import com.gustavo.docanalyzer.core.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    public ResponseEntity<UserNoUploadListResponse> usersNoUploadGet(
            @RequestParam LocalDate startDate,
            @RequestParam LocalDate endDate) {
        if (endDate != null && startDate != null && endDate.isBefore(startDate)) {
            throw new InvalidDateRangeException("End date cannot be before start date.");
        }

        List<User> users = service.getUsersByNoUploadWithinPeriod(startDate, endDate);
        UserNoUploadListResponse response = mapper.toResponseList(users, users.size());
        return ResponseEntity.ok(response);
    }

}
