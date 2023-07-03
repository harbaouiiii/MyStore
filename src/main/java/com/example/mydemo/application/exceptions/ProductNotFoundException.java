package com.example.mydemo.application.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serial;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ProductNotFoundException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;
    public ProductNotFoundException() {
        super("Product not found!");
    }

    public ProductNotFoundException(String message) {
        super(message);
    }
}
