package com.chunglv6.loans.service;

import com.chunglv6.loans.model.Loans;
import com.chunglv6.loans.repository.LoansRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public interface LoansService {
    List<Loans> findLoansByCustomerIdAndStartDtDesc(Long customerId);
}
