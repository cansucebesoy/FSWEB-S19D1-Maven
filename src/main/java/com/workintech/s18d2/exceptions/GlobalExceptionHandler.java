package com.workintech.s18d2.exceptions;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    private ResponseEntity<PlantErrorResponse> handleException(PlantException exception){
        log.error("plant exception occured! exception details: ", exception);
        PlantErrorResponse plantErrorResponse = new PlantErrorResponse(exception.getHttpStatus().value(), exception.getMessage(), LocalDateTime.now());
        return new ResponseEntity<>(plantErrorResponse, exception.getHttpStatus());
    }

    @ExceptionHandler
    private ResponseEntity<PlantErrorResponse> handleException(Exception exception){
        log.error("plant exception occured! exception details: ", exception);
        PlantErrorResponse plantErrorResponse = new PlantErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), exception.getMessage(), LocalDateTime.now());
        return new ResponseEntity<>(plantErrorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
