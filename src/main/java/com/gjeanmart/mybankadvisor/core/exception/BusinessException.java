package com.gjeanmart.mybankadvisor.core.exception;

import java.io.Serializable;
import java.util.Date;

public class BusinessException extends Exception implements Serializable {

    private static final long serialVersionUID = 7599983583320113972L;

    private final BusinessExceptionEnum businessException;
    private final String message;
    private final Date date;


    public BusinessException(Throwable e) {
        super(e);
        this.businessException = BusinessExceptionEnum.UNEXPECTED;
        this.message = String.format(BusinessExceptionEnum.UNEXPECTED.getMessage(), e.getMessage());
        this.date = new Date();
    }

    public BusinessException(String message) {
        super(message);
        this.businessException = BusinessExceptionEnum.UNEXPECTED;
        this.message = String.format(BusinessExceptionEnum.UNEXPECTED.getMessage(), message);
        this.date = new Date();
    }

    public BusinessException(BusinessExceptionEnum businessException) {
        super(businessException.getMessage());
        this.businessException = businessException;
        this.message = businessException.getMessage();
        this.date = new Date();
    }

    public BusinessException(BusinessExceptionEnum businessException, String message) {
        super(String.format(businessException.getMessage(), message));
        this.businessException = businessException;
        this.message = String.format(businessException.getMessage(), message);
        this.date = new Date();
    }

    public BusinessException(BusinessExceptionEnum businessException, String message, Date date) {
        super(String.format(businessException.getMessage(), message));
        this.businessException = businessException;
        this.message = String.format(businessException.getMessage(), message);
        this.date = date;
    }

    public BusinessExceptionEnum getBusinessExceptionEnum() {
        return businessException;
    }
    public String getMessage() {
        return message;
    }
    public Date getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "BusinessException [businessException=" + businessException + ", message=" + message + ", date=" + date
                + "]";
    }



}
