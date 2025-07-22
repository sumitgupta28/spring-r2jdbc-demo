package com.spring.react.demo.exception;


import lombok.Getter;

@Getter
public class BookServiceException extends  RuntimeException {


    public BookServiceException(String errorMessage, String apiName, String errorCode)
    {
        super(errorMessage);
        this.apiName=apiName;
        this.errorCode=errorCode;
        this.errorMessage = errorMessage;
    }

    private final String errorMessage;
    private final String apiName;
    private final String errorCode;



}
