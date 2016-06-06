package com.gjeanmart.mybankadvisor.core.webservice;

import com.gjeanmart.mybankadvisor.core.exception.BusinessException;
import com.gjeanmart.mybankadvisor.core.exception.TechnicalException;
import com.gjeanmart.mybankadvisor.core.model.reference.Currency;
import org.keycloak.KeycloakPrincipal;
import org.keycloak.adapters.RefreshableKeycloakSecurityContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/v1/currency")
public interface CurrencyRestService extends AbstractRestService<Currency, String> {

    @RequestMapping(value="/sync", method= RequestMethod.POST, consumes={MediaType.APPLICATION_JSON_VALUE})
    public @ResponseStatus(HttpStatus.OK) void sync(KeycloakPrincipal<RefreshableKeycloakSecurityContext> principal) throws BusinessException, TechnicalException;

}
