package com.gjeanmart.mybankadvisor.core.service.impl;


import com.gjeanmart.mybankadvisor.core.dao.AccountDao;
import com.gjeanmart.mybankadvisor.core.dao.UserDao;
import com.gjeanmart.mybankadvisor.core.model.config.Account;
import com.gjeanmart.mybankadvisor.core.model.security.User;
import com.gjeanmart.mybankadvisor.core.service.AccountService;
import com.gjeanmart.mybankadvisor.core.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl extends AbstractServiceImpl<Account, Long> implements AccountService {

    private AccountDao dao;

    @Autowired
    public AccountServiceImpl(AccountDao dao) {
        super(Account.class, dao);
        this.dao = dao;
    }
}
