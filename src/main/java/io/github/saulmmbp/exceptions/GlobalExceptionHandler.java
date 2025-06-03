package io.github.saulmmbp.exceptions;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import io.github.saulmmbp.dtos.ErrorDetailsDto;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorDetailsDto> runtimeExceptionHandler(RuntimeException exception, WebRequest request) {
        ErrorDetailsDto errorDetails = new ErrorDetailsDto(new Date(), exception.toString(),
                request.getDescription(false));
        return ResponseEntity.internalServerError().body(errorDetails);
    }

    @ExceptionHandler(ApplicationException.class)
    public ResponseEntity<ErrorDetailsDto> applicationExceptionHandler(ApplicationException exception,
            WebRequest request) {
        ErrorDetailsDto errorDetails = new ErrorDetailsDto(new Date(), exception.getMessage(),
                request.getDescription(false));
        return ResponseEntity.internalServerError().body(errorDetails);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorDetailsDto> resourceNotFoundExceptionHandler(ResourceNotFoundException exception,
            WebRequest request) {
        ErrorDetailsDto errorDetails = new ErrorDetailsDto(new Date(), exception.getMessage(),
                request.getDescription(false));
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDetails);
    }

    @ExceptionHandler(ResourceNotBelongsUserException.class)
    public ResponseEntity<ErrorDetailsDto> resourceNotBelongsUserExceptionHandler(
            ResourceNotBelongsUserException exception, WebRequest request) {
        ErrorDetailsDto errorDetails = new ErrorDetailsDto(new Date(), exception.getMessage(),
                request.getDescription(false));
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorDetails);
    }
    
    @ExceptionHandler(ResourceAlreadyExistsException.class)
    public ResponseEntity<ErrorDetailsDto> resourceAlreadyExistsExceptionHandler(
            ResourceNotBelongsUserException exception, WebRequest request) {
        ErrorDetailsDto errorDetails = new ErrorDetailsDto(new Date(), exception.getMessage(),
                request.getDescription(false));
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorDetails);
    }
    
}
