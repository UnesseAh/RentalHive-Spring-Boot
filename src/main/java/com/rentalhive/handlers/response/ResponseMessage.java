package com.rentalhive.handlers.response;

import java.time.LocalDateTime;

public class ResponseMessage {
    private int statusCode;
    private LocalDateTime timestamp;
    private String message;

    private Object data;

    public ResponseMessage(int statusCode,Object data,String message) {
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
    public Object getData() {
        return data;
    }


}
