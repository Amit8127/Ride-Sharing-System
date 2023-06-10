package com.driver.Exceptions;

public class AdminNotFound extends RuntimeException{
    public AdminNotFound(Integer adminId) {
        super("Admin is not present with id :" + Integer.toString(adminId));
    }
}
