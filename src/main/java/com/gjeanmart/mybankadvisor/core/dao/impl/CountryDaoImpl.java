package com.gjeanmart.mybankadvisor.core.dao.impl;

import com.gjeanmart.mybankadvisor.core.dao.AbstractDao;
import com.gjeanmart.mybankadvisor.core.dao.CustomDao;
import com.gjeanmart.mybankadvisor.core.model.reference.Country;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class CountryDaoImpl extends CustomDaoImpl<Country, String> implements CustomDao<Country, String> {

    public CountryDaoImpl() {
        super(Country.class);
    }


}
