package com.gustavo.docanalyzer.api.controller.user.mapper;

import com.gustavo.docanalyzer.api.controller.user.response.UserNoUploadResponse;
import com.gustavo.docanalyzer.core.domain.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserResponseMapper {
    UserNoUploadResponse toResponse(User user);
    List<UserNoUploadResponse> toResponseList(List<User> users);
}
