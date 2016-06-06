package com.gjeanmart.mybankadvisor.core.webservice;

import com.gjeanmart.mybankadvisor.core.exception.BusinessException;
import com.gjeanmart.mybankadvisor.core.exception.TechnicalException;
import com.gjeanmart.mybankadvisor.core.model.security.User;
import org.keycloak.KeycloakPrincipal;
import org.keycloak.adapters.RefreshableKeycloakSecurityContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RequestMapping("/api/v1/user")
public interface UserRestService extends AbstractRestService<User, Long>{

    @Override
    @RequestMapping(value="/", method= RequestMethod.POST, consumes={MediaType.APPLICATION_JSON_VALUE})
    public @ResponseStatus(HttpStatus.CREATED) @ResponseBody
    User add(KeycloakPrincipal<RefreshableKeycloakSecurityContext> principal, @RequestBody User obj) throws BusinessException, TechnicalException;

}
