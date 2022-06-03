package com.ethbek.mezion.inventory.service.models.avro;

import lombok.Data;

@Data
public class RoleMap {
    private String roleId;
    private Long organisationalId;
    private String branchId;
}
