package com.rentalhive.handlers.response;

import java.time.LocalDateTime;

public class ResponseMessage<T> {
    private int statusCode;
    private LocalDateTime timestamp;
    private String message;

    private T data;

    public ResponseMessage(int statusCode,T data,String message) {
        this.statusCode = statusCode;
        this.timestamp = LocalDateTime.now();
        this.data = data;
        this.message = message;
    }
    public ResponseMessage(int statusCode,String message) {
        this.statusCode = statusCode;
        this.timestamp = LocalDateTime.now();
        this.message = message;
    }
    public int getStatusCode() {
        return statusCode;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public String getMessage() {
        return message;
    }
    public T getData() {
        return data;
    }


}
