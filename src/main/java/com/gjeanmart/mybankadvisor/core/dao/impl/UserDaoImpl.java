package com.gjeanmart.mybankadvisor.core.dao.impl;

import com.gjeanmart.mybankadvisor.core.dao.AbstractDao;
import com.gjeanmart.mybankadvisor.core.dao.CustomDao;
import com.gjeanmart.mybankadvisor.core.model.security.User;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Repository
@Transactional
public class UserDaoImpl extends CustomDaoImpl<User, String> implements CustomDao<User, String> {

    public UserDaoImpl() {
        super(User.class);
    }


}
