package com.gjeanmart.mybankadvisor.core.service;

import com.gjeanmart.mybankadvisor.core.exception.BusinessException;
import com.gjeanmart.mybankadvisor.core.exception.TechnicalException;
import com.gjeanmart.mybankadvisor.core.model.reference.Currency;

public interface CurrencyService extends AbstractService<Currency, String> {

    public void synchronizedCurrency(String username) throws BusinessException, TechnicalException;
}
