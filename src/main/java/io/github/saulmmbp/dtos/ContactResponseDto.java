package io.github.saulmmbp.dtos;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public record ContactResponseDto(Long id, String userId, String identifier, String name, String email, String position,
        String company, String web, List<String> phones) {

}
