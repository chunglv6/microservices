package com.chunglv6.accounts.controller;

import com.chunglv6.accounts.config.ApplicationProperties;
import com.chunglv6.accounts.model.Accounts;
import com.chunglv6.accounts.model.Cards;
import com.chunglv6.accounts.model.CustomerDetails;
import com.chunglv6.accounts.model.Loans;
import com.chunglv6.accounts.service.AccountService;
import com.chunglv6.accounts.service.ServiceTest;
import com.chunglv6.accounts.service.client.CardsClient;
import com.chunglv6.accounts.service.client.LoansClient;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Properties;


@RestController
@RequestMapping("/api/v1/")
public class AccountsController {
    public static final Logger log = LoggerFactory.getLogger(AccountsController.class);
    @Autowired
    private AccountService accountService;

    @Autowired
    private ApplicationProperties applicationProperties;

    @Autowired
    private LoansClient loansClient;

    @Autowired
    private ServiceTest serviceTest;

    @Autowired
    private CardsClient cardsClient;

    @GetMapping("/{customerId}/accounts")
    public Accounts getAccountsByCustomerId(@PathVariable Long customerId) {
        return accountService.findByCustomerId(customerId);
    }

    @GetMapping("/accounts/properties")
    public String getProperties() throws JsonProcessingException {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        ApplicationProperties properties = new ApplicationProperties(applicationProperties.getMsg(), applicationProperties.getBuildVersion(), applicationProperties.getMailDetails(), applicationProperties.getActiveBranches());
        return ow.writeValueAsString(properties);
    }

    @GetMapping("/{customerId}/details")
    @CircuitBreaker(name = "getCustomerById", fallbackMethod = "getCustomerByIdFallback")
//    @Retry(name = "f1orDetailsCustomer",fallbackMethod = "getCustomerByIdFallback")
    public CustomerDetails getCustomerById(@PathVariable Long customerId, @RequestHeader(value = "chunglv6-correlation-id", required = false) String correlationid) {

        return serviceTest.getCustomerDetails(customerId, correlationid);
    }

    private CustomerDetails getCustomerByIdFallback(Long customerId, String correlationid, Throwable t) {
        return serviceTest.getCustomerDetailsFallBack(customerId, correlationid);
    }

    @GetMapping("/white")
    public String helloTho(@RequestHeader(value = "chunglv6-correlation-id", required = false) String correlationid) {
        return "xin chao tho trang";
    }

    @GetMapping("/sayHello")
    @RateLimiter(name = "sayHello", fallbackMethod = "sayHelloFallback")
    public String sayHello() {
        return "Hello, Welcome to EazyBank";
    }

    private String sayHelloFallback(Throwable t) {
        return "Hi, Welcome to EazyBank";
    }
}
