package com.ethbek.mezion.inventory.service.models.entites;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DipSheetKey implements Serializable {
    private static final long serialVersionUID = 1L;
    private Tank tank;
    private String dipSheetId;
}

