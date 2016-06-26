package com.gjeanmart.mybankadvisor.core.webservice.impl;

import com.gjeanmart.mybankadvisor.core.model.reference.Bank;
import com.gjeanmart.mybankadvisor.core.service.BankService;
import com.gjeanmart.mybankadvisor.core.webservice.BankRestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BankRestServiceImpl extends AbstractRestServiceImpl<Bank, Long> implements BankRestService {
    private static final Logger LOGGER = LoggerFactory.getLogger(BankRestServiceImpl.class);


    @Autowired
    private BankService service;

    @Autowired
    public BankRestServiceImpl(BankService service) {
        super(service);
        this.service = service;
    }

}
