package com.gjeanmart.mybankadvisor.core.webservice;

import com.gjeanmart.mybankadvisor.core.exception.BusinessException;
import com.gjeanmart.mybankadvisor.core.exception.TechnicalException;
import com.gjeanmart.mybankadvisor.core.model.utils.BaseModel;
import org.keycloak.KeycloakPrincipal;
import org.keycloak.adapters.RefreshableKeycloakSecurityContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;

public abstract interface AbstractRestService<T extends BaseModel, ID extends Serializable> {

    @RequestMapping(value="/", method= RequestMethod.POST, consumes={MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.CREATED) @ResponseBody T add(KeycloakPrincipal<RefreshableKeycloakSecurityContext> principal, @RequestBody T obj) throws BusinessException, TechnicalException;

    @RequestMapping(value="/{id}", method= RequestMethod.PUT, consumes={MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody T modify(KeycloakPrincipal<RefreshableKeycloakSecurityContext> principal, @PathVariable ID id, @RequestBody T obj) throws BusinessException, TechnicalException;

    @RequestMapping(value="/{id}", method= RequestMethod.DELETE, consumes={MediaType.APPLICATION_JSON_VALUE})
    void delete(KeycloakPrincipal<RefreshableKeycloakSecurityContext> principal, @PathVariable ID id) throws BusinessException, TechnicalException;

    @RequestMapping(value="/{id}", method= RequestMethod.GET, consumes={MediaType.APPLICATION_JSON_VALUE})
    T get(KeycloakPrincipal<RefreshableKeycloakSecurityContext> principal, @PathVariable ID id) throws BusinessException, TechnicalException;

    @RequestMapping(value="/", method= RequestMethod.GET, consumes={MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody Page<T> searchByFilters(
            KeycloakPrincipal<RefreshableKeycloakSecurityContext> principal,
            @RequestParam(value = "page", defaultValue = "1", required = false) int pageNo,
            @RequestParam(value = "size", defaultValue = "20", required = false) int pageSize,
            @RequestParam(value = "sort", defaultValue = "id", required = false) String sortAttribute,
            @RequestParam(value = "dir", defaultValue = "ASC", required = false) Sort.Direction sortDirection,
            T filterObject,
            @RequestParam(value = "filter", required = false) String query)
            throws BusinessException, TechnicalException;

}
