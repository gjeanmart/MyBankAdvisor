package com.gjeanmart.mybankadvisor.core.dao.impl;

import com.gjeanmart.mybankadvisor.core.dao.CustomDao;
import com.gjeanmart.mybankadvisor.core.model.config.Account;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class AccountDaoImpl extends CustomDaoImpl<Account, Long> implements CustomDao<Account, Long> {

    public AccountDaoImpl() {
        super(Account.class);
    }


}
