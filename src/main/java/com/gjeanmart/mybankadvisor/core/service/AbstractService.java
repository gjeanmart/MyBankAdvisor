package com.gjeanmart.mybankadvisor.core.service;

import com.gjeanmart.mybankadvisor.core.exception.BusinessException;
import com.gjeanmart.mybankadvisor.core.exception.TechnicalException;
import com.gjeanmart.mybankadvisor.core.model.utils.BaseModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.persistence.criteria.Predicate;
import java.io.Serializable;
import java.util.Map;

/**
 * Created by Grégoire JEANMART on 2016-04-29.
 */
public abstract interface AbstractService<T extends BaseModel, ID extends Serializable> {

    T add(String username, T obj) throws BusinessException, TechnicalException;
    T modify(String username, T obj) throws BusinessException, TechnicalException;
    void delete(String username, ID id) throws BusinessException, TechnicalException;
    T get(String username, ID id) throws BusinessException, TechnicalException;
    Page<T> search(String username, T filter, Pageable pagination) throws BusinessException, TechnicalException;
    Page<T> search(String username, String filter, Pageable pagination) throws BusinessException, TechnicalException;
    Page<T> search(String username, Pageable pagination) throws BusinessException, TechnicalException;
}
