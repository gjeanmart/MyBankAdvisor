package com.gjeanmart.mybankadvisor.core.webservice;

import com.gjeanmart.mybankadvisor.core.exception.BusinessException;
import com.gjeanmart.mybankadvisor.core.exception.TechnicalException;
import com.gjeanmart.mybankadvisor.core.model.config.Account;
import com.gjeanmart.mybankadvisor.core.model.reference.Country;
import org.keycloak.KeycloakPrincipal;
import org.keycloak.adapters.RefreshableKeycloakSecurityContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;

@RequestMapping("/api/v1/account")
public interface AccountRestService extends AbstractRestService<Account, Long>{


}
