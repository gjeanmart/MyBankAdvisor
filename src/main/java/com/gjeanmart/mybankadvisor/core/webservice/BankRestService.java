package com.gjeanmart.mybankadvisor.core.webservice;

import com.gjeanmart.mybankadvisor.core.model.reference.Bank;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/api/v1/bank")
public interface BankRestService extends AbstractRestService<Bank, Long>{


}
