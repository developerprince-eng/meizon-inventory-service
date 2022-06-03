package com.ethbek.mezion.inventory.service.models.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class AccountTypeDto implements Serializable {
    private static final long serialVersionUID = 73739832653L;
    private String type;
}
