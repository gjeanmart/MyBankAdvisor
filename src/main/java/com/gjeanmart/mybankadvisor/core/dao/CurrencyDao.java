package com.gjeanmart.mybankadvisor.core.dao;

import com.gjeanmart.mybankadvisor.core.model.reference.Currency;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface CurrencyDao extends AbstractDao<Currency, String> {
    Page<Currency> findByCodeContainingIgnoreCaseAndDescriptionContainingIgnoreCase(String code, String description, Pageable pageable);
    Page<Currency> findByDescriptionContainingIgnoreCase(String description, Pageable pageable);
    Page<Currency> findByCodeContainingIgnoreCase(String code, Pageable pageable);
}
