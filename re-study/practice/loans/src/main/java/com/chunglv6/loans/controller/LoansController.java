package com.chunglv6.loans.controller;

import com.chunglv6.loans.config.ApplicationProperties;
import com.chunglv6.loans.model.Loans;
import com.chunglv6.loans.service.LoansService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/")
@Slf4j
public class LoansController {
//    public static final Logger log = LoggerFactory.getLogger(LoansController.class);
    @Autowired
    private LoansService loansService;
    @Autowired
    private ApplicationProperties applicationProperties;

    @GetMapping("/{customerId}/loans")
    public List<Loans> getLoans(@PathVariable Long customerId,@RequestHeader(value = "chunglv6-correlation-id",required = false) String correlationid) {
        log.info("findLoansByCustomerIdAndStartDtDesc() started");
        List<Loans> loansByCustomerIdAndStartDtDesc = loansService.findLoansByCustomerIdAndStartDtDesc(customerId);
        log.info("findLoansByCustomerIdAndStartDtDesc() end");
        return loansByCustomerIdAndStartDtDesc;
    }

    @GetMapping("/loans/properties")
    public String getProperties() throws JsonProcessingException {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        ApplicationProperties properties = new ApplicationProperties(applicationProperties.getMsg(), applicationProperties.getBuildVersion(), applicationProperties.getMailDetails(), applicationProperties.getActiveBranches());
        return ow.writeValueAsString(properties);
    }
}
