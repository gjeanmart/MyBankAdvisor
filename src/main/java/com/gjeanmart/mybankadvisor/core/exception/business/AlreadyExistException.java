package com.gjeanmart.mybankadvisor.core.exception.business;

import com.gjeanmart.mybankadvisor.core.exception.BusinessException;
import com.gjeanmart.mybankadvisor.core.exception.BusinessExceptionEnum;

public class AlreadyExistException extends BusinessException {

    private static final long serialVersionUID = 7131275773055376148L;

    public AlreadyExistException(Object entity) {
        super(BusinessExceptionEnum.ALREADY_EXISTS, entity.toString());
    }

}
