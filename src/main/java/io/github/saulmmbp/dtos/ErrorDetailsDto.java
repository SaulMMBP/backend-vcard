package io.github.saulmmbp.dtos;

import java.util.*;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public record ErrorDetailsDto(Date timestamp, String message, Map<String, String> messages, String details) {
    
}
