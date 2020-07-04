package com.okren.spring_java_advanced.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

@RestControllerAdvice  // означає, що контроллер повертає JSON
public class ErrorHandlingController {

    @ExceptionHandler({ResponseStatusException.class})   // єдиний параметр - клас ексепшинів, які він буде ловити
    public ResponseEntity<ErrorResponse> handleResponseStatusException(ResponseStatusException ex) {   // Методи зазвичай повертають ResponseEntity. Параметром передається Exception, який прилетить
        ErrorResponse errorResponse = new ErrorResponse(ex.getReason());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);    // повертаєм ResponseEntity з message і HttpStatus
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex){
        // WORKS ONLY IN CASES WHEN ERROR IS IN ONE FIELD.
        // NEED TO BE REWORKED FOR MULTIPLE ERRORS IN FIELDS.
        FieldError fieldError = ex.getBindingResult().getFieldError();
        String message = "Object " +
                fieldError.getObjectName() +
                " , field " +
                fieldError.getField() +
                "  -  " +
                fieldError.getDefaultMessage();
//        StringBuilder message = new StringBuilder()
//                .append("Object ")
//                .append(fieldError.getObjectName())
//                .append(" , field ")
//                .append(fieldError.getField())
//                .append("  -  ")
//                .append(fieldError.getDefaultMessage());

        ErrorResponse errorResponse = new ErrorResponse(message);
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @Data
    @AllArgsConstructor
    private static class ErrorResponse {   // створюєм клас з полем message
        private String message;
    }
}
