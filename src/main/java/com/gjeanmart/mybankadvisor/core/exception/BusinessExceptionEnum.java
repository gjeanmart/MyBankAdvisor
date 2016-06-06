package com.gjeanmart.mybankadvisor.core.exception;

public enum BusinessExceptionEnum {

    UNEXPECTED				(9999, "Unexpected exception : %s"),
    ALREADY_EXISTS			(0001, "The entity %s already exists"),
    DO_NOT_EXIST			(0002, "The entity %s does not exist"),
    NOT_AUTHORIZED 	        (0003, "You are not authorized to access to this resource");

    private int     code;
    private String  message;

    BusinessExceptionEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


}