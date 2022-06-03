package com.ethbek.mezion.inventory.service.models.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
public class UserRoleDto implements Serializable {
    private static final long serialVersionUID = 73739832653L;
    private String name;
    private List<UserPermissionDto> permissions = new ArrayList<>();
}
