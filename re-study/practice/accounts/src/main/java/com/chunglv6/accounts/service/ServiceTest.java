package com.chunglv6.accounts.service;

import com.chunglv6.accounts.controller.AccountsController;
import com.chunglv6.accounts.model.Accounts;
import com.chunglv6.accounts.model.Cards;
import com.chunglv6.accounts.model.CustomerDetails;
import com.chunglv6.accounts.model.Loans;
import com.chunglv6.accounts.service.client.CardsClient;
import com.chunglv6.accounts.service.client.LoansClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceTest {
    public static final Logger log = LoggerFactory.getLogger(ServiceTest.class);

    @Autowired
    private AccountServiceImpl accountService;

    @Autowired
    private LoansClient loansClient;

    @Autowired
    private CardsClient cardsClient;

    public CustomerDetails getCustomerDetails(Long customerId,String correlationid){
        log.info("getCustomerDetails() started");
        Accounts accounts = accountService.findByCustomerId(customerId);
        List<Loans> loansList = loansClient.getLoansByCustomerId(customerId,correlationid);
        List<Cards> cardsList = cardsClient.getCardsBycustomerId(customerId,correlationid);

        CustomerDetails customerDetails = new CustomerDetails();
        customerDetails.setAccounts(accounts);
        customerDetails.setCards(cardsList);
        customerDetails.setLoans(loansList);
        log.info("getCustomerDetails() end");
        return customerDetails;
    }

    public CustomerDetails getCustomerDetailsFallBack(Long customerId,String correlationid){
        log.info("getCustomerDetailsFallBack() started");
        Accounts accounts = accountService.findByCustomerId(customerId);
        List<Loans> loansList = loansClient.getLoansByCustomerId(customerId,correlationid);
        CustomerDetails customerDetails = new CustomerDetails();
        customerDetails.setAccounts(accounts);
        customerDetails.setLoans(loansList);
        log.info("getCustomerDetailsFallBack() end");
        return customerDetails;
    }
}
