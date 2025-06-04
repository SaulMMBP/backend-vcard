package io.github.saulmmbp.dtos;

import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

public record ContactRequestDto(
        @NotBlank(message = "El campo identifier es obligatorio")
        String identifier, 
        @NotBlank(message = "El campo name es obligatorio")
        String name, 
        String email, 
        String position,
        String company, 
        String web, 
        @NotEmpty(message = "El contacto debe tener por lo menos un número telefónico")
        List<String> phones) {
}
