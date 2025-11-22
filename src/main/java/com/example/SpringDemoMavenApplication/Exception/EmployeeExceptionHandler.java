package com.example.SpringDemoMavenApplication.Exception;

import com.example.SpringDemoMavenApplication.Model.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static com.example.SpringDemoMavenApplication.Constants.EmployeeConstants.EMPLOYEE_DOEN_NOT_EXIST;

@ControllerAdvice
public class EmployeeExceptionHandler {


    @ExceptionHandler(EmployeeNotFoundexception.class)
    public ResponseEntity<ErrorResponse> handleEmployeeNotFoundexception(EmployeeNotFoundexception exception) {
        ErrorResponse errorResponse = new ErrorResponse(exception.getMessage(), EMPLOYEE_DOEN_NOT_EXIST);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }
}
