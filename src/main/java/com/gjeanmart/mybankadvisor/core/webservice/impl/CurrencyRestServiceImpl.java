package com.gjeanmart.mybankadvisor.core.webservice.impl;

import com.gjeanmart.mybankadvisor.core.exception.BusinessException;
import com.gjeanmart.mybankadvisor.core.exception.TechnicalException;
import com.gjeanmart.mybankadvisor.core.model.reference.Currency;
import com.gjeanmart.mybankadvisor.core.service.CurrencyService;
import com.gjeanmart.mybankadvisor.core.webservice.CurrencyRestService;
import org.keycloak.KeycloakPrincipal;
import org.keycloak.adapters.RefreshableKeycloakSecurityContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CurrencyRestServiceImpl extends AbstractRestServiceImpl<Currency, String> implements CurrencyRestService {

    @Autowired
    private CurrencyService currencyService;

    @Autowired
    public CurrencyRestServiceImpl(CurrencyService currencyService) {
        super(currencyService);
        this.currencyService = currencyService;
    }

    @Override
    public void sync(KeycloakPrincipal<RefreshableKeycloakSecurityContext> principal) throws BusinessException, TechnicalException {

        currencyService.synchronizedCurrency(principal.getKeycloakSecurityContext().getToken().getPreferredUsername());
    }
}
