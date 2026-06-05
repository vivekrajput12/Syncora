package com.collaboration.syncora.exceptions;

import com.collaboration.syncora.Constants.CommonConstant;
import com.collaboration.syncora.dtos.response.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<List<String>>> handleValidationError(MethodArgumentNotValidException ex){
        List<String> errors = ex.getBindingResult()
                .getAllErrors()
                .stream()
                .map(err->{
                    if(err instanceof FieldError fieldError){
                        return fieldError.getDefaultMessage();
                    }else{
                        return err.getDefaultMessage();
                    }
                }).toList();
        ApiResponse <List<String>>apiResponse = new ApiResponse(-1 , CommonConstant.REQUEST_FAILED, errors);
        return ResponseEntity.ok(apiResponse);
    }
//    @ExceptionHandler(DataIntegrityViolationException.class)
//    public ResponseEntity<ApiResponse<String>> handleUniqueConstraint(DataIntegrityViolationException ex) {
//        System.out.println("running ");
//        String message = "Duplicate value already exists";
//
//        Throwable cause = ex.getCause();
//        if (cause instanceof org.hibernate.exception.ConstraintViolationException hibernateEx) {
//            String exceptionMsg = hibernateEx.getConstraintName(); // gives the constraint key name
//
//            if (exceptionMsg != null) {
//                if (exceptionMsg.toLowerCase().contains("email")) {
//                    message = "Email is already in use";
//                } else if (exceptionMsg.toLowerCase().contains("username")) {
//                    message = "Username is already taken";
//                }
//            }
//        }
//
//        ApiResponse<String> apiResponse =
//                new ApiResponse<>(-1, CommonConstant.REQUEST_FAILED, message);
//
//        return ResponseEntity.ok(apiResponse);
//    }

}
