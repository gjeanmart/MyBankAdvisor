package com.gjeanmart.mybankadvisor.core.dao;

import com.gjeanmart.mybankadvisor.core.model.utils.BaseModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;

public interface CustomDao<T extends BaseModel, ID extends Serializable> {

    Page<T> findAll(String search, Pageable pageable, String... field);
}