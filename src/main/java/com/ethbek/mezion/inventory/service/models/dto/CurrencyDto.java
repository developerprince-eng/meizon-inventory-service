package com.ethbek.mezion.inventory.service.models.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class CurrencyDto implements Serializable {
    private static final long serialVersionUID = 2166984451L;
    private String name;
}
