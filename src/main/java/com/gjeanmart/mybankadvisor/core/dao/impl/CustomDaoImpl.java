package com.gjeanmart.mybankadvisor.core.dao.impl;

import com.gjeanmart.mybankadvisor.core.dao.AbstractDao;
import com.gjeanmart.mybankadvisor.core.dao.CustomDao;
import com.gjeanmart.mybankadvisor.core.model.utils.BaseModel;
import org.apache.lucene.search.Sort;
import org.apache.lucene.search.SortField;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;
import java.util.List;

public abstract class CustomDaoImpl<T extends BaseModel, ID extends Serializable>  implements CustomDao<T, ID> {
    private static final Logger LOGGER = LoggerFactory.getLogger(CustomDaoImpl.class);

    @PersistenceContext
    private EntityManager entityManager;

    private Class<T> className;

    public CustomDaoImpl(Class<T> className) {
        this.className = className;
    }

    @Override
    public Page<T> findAll(String search, Pageable pageable, String... fields) {
        // get the full text entity manager
        FullTextEntityManager fullTextEntityManager =  org.hibernate.search.jpa.Search.getFullTextEntityManager(entityManager);

        // create the query using Hibernate Search query DSL
        QueryBuilder queryBuilder =  fullTextEntityManager.getSearchFactory().buildQueryBuilder().forEntity(className).get();

        // a very basic query by keywords
        org.apache.lucene.search.Query query =
                queryBuilder
                        .keyword()
                        .onFields(fields)
                        .matching(search)
                        .createQuery();

        // wrap Lucene query in an Hibernate Query object
        org.hibernate.search.jpa.FullTextQuery jpaQuery =  fullTextEntityManager.createFullTextQuery(query, className);

        // Pagination
        jpaQuery.setFirstResult(pageable.getOffset());
        jpaQuery.setMaxResults(pageable.getPageSize());

        // Order
        Sort sort = new Sort(
                new SortField(
                        pageable.getSort().iterator().next().getProperty(),
                        SortField.Type.STRING,
                        pageable.getSort().iterator().next().getDirection().equals(org.springframework.data.domain.Sort.Direction.DESC)));
        jpaQuery.setSort(sort);

        // execute search and return results (sorted by relevance as default)
        @SuppressWarnings("unchecked")
        List<T> results = jpaQuery.getResultList();

        return new PageImpl(results);
    }
}
