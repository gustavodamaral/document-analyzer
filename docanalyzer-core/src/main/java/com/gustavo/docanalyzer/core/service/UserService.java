package com.gustavo.docanalyzer.core.service;

import com.gustavo.docanalyzer.core.domain.User;
import com.gustavo.docanalyzer.core.mapper.UserMapper;
import com.gustavo.docanalyzer.infrastructure.db.entity.UserEntity;
import com.gustavo.docanalyzer.infrastructure.db.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public List<User> getUsersByNoUploadWithinPeriod(LocalDate startDate, LocalDate endDate) {
        LocalDateTime startDateTime = startDate.atStartOfDay();
        LocalDateTime endDateTime = endDate.atTime(LocalTime.MAX);
        List<UserEntity> users = userRepository.findUsersByNoUploadWithinPeriod(startDateTime, endDateTime);

        return users.stream()
                .map(userMapper::toUser)
                .toList();
    }
}
