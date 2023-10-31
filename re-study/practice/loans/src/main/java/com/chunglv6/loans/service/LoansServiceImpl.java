package com.chunglv6.loans.service;

import com.chunglv6.loans.model.Loans;
import com.chunglv6.loans.repository.LoansRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoansServiceImpl implements LoansService{
    @Autowired
    private LoansRepository loansRepository;
    @Override
    public List<Loans> findLoansByCustomerIdAndStartDtDesc(Long customerId) {
        return loansRepository.findByCustomerIdOrderByStartDtDesc(customerId);
    }
}
