package com.chunglv6.accounts.service;

import com.chunglv6.accounts.model.Accounts;
import com.chunglv6.accounts.repository.AccountsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountsRepository accountsRepository;

    @Override
    public Accounts findByCustomerId(Long customerId) {
        return accountsRepository.findByCustomerId(customerId);
    }
}
