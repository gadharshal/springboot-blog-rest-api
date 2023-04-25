package com.harshal.springbootblogrestapi.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Data
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BlogAPIException extends  RuntimeException{

    private HttpStatus status;
    private String message;

    public BlogAPIException(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    public BlogAPIException(HttpStatus status, String message,String message1) {
        super(message);
        this.status = status;
        this.message = message1;
    }

   /* public HttpStatus getStatus() {
        return status;
    }

    @Override
    public String getMessage() {
        return message;
    }*/
}
