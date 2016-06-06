package com.gjeanmart.mybankadvisor.core.webservice.impl;

import com.gjeanmart.mybankadvisor.core.exception.BusinessException;
import com.gjeanmart.mybankadvisor.core.exception.TechnicalException;
import com.gjeanmart.mybankadvisor.core.service.AbstractService;
import com.gjeanmart.mybankadvisor.core.model.utils.BaseModel;
import com.gjeanmart.mybankadvisor.core.webservice.AbstractRestService;
import com.gjeanmart.mybankadvisor.security.config.Authorization;
import com.gjeanmart.mybankadvisor.security.model.RoleEnum;
import org.keycloak.KeycloakPrincipal;
import org.keycloak.adapters.RefreshableKeycloakSecurityContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;

public abstract class AbstractRestServiceImpl<T extends BaseModel, ID extends Serializable> implements AbstractRestService<T, ID> {
    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractRestServiceImpl.class);

    protected AbstractService<T, ID> service;

    public AbstractRestServiceImpl(AbstractService<T, ID> service) {
        this.service = service;
    }

    @Override
    public T add(KeycloakPrincipal<RefreshableKeycloakSecurityContext> principal, @RequestBody T obj) throws BusinessException, TechnicalException {
        Authorization.isAuthorized(principal, RoleEnum.MBA_ADMIN);

        return service.add(principal.getKeycloakSecurityContext().getToken().getPreferredUsername(), obj);
    }

    @Override
    public T modify(KeycloakPrincipal<RefreshableKeycloakSecurityContext> principal, @PathVariable ID id, @RequestBody T obj) throws BusinessException, TechnicalException {
        return service.modify(principal.getKeycloakSecurityContext().getToken().getPreferredUsername(), obj);
    }

    @Override
    public void delete(KeycloakPrincipal<RefreshableKeycloakSecurityContext> principal, @PathVariable ID id) throws BusinessException, TechnicalException {
        service.delete(principal.getKeycloakSecurityContext().getToken().getPreferredUsername(), id);
    }

    @Override
    public T get(KeycloakPrincipal<RefreshableKeycloakSecurityContext> principal, @PathVariable ID id) throws BusinessException, TechnicalException {
        return service.get(principal.getKeycloakSecurityContext().getToken().getPreferredUsername(), id);
    }

    @Override
    public Page<T> searchByFilters(KeycloakPrincipal<RefreshableKeycloakSecurityContext> principal, @RequestParam(value = "page", defaultValue = "1", required = false) int pageNo, @RequestParam(value = "size", defaultValue = "20", required = false) int pageSize, @RequestParam(value = "sort", defaultValue = "id", required = false) String sortAttribute, @RequestParam(value = "dir", defaultValue = "ASC", required = false) Sort.Direction sortDirection, T filterObject, @RequestParam(value = "filter", required = false) String query) throws BusinessException, TechnicalException {

        PageRequest pagination = new PageRequest(pageNo-1, pageSize, sortDirection, sortAttribute);

        if(query == null || query.isEmpty()) {
            return service.search(principal.getKeycloakSecurityContext().getToken().getPreferredUsername(), filterObject, pagination);
        } else {
            return service.search(principal.getKeycloakSecurityContext().getToken().getPreferredUsername(), query, pagination);
        }
    }



}
