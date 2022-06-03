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
public class PumpDto implements Serializable {
    private static final long serialVersionUID = 73739832653L;
    private String description;
    private String type;
    private String tank;
    private Integer sides;
}
