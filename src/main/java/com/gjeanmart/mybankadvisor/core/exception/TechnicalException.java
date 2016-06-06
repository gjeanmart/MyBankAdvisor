package com.gjeanmart.mybankadvisor.core.exception;

import java.io.Serializable;
import java.util.Date;

public class TechnicalException extends Exception implements Serializable {

    private static final long serialVersionUID = 7599983583320113972L;

    private final TechnicalExceptionEnum technicalException = null;
    private final String message;
    private final Date date;


    public TechnicalException(Throwable e) {
        super(e);
        this.message = String.format(TechnicalExceptionEnum.UNEXPECTED.getMessage(), e.getMessage());
        this.date = new Date();
    }

    public TechnicalException(String message) {
        super(message);
        this.message = String.format(TechnicalExceptionEnum.UNEXPECTED.getMessage(), message);
        this.date = new Date();
    }

    public TechnicalException(String message, Date date) {
        super(message);
        this.message = message;
        this.date = date;
    }

    public String getMessage() {
        return message;
    }
    public Date getDate() {
        return date;
    }

    public TechnicalExceptionEnum getTechnicalException() {
        return technicalException;
    }

    @Override
    public String toString() {
        return "TechnicalException [message=" + message + ", date=" + date
                + "]";
    }
}
