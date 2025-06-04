package io.github.saulmmbp.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UserRequestDto(
        @NotBlank(message = "El campo id es obligatorio")
        String id, 
        @NotBlank(message = "El campo email es obligatorio")
        @Email(message = "El campo email debe tener el formato correcto")
        String email
        ) {

}
