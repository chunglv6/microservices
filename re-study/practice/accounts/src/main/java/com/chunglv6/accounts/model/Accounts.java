package com.chunglv6.accounts.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Data
@Entity(name = "accounts")
@AllArgsConstructor
@NoArgsConstructor
public class Accounts {
    @Column(name = "customer_id")
   private Long customerId;

    @Column(name = "account_number")
    @Id
    private Long accountNumber;

    @Column(name = "account_type")
    private String accountType;

    @Column(name = "branch_address")
    private String branch_Address;

    @Column(name = "create_dt")
    private Date createDt;

}
