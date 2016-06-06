package com.gjeanmart.mybankadvisor.core.webservice.impl;

import com.gjeanmart.mybankadvisor.core.exception.BusinessException;
import com.gjeanmart.mybankadvisor.core.exception.TechnicalException;
import com.gjeanmart.mybankadvisor.core.model.config.Account;
import com.gjeanmart.mybankadvisor.core.model.reference.Country;
import com.gjeanmart.mybankadvisor.core.service.AccountService;
import com.gjeanmart.mybankadvisor.core.service.CountryService;
import com.gjeanmart.mybankadvisor.core.webservice.AccountRestService;
import com.gjeanmart.mybankadvisor.core.webservice.CountryRestService;
import org.keycloak.KeycloakPrincipal;
import org.keycloak.adapters.RefreshableKeycloakSecurityContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountRestServiceImpl extends AbstractRestServiceImpl<Account, Long> implements AccountRestService {
    private static final Logger LOGGER = LoggerFactory.getLogger(AccountRestServiceImpl.class);


    @Autowired
    private AccountService service;

    @Autowired
    public AccountRestServiceImpl(AccountService service) {
        super(service);
        this.service = service;
    }

}
