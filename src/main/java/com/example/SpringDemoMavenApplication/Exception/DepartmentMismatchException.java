package com.example.SpringDemoMavenApplication.Exception;

public class DepartmentMismatchException extends RuntimeException{
    public DepartmentMismatchException(String message){
        super(message);
    }
}
