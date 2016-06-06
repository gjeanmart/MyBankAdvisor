package com.gjeanmart.mybankadvisor.core.service;

import com.gjeanmart.mybankadvisor.core.exception.BusinessException;
import com.gjeanmart.mybankadvisor.core.exception.TechnicalException;
import com.gjeanmart.mybankadvisor.core.model.reference.Country;

public interface CountryService extends AbstractService<Country, String> {

    public void synchronizedCountries(String username) throws BusinessException, TechnicalException;
}
