package com.shop.aqua.exception;

public class OutOfStockException extends RuntimeException{
    public OutOfStockException(String message) {//예외처리

        super(message);
    }
}
