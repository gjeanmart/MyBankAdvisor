package com.gjeanmart.mybankadvisor.core.service.impl;


import com.gjeanmart.mybankadvisor.core.dao.BankDao;
import com.gjeanmart.mybankadvisor.core.model.reference.Bank;
import com.gjeanmart.mybankadvisor.core.service.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BankServiceImpl extends AbstractServiceImpl<Bank, Long> implements BankService {

    private BankDao dao;

    @Autowired
    public BankServiceImpl(BankDao dao) {
        super(Bank.class, dao);
        this.dao = dao;
    }
}
