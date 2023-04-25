package com.harshal.springbootblogrestapi.utils;

import com.harshal.springbootblogrestapi.exception.ResourceNotFoundException;

import java.util.function.Supplier;

public class BlogRestApiutils {

    private static Supplier<ResourceNotFoundException> resourceNotFouncException;

    public static Supplier<ResourceNotFoundException> resourceNotFoundExceptionSupplier(String resource,String field,Long value){
        return ()->new ResourceNotFoundException(resource,field,value);
    }
}
