package com.example.SpringDemoMavenApplication.Exception;

public class UnauthorizedAssignmentException extends RuntimeException{

    public UnauthorizedAssignmentException(String message){
        super(message);
    }
}
