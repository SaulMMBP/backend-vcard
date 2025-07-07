package io.github.saulmmbp.exceptions;

import java.util.*;

import org.springframework.http.*;
import org.springframework.security.authorization.AuthorizationDeniedException;
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
        log.info("[{}] {}", HttpStatus.INTERNAL_SERVER_ERROR,exception.getMessage());
        return ResponseEntity.internalServerError().body(errorDetails);
    }

    @ExceptionHandler(ApplicationException.class)
    public ResponseEntity<ErrorDetailsDto> applicationExceptionHandler(ApplicationException exception,
            WebRequest request) {
        ErrorDetailsDto errorDetails = new ErrorDetailsDto(new Date(), exception.getMessage(), null,
                request.getDescription(false));
        log.info("[{}] {}", HttpStatus.INTERNAL_SERVER_ERROR,exception.getMessage());
        return ResponseEntity.internalServerError().body(errorDetails);
    }
    
    @ExceptionHandler(AuthorizationDeniedException.class)
    public ResponseEntity<?> applicationExceptionHandler(AuthorizationDeniedException exception, WebRequest request) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED.value()).build();
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorDetailsDto> resourceNotFoundExceptionHandler(ResourceNotFoundException exception,
            WebRequest request) {
        ErrorDetailsDto errorDetails = new ErrorDetailsDto(new Date(), exception.getMessage(), null,
                request.getDescription(false));
        log.info("[{}] {}", HttpStatus.NOT_FOUND,exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDetails);
    }

    @ExceptionHandler(ResourceNotBelongsUserException.class)
    public ResponseEntity<ErrorDetailsDto> resourceNotBelongsUserExceptionHandler(
            ResourceNotBelongsUserException exception, WebRequest request) {
        ErrorDetailsDto errorDetails = new ErrorDetailsDto(new Date(), exception.getMessage(), null,
                request.getDescription(false));
        log.info("[{}] {}", HttpStatus.UNAUTHORIZED,exception.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorDetails);
    }
    
    @ExceptionHandler(ResourceAlreadyExistsException.class)
    public ResponseEntity<ErrorDetailsDto> resourceAlreadyExistsExceptionHandler(
            ResourceAlreadyExistsException exception, WebRequest request) {
        ErrorDetailsDto errorDetails = new ErrorDetailsDto(new Date(), exception.getMessage(), null,
                request.getDescription(false));
        log.info("[{}] {}", HttpStatus.CONFLICT,exception.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorDetails);
    }
    
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorDetailsDto> resourceConstraintValidationExceptionHandler(MethodArgumentNotValidException exception, WebRequest request) {
        Map<String, String> messages = new HashMap<>();
        exception.getBindingResult().getFieldErrors().forEach(fieldError -> messages.put(fieldError.getField(), fieldError.getDefaultMessage()));
        ErrorDetailsDto errorDetails = new ErrorDetailsDto(new Date(), null, messages, request.getDescription(false));
        log.info("[{}] {}", HttpStatus.BAD_REQUEST,exception.getMessage());
        return ResponseEntity.badRequest().body(errorDetails);
    }
    
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorDetailsDto> illegalArgumentExceptionHandler(IllegalArgumentException exception,
            WebRequest request) {
        ErrorDetailsDto errorDetails = new ErrorDetailsDto(new Date(), exception.getMessage(), null,
                request.getDescription(false));
        log.info("[{}] {}", HttpStatus.BAD_REQUEST,exception.getMessage());
        return ResponseEntity.badRequest().body(errorDetails);
    }
    
}
