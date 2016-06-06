package com.gjeanmart.mybankadvisor.core.exception.business;

import com.gjeanmart.mybankadvisor.core.exception.BusinessException;
import com.gjeanmart.mybankadvisor.core.exception.BusinessExceptionEnum;

public class NotAuthorizedException extends BusinessException {

    private static final long serialVersionUID = 171391875449863515L;

    public NotAuthorizedException(Object entity) {
        super(BusinessExceptionEnum.NOT_AUTHORIZED, entity.toString());
    }

}

