package com.company.RESTApi.Exceptions;

public class GPSInfoNotFoundException extends RuntimeException{

    public GPSInfoNotFoundException(Long id)  {
        super("Could not find GPSInfo of id " + id);
    }
}
