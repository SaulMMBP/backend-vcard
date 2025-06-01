package io.github.saulmmbp.dtos;

import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

public record ContactRequestDto(
		
		@NotBlank
		String identifier,
		
		@NotBlank
		String name,
		String email,
		String position,
		String company,
		String web,
		
		@NotEmpty
		List<String> phones
		) {

}
