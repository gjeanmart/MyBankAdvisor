package com.gjeanmart.mybankadvisor.core.exception;


import com.gjeanmart.mybankadvisor.core.exception.business.AlreadyExistException;
import com.gjeanmart.mybankadvisor.core.exception.business.DoNotExistException;
import com.gjeanmart.mybankadvisor.core.exception.business.NotAuthorizedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@ControllerAdvice
public class ApiExceptionHandler extends RequestMappingHandlerMapping {
    private static final Logger LOGGER = LoggerFactory.getLogger(ApiExceptionHandler.class);

    @ExceptionHandler(DoNotExistException.class)
    public @ResponseStatus(HttpStatus.NOT_FOUND) @ResponseBody
    ApiException DoNotExistExceptionHandler(DoNotExistException doNotExistException, HttpServletResponse response, HttpServletRequest request){
        LOGGER.error("Error while requesting URL '"+request.getRequestURL().toString()+"'", doNotExistException);

        ApiException apiException = new ApiException();
        apiException.setHttpCode(HttpStatus.NOT_FOUND.value());
        apiException.setHttpStatus(HttpStatus.NOT_FOUND.name());
        apiException.setMessage(doNotExistException.getMessage());
        apiException.setUrl(request.getRequestURL().toString());
        apiException.setInternalErrorCode(doNotExistException.getBusinessExceptionEnum().getCode());

        return apiException;
    }

    @ExceptionHandler(AlreadyExistException.class)
    public @ResponseStatus(HttpStatus.CONFLICT) @ResponseBody
    ApiException AlreadyExistExceptionHandler(AlreadyExistException alreadyExistException, HttpServletResponse response, HttpServletRequest request){
        LOGGER.error("Error while requesting URL '"+request.getRequestURL().toString()+"'", alreadyExistException);

        ApiException apiException = new ApiException();
        apiException.setHttpCode(HttpStatus.CONFLICT.value());
        apiException.setHttpStatus(HttpStatus.CONFLICT.name());
        apiException.setMessage(alreadyExistException.getMessage());
        apiException.setUrl(request.getRequestURL().toString());
        apiException.setInternalErrorCode(alreadyExistException.getBusinessExceptionEnum().getCode());

        return apiException;
    }

    @ExceptionHandler(NotAuthorizedException.class)
    public @ResponseStatus(HttpStatus.FORBIDDEN) @ResponseBody
    ApiException NotAuthorizedExceptionHandler(NotAuthorizedException notAuthorizedException, HttpServletResponse response, HttpServletRequest request){
        LOGGER.error("Error while requesting URL '"+request.getRequestURL().toString()+"'", notAuthorizedException);

        ApiException apiException = new ApiException();
        apiException.setHttpCode(HttpStatus.FORBIDDEN.value());
        apiException.setHttpStatus(HttpStatus.FORBIDDEN.name());
        apiException.setMessage(notAuthorizedException.getMessage());
        apiException.setUrl(request.getRequestURL().toString());
        apiException.setInternalErrorCode(notAuthorizedException.getBusinessExceptionEnum().getCode());

        return apiException;
    }

    @ExceptionHandler(BusinessException.class)
    public @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR) @ResponseBody
    ApiException BusinessExceptionHandler(BusinessException businessException, HttpServletResponse response, HttpServletRequest request){
        LOGGER.error("Error while requesting URL '"+request.getRequestURL().toString()+"'", businessException);

        ApiException apiException = new ApiException();
        apiException.setHttpCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        apiException.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR.name());
        apiException.setMessage(businessException.getMessage());
        apiException.setUrl(request.getRequestURL().toString());
        apiException.setInternalErrorCode(businessException.getBusinessExceptionEnum().getCode());

        return apiException;
    }

    @ExceptionHandler(TechnicalException.class)
    public @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR) @ResponseBody
    ApiException TechnicalExceptionHandler(TechnicalException technicalException, HttpServletResponse response, HttpServletRequest request){
        LOGGER.error("Error while requesting URL '"+request.getRequestURL().toString()+"'", technicalException);

        ApiException apiException = new ApiException();
        apiException.setHttpCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        apiException.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR.name());
        apiException.setMessage(technicalException.getMessage());
        apiException.setUrl(request.getRequestURL().toString());
        apiException.setInternalErrorCode(technicalException.getTechnicalException().getCode());

        return apiException;
    }


    @ExceptionHandler(Exception.class)
    public @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR) @ResponseBody
    ApiException ExceptionHandler(Exception ex, HttpServletResponse response, HttpServletRequest request){
        LOGGER.error("Error while requesting URL '"+request.getRequestURL().toString()+"'", ex);

        ApiException apiException = new ApiException();
        apiException.setHttpCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        apiException.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR.name());
        apiException.setMessage(String.format(TechnicalExceptionEnum.UNEXPECTED.getMessage(), ex.getMessage()));
        apiException.setUrl(request.getRequestURL().toString());
        apiException.setInternalErrorCode(TechnicalExceptionEnum.UNEXPECTED.getCode());

        return apiException;
    }

}