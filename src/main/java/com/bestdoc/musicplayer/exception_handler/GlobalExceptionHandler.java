package com.bestdoc.musicplayer.exception_handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author nandishamm
 */
@ControllerAdvice
public class GlobalExceptionHandler  {

    /**
     * Default error handler
     * @param ex
     * @param request
     * @return
     */
    @ExceptionHandler(value = {Exception.class})
    protected ResponseEntity handleInvalidDataException(
            Exception ex, WebRequest request) {
        Map<String, String> errors = new HashMap<>();
        errors.put("timestamp", new Date().toString());
        errors.put("status", HttpStatus.BAD_REQUEST.toString());
        errors.put("error", "Bad Request");
        errors.put("message", ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .contentType(MediaType.APPLICATION_JSON)
                .body(errors);
    }

    /**
     * validation handler
     * @param ex
     * @return
     */

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        StringBuilder stringBuilder= new StringBuilder();
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().stream().forEach(error -> {
            stringBuilder.append(error.getDefaultMessage());
            stringBuilder.append(", ");
        });
        errors.put("timestamp",new Date().toString());
        errors.put("status", HttpStatus.BAD_REQUEST.toString());
        errors.put("message", stringBuilder.toString());
        errors.put("error", "Bad Request");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .contentType(MediaType.APPLICATION_JSON).body(errors);
    }

}
