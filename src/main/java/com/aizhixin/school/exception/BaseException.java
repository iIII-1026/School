package com.aizhixin.school.exception;

public class BaseException extends RuntimeException {

    private static final long serialVersionUID = 8258442819452024103L;
    private Integer code;
    private String message;

    public BaseException(Integer code, String message){
        super(message);
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return this.code;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
