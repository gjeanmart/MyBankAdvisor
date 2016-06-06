package com.gjeanmart.mybankadvisor.core.service.impl;


import com.gjeanmart.mybankadvisor.core.dao.CountryDao;
import com.gjeanmart.mybankadvisor.core.exception.BusinessException;
import com.gjeanmart.mybankadvisor.core.exception.TechnicalException;
import com.gjeanmart.mybankadvisor.core.model.reference.Country;
import com.gjeanmart.mybankadvisor.core.service.CountryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.List;

@Service
public class CountryServiceImpl extends AbstractServiceImpl<Country, String> implements CountryService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CountryServiceImpl.class);

    @Value("${services.country.url}")
    private String countryServiceUrl;

    private CountryDao countryDao;

    @Autowired
    public CountryServiceImpl(CountryDao countryDao) {
        super(Country.class, countryDao, Country.SEARCH_FIELDS);
        this.countryDao = countryDao;
    }

    @Override
    public void synchronizedCountries(String username) throws BusinessException, TechnicalException {
        LOGGER.debug("start synchronizedCountries");

        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<List<Country>> resp = restTemplate.exchange(countryServiceUrl, HttpMethod.GET, null, new ParameterizedTypeReference<List<Country>>() {});
        List<Country> countries = resp.getBody();

        LOGGER.debug(countries.toString());

        countryDao.deleteAll();
        for(Country country : countries) {

            country.setAddedBy(username);
            country.setAddedDate(new Date());
            country.setLastModifiedBy(username);
            country.setLastModifiedDate(new Date());

            countryDao.save(country);
        }

        LOGGER.debug("end synchronizedCountries");
    }


    @Override
    public Page<Country> search(String username, Country filter, Pageable pagination) throws BusinessException, TechnicalException {
        if(filter == null) {
            return countryDao.findAll(pagination);

        } else {
            return countryDao.findAll(pagination);
        }
    }
}
