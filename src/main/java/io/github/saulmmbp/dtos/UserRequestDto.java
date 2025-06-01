package io.github.saulmmbp.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UserRequestDto(
		
		@NotBlank
		String id,
		
		@NotBlank
		@Email
		String email
		
		) {

}
