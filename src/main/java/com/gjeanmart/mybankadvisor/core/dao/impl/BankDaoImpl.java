package com.gjeanmart.mybankadvisor.core.dao.impl;

import com.gjeanmart.mybankadvisor.core.dao.CustomDao;
import com.gjeanmart.mybankadvisor.core.model.reference.Bank;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class BankDaoImpl extends CustomDaoImpl<Bank, Long> implements CustomDao<Bank, Long> {

    public BankDaoImpl() {
        super(Bank.class);
    }


}
