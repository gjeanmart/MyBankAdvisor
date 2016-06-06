package com.gjeanmart.mybankadvisor.core.service.impl;


import com.gjeanmart.mybankadvisor.core.dao.CurrencyDao;
import com.gjeanmart.mybankadvisor.core.exception.BusinessException;
import com.gjeanmart.mybankadvisor.core.exception.TechnicalException;
import com.gjeanmart.mybankadvisor.core.model.reference.Currency;
import com.gjeanmart.mybankadvisor.core.service.CurrencyService;
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
import java.util.Map;

@Service
public class CurrencyServiceImpl extends AbstractServiceImpl<Currency, String> implements CurrencyService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CurrencyServiceImpl.class);

    @Value("${services.currency.url}")
    private String currencyServiceUrl;

    private CurrencyDao currencyDao;

    @Autowired
    public CurrencyServiceImpl(CurrencyDao currencyDao) {
        super(Currency.class, currencyDao, Currency.SEARCH_FIELDS);
        this.currencyDao = currencyDao;
    }

    @Override
    public void synchronizedCurrency(String username) throws BusinessException, TechnicalException {
        LOGGER.debug("start synchronizedCurrency");

        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<Map<String, String>> resp = restTemplate.exchange(currencyServiceUrl, HttpMethod.GET, null, new ParameterizedTypeReference<Map<String, String>>() {});
        Map<String, String> map = resp.getBody();

        LOGGER.debug(map.toString());

        for(String key : map.keySet()) {
            Currency c = new Currency();
            c.setCode(key);
            c.setDescription(map.get(key));

            c.setAddedBy(username);
            c.setAddedDate(new Date());
            c.setLastModifiedBy(username);
            c.setLastModifiedDate(new Date());

            currencyDao.save(c);
        }

        LOGGER.debug("end synchronizedCurrency");
    }

    @Override
    public Page<Currency> search(String username, Currency filter, Pageable pagination) throws BusinessException, TechnicalException {
        if(filter == null || (filter.getCode() == null || filter.getCode().isEmpty()) && (filter.getDescription() == null || filter.getDescription().isEmpty()) ) {
            return currencyDao.findAll(pagination);
        } else {
            if(filter.getDescription() == null || filter.getDescription().isEmpty()) {
                return currencyDao.findByCodeContainingIgnoreCase(filter.getCode(), pagination);
            } else if(filter.getCode() == null || filter.getCode().isEmpty()) {
                return currencyDao.findByDescriptionContainingIgnoreCase(filter.getDescription(), pagination);
            } else {
                return currencyDao.findByCodeContainingIgnoreCaseAndDescriptionContainingIgnoreCase(filter.getCode(), filter.getDescription(), pagination);
            }
        }
    }
}
