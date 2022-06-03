package com.ethbek.mezion.inventory.service.models.avro;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class UserDto {
    private String emailAddress;
    private String employeeId;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private Boolean enabled;
    private String action;
    private List<RoleMap> role;
}
