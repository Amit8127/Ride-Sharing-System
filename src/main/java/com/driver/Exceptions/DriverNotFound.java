package com.driver.Exceptions;

public class DriverNotFound extends RuntimeException{
    public DriverNotFound(Integer driverId) {
        super("Driver is not present with Id: " + Integer.toString(driverId));
    }
}
