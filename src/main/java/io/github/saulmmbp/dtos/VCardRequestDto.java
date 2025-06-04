package io.github.saulmmbp.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record VCardRequestDto(
        @NotBlank(message = "El campo name es obligatorio")
        String name, 
        String color, 
        @NotNull(message = "El campo contactId es obligatorio")
        Long contactId
        ) {

}
