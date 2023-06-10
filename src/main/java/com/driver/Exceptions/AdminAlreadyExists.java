package com.driver.Exceptions;

public class AdminAlreadyExists extends RuntimeException{
    public AdminAlreadyExists() {
        super("Admin is Already Exists id Data");
    }
}
