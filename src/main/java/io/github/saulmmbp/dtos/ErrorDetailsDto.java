package io.github.saulmmbp.dtos;

import java.util.Date;

public record ErrorDetailsDto(Date timestamp, String message, String details) {

}
