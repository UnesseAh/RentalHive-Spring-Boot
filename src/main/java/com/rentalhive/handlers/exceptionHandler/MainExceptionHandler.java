package com.rentalhive.handlers.exceptionHandler;

import com.rentalhive.handlers.response.ResponseMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDateTime;

@ControllerAdvice
public class MainExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ResponseMessage> resourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
        ResponseMessage message = new ResponseMessage(
                HttpStatus.NOT_FOUND.value(),
                ex.getMessage());

        return new ResponseEntity<ResponseMessage>(message, HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseMessage> globalExceptionHandler(Exception ex, WebRequest request) {
        ResponseMessage message = new ResponseMessage(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                ex.getMessage());

        return new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
