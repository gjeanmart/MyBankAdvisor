package com.gjeanmart.mybankadvisor.core.exception.technical;

import com.gjeanmart.mybankadvisor.core.exception.TechnicalException;

public class DAOException extends TechnicalException {

    private static final long serialVersionUID = 3398176970163036088L;

    public DAOException(String message) {
        super(message);
    }

    public DAOException(Throwable e) {
        super(e);
    }

}