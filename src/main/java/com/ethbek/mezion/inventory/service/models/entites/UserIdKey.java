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
public class UserIdKey implements Serializable {
    private static final long serialVersionUID = 73739832653L;
    private String email;
    private String locationId;
    private Long organisationalId;
}
