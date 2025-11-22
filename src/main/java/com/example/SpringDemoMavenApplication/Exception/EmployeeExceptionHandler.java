package com.example.SpringDemoMavenApplication.Exception;

import com.example.SpringDemoMavenApplication.Model.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static com.example.SpringDemoMavenApplication.Constants.EmployeeConstants.*;

@ControllerAdvice
public class EmployeeExceptionHandler {


    @ExceptionHandler(EmployeeNotFoundexception.class)
    public ResponseEntity<ErrorResponse> handleEmployeeNotFoundexception(EmployeeNotFoundexception exception) {
        ErrorResponse errorResponse = new ErrorResponse(exception.getMessage(), EMPLOYEE_DOEN_NOT_EXIST);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(DepartmentMismatchException.class)
    public ResponseEntity<ErrorResponse> handleDepartmentMismatchException(DepartmentMismatchException exception) {
        ErrorResponse errorResponse = new ErrorResponse(exception.getMessage(), DEPARTMENT_MIS_MATCH);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }


    @ExceptionHandler(TaskAssignmentException.class)
    public ResponseEntity<ErrorResponse> handleTaskAssignment(TaskAssignmentException ex) {
        ErrorResponse error = new ErrorResponse(
                ex.getMessage(),
                TASK_ASSIGNMENT_ERROR
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }


    @ExceptionHandler(TaskNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleTaskNotFound(TaskNotFoundException ex) {
        ErrorResponse error = new ErrorResponse(
                ex.getMessage(),
                TASK_NOT_FOUND
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResponse> handleRuntime(RuntimeException ex) {
        ErrorResponse error = new ErrorResponse(
                ex.getMessage(),
                "RUNTIME_ERROR"
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGeneric(Exception ex) {
        ErrorResponse error = new ErrorResponse(
                "Unexpected error occurred",
                "INTERNAL_SERVER_ERROR"
        );
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }
}

