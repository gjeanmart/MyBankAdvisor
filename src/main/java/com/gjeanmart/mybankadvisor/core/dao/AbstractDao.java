package com.gjeanmart.mybankadvisor.core.dao;

import com.gjeanmart.mybankadvisor.core.model.utils.BaseModel;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.io.Serializable;

@NoRepositoryBean
public abstract interface AbstractDao<T extends BaseModel, ID extends Serializable> extends CustomDao<T, ID>, PagingAndSortingRepository<T, ID> {
}
