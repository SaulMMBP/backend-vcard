package io.github.saulmmbp.utils.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import io.github.saulmmbp.dtos.UserRequestDto;
import io.github.saulmmbp.entities.User;

@Mapper(componentModel = "spring")
public interface IUserMapper {

    @Mapping(target = "auditMetadata", ignore = true)
    User toEntity(UserRequestDto request);

}
