package com.example.demo.exception.handler;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class TodoItemException extends RuntimeException {
    
    private String message;
    private HttpStatus httpStatus;
    
    @Override
    public String toString() {
        return "TodoItemException [message=" + message + ", httpStatus=" + httpStatus + "]";
    }
}
