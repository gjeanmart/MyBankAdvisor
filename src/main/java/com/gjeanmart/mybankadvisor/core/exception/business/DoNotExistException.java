package com.gjeanmart.mybankadvisor.core.exception.business;

import com.gjeanmart.mybankadvisor.core.exception.BusinessException;
import com.gjeanmart.mybankadvisor.core.exception.BusinessExceptionEnum;

public class DoNotExistException extends BusinessException {

    private static final long serialVersionUID = 171391875449863515L;

    public DoNotExistException(Object entity) {
        super(BusinessExceptionEnum.DO_NOT_EXIST, entity.toString());
    }

}

