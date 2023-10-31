package com.chunglv6.accounts.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cards {


    private Long cardId;


    private String cardNumber;

    private Long customerId;

    private String cardType;

    private Long totalLimit;

    private Long amountUsed;

    private Long availableAmount;

    private Date createDt;

}
