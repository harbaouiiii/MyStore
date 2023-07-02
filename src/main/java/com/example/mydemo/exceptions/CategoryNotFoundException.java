package com.example.mydemo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serial;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class CategoryNotFoundException extends RuntimeException{

    @Serial
    private static final long serialVersionUID = 1L;

    public CategoryNotFoundException() {
        super("Category not found!");
    }

    public CategoryNotFoundException(String message) {
        super(message);
    }
}
