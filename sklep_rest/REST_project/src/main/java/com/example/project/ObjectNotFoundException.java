package com.example.project;

public class ObjectNotFoundException extends RuntimeException{
    public ObjectNotFoundException(int id, String msg) {
        super(msg + id);
    }
}
