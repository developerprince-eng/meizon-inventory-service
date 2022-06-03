package com.ethbek.mezion.inventory.service.models.dto;

import com.ethbek.mezion.inventory.service.models.entites.AccountStatus;
import lombok.Data;

import java.io.Serializable;

@Data
public class AccountsDto implements Serializable {
    private static final long serialVersionUID = 73739832653L;
    private CustomerDto customer;
    private AccountTypeDto accountType;
    private LocationDto location;
    private AccountStatus status;
}
