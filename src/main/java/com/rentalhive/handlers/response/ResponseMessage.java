package com.rentalhive.handlers.response;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ResponseMessage {
    private int statusCode;
    private String timestamp;
    private String message;

    private Object data;

    public ResponseMessage(int statusCode,Object data,String message) {
        this.statusCode = statusCode;
        this.timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd MMMM yyyy, HH:mm:ss"));
        this.data = data;
        this.message = message;
    }
    public ResponseMessage(int statusCode,String message) {
        this.statusCode = statusCode;
        this.timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd MMMM yyyy, HH:mm:ss"));
        this.message = message;
    }
    public int getStatusCode() {
        return statusCode;
    }

    public String  getTimestamp() {
        return timestamp;
    }

    public String getMessage() {
        return message;
    }
    public Object getData() {
        return data;
    }


}
