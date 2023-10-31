package com.chunglv6.accounts.service;

import com.chunglv6.accounts.model.Accounts;

public interface AccountService {
    Accounts findByCustomerId(Long customerId);
}
