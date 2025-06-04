package io.github.saulmmbp.exceptions;

import java.util.*;

import org.springframework.http.*;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import io.github.saulmmbp.dtos.ErrorDetailsDto;
import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorDetailsDto> runtimeExceptionHandler(RuntimeException exception, WebRequest request) {
        ErrorDetailsDto errorDetails = new ErrorDetailsDto(new Date(), exception.toString(), null,
                request.getDescription(false));
        log.error(exception.getMessage());
        return ResponseEntity.internalServerError().body(errorDetails);
    }

    @ExceptionHandler(ApplicationException.class)
    public ResponseEntity<ErrorDetailsDto> applicationExceptionHandler(ApplicationException exception,
            WebRequest request) {
        ErrorDetailsDto errorDetails = new ErrorDetailsDto(new Date(), exception.getMessage(), null,
                request.getDescription(false));
        log.error(exception.getMessage());
        return ResponseEntity.internalServerError().body(errorDetails);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorDetailsDto> resourceNotFoundExceptionHandler(ResourceNotFoundException exception,
            WebRequest request) {
        ErrorDetailsDto errorDetails = new ErrorDetailsDto(new Date(), exception.getMessage(), null,
                request.getDescription(false));
        log.info(exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDetails);
    }

    @ExceptionHandler(ResourceNotBelongsUserException.class)
    public ResponseEntity<ErrorDetailsDto> resourceNotBelongsUserExceptionHandler(
            ResourceNotBelongsUserException exception, WebRequest request) {
        ErrorDetailsDto errorDetails = new ErrorDetailsDto(new Date(), exception.getMessage(), null,
                request.getDescription(false));
        log.info(exception.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorDetails);
    }
    
    @ExceptionHandler(ResourceAlreadyExistsException.class)
    public ResponseEntity<ErrorDetailsDto> resourceAlreadyExistsExceptionHandler(
            ResourceNotBelongsUserException exception, WebRequest request) {
        ErrorDetailsDto errorDetails = new ErrorDetailsDto(new Date(), exception.getMessage(), null,
                request.getDescription(false));
        log.info(exception.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorDetails);
    }
    
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorDetailsDto> resourceConstraintValidationExceptionHandler(MethodArgumentNotValidException exception, WebRequest request) {
        Map<String, String> messages = new HashMap<>();
        exception.getBindingResult().getFieldErrors().forEach(fieldError -> messages.put(fieldError.getField(), fieldError.getDefaultMessage()));
        ErrorDetailsDto errorDetails = new ErrorDetailsDto(new Date(), null, messages, request.getDescription(false));
        log.info(exception.getMessage());
        return ResponseEntity.badRequest().body(errorDetails);
    }
    
}
