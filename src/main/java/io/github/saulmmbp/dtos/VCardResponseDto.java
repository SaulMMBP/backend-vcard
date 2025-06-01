package io.github.saulmmbp.dtos;

public record VCardResponseDto(
		
		Long id,
		String userId,
		String name,
		String color,
		ContactResponseDto contact,
		byte[] qr
		
		) {

}
