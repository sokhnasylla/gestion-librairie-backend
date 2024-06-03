package com.gestionlibrairie.librairie.exception;

public class MyNotFoundExceptionClass extends RuntimeException{
    public MyNotFoundExceptionClass(String message){
        super(message);
    }
    public MyNotFoundExceptionClass(String message,Throwable cause){
        super(message, cause);
    }
}