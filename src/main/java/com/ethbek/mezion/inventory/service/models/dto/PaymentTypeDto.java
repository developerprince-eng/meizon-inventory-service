package com.ethbek.mezion.inventory.service.models.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class PaymentTypeDto implements Serializable {
    private static final long serialVersionUID = 2166984451L;
    private Long typeId;
    private String type;
}
