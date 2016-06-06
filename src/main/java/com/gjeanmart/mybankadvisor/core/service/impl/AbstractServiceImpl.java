package com.gjeanmart.mybankadvisor.core.service.impl;

import com.gjeanmart.mybankadvisor.core.dao.AbstractDao;
import com.gjeanmart.mybankadvisor.core.exception.BusinessException;
import com.gjeanmart.mybankadvisor.core.exception.TechnicalException;
import com.gjeanmart.mybankadvisor.core.exception.business.DoNotExistException;
import com.gjeanmart.mybankadvisor.core.model.utils.BaseModel;
import com.gjeanmart.mybankadvisor.core.service.AbstractService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import javax.persistence.criteria.Predicate;
import java.io.Serializable;
import java.util.Date;
import java.util.Map;

public abstract class AbstractServiceImpl<T extends BaseModel, ID extends Serializable> implements AbstractService<T, ID> {
    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractServiceImpl.class);

    private Class<T> type;
    private AbstractDao<T, ID> abstractDao;
    private String[] searchFields;

    public AbstractServiceImpl(Class<T> type, AbstractDao<T, ID> abstractDao) {
        this.abstractDao = abstractDao;
        this.type = type;
    }

    public AbstractServiceImpl(Class<T> type, AbstractDao<T, ID> abstractDao, String... searchFields) {
        this.abstractDao = abstractDao;
        this.type = type;
        this.searchFields = searchFields;
    }

    public T add(String username, T obj) throws BusinessException, TechnicalException {

        obj.setAddedBy(username);
        obj.setAddedDate(new Date());
        obj.setLastModifiedBy(username);
        obj.setLastModifiedDate(new Date());

        abstractDao.save(obj);
        // obj.setId(id);

        return obj;
    }

    public T modify(String username, T obj) throws BusinessException, TechnicalException {

        obj.setLastModifiedBy(username);
        obj.setLastModifiedDate(new Date());

        /*
        if(!abstractDao.exists(obj.getId())) {
            throw new DoNotExistException(obj);
        }
        */

        abstractDao.save(obj);

        return obj;
    }

    public void delete(String username, ID id) throws BusinessException, TechnicalException {
        T obj = (T) abstractDao.findOne(id);

        if (obj == null) {
            throw new DoNotExistException(type.getName() + "[id=" + id + "]");
        }

        abstractDao.delete(obj);
    }

    public T get(String username, ID id) throws BusinessException, TechnicalException {

        if (!abstractDao.exists(id)) {
            throw new DoNotExistException(type.getName() + "[id=" + id + "]");
        }

        return (T) abstractDao.findOne(id);
    }

    public Page<T> search(String username, Pageable pagination) throws BusinessException, TechnicalException {
        return abstractDao.findAll(pagination);
    }

    public Page<T> search(String username, String filter, Pageable pagination) throws BusinessException, TechnicalException {
        if(filter == null || filter.isEmpty()) {
            return abstractDao.findAll(pagination);
        } else {
            return abstractDao.findAll(filter, pagination, searchFields);
        }
    }

    public Page<T> search(String username, T filter, Pageable pagination) throws BusinessException, TechnicalException {
        return abstractDao.findAll(pagination);
    }
}
