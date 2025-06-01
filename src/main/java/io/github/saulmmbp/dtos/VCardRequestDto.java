package io.github.saulmmbp.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record VCardRequestDto(
		
		@NotBlank
		String name,
		String color,
		
		@NotNull
		Long contactId
		) {

}
