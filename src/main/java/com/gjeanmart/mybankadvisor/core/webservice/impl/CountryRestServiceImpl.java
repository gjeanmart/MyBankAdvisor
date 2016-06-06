package com.gjeanmart.mybankadvisor.core.webservice.impl;

import com.gjeanmart.mybankadvisor.core.exception.BusinessException;
import com.gjeanmart.mybankadvisor.core.exception.TechnicalException;
import com.gjeanmart.mybankadvisor.core.model.reference.Country;
import com.gjeanmart.mybankadvisor.core.service.CountryService;
import com.gjeanmart.mybankadvisor.core.webservice.CountryRestService;
import org.keycloak.KeycloakPrincipal;
import org.keycloak.adapters.RefreshableKeycloakSecurityContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
public class CountryRestServiceImpl extends AbstractRestServiceImpl<Country, String> implements CountryRestService {
    private static final Logger LOGGER = LoggerFactory.getLogger(CountryRestServiceImpl.class);


    @Autowired
    private CountryService countryService;

    @Autowired
    public CountryRestServiceImpl(CountryService countryService) {
        super(countryService);
        this.countryService = countryService;
    }

    @Override
    public void sync(KeycloakPrincipal<RefreshableKeycloakSecurityContext> principal) throws BusinessException, TechnicalException {
        countryService.synchronizedCountries(principal.getKeycloakSecurityContext().getToken().getPreferredUsername());
    }
}
