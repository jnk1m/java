package com.nhnacademy.gw1.parking.exception;

public class NotEnoughMoneyException extends RuntimeException {
    public NotEnoughMoneyException(int amount) {
        super("Please check your balance. Required balance is " + amount);
    }
}
