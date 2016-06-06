package com.gjeanmart.mybankadvisor.core.service.impl;


import com.gjeanmart.mybankadvisor.core.dao.UserDao;
import com.gjeanmart.mybankadvisor.core.model.security.User;
import com.gjeanmart.mybankadvisor.core.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends AbstractServiceImpl<User,Long> implements UserService {

    private UserDao userDao;

    @Autowired
    public UserServiceImpl(UserDao userDao) {
        super(User.class, userDao);
        this.userDao = userDao;
    }
}
