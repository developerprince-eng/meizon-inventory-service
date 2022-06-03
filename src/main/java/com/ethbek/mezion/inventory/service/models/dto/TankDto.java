package com.ethbek.mezion.inventory.service.models.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TankDto implements Serializable {
    private static final long serialVersionUID = 2166984451L;
    private Long capacity;
    private String product;
    private String location;
    private String referenceHeight;
}
