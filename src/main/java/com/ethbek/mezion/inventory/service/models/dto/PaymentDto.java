package com.ethbek.mezion.inventory.service.models.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class PaymentDto implements Serializable {
    private static final long serialVersionUID = 2166984451L;
    private Long paymentHistoryId;
    private Double amount;
    private Date paymentTime;
    private CurrencyDto currency;
    private PaymentTypeDto paymentType;
    private CustomerDto customer;
}
