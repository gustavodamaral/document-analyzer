package com.gustavo.docanalyzer.core.mapper;


import com.gustavo.docanalyzer.core.domain.User;
import com.gustavo.docanalyzer.infrastructure.db.entity.UserEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User toUser(UserEntity userEntity);

}
