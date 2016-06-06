package com.gjeanmart.mybankadvisor.core.exception;

public enum TechnicalExceptionEnum {

    UNEXPECTED				(9999, "Unexpected technical exception : %s");

    private int     code;
    private String message;

    TechnicalExceptionEnum(int code, String message) {
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
