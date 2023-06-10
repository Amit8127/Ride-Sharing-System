package com.driver.Exceptions;

public class CustomerNotFound extends RuntimeException{
    public CustomerNotFound() {
        super("Customer is not present");
    }
}
