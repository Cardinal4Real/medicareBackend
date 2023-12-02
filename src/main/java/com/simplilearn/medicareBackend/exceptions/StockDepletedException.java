package com.simplilearn.medicareBackend.exceptions;

public class StockDepletedException extends RuntimeException {
    public StockDepletedException(String msg) {
        super(msg);
    }
}
