package com.collaboration.syncora.dtos.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;

import java.util.Optional;

@Getter
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ApiResponse<T> {
    private int status;
    private String message;
    private Optional<T> data;
    public ApiResponse(int status , String message, T data){
        this.status = status;
        this.message = message;
        this.data = Optional.ofNullable(data);
    }
    public ApiResponse(int status , String message){
        this.status = status;
        this.message = message;
        this.data = Optional.empty();
    }
}