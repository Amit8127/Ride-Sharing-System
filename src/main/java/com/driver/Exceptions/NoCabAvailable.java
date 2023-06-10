package com.driver.Exceptions;

public class NoCabAvailable extends RuntimeException{
    public NoCabAvailable() {
        super("No cab available!");
    }
}
