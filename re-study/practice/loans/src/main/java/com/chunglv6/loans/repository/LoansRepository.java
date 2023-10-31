package com.chunglv6.loans.repository;

import com.chunglv6.loans.model.Loans;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoansRepository extends JpaRepository<Loans,Long> {
    List<Loans> findByCustomerIdOrderByStartDtDesc(Long customerId);
}
