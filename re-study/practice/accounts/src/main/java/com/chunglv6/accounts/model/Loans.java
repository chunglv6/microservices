package com.chunglv6.accounts.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Loans {

    private Long loanNumber;

    private Long customerId;

    private Date startDt;

    private String loanType;

    private Long totalLoan;

    private Long amountPaid;

    private Long outstandingAmount;
}
