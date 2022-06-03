package com.ethbek.mezion.inventory.service.models.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class UserDto implements Serializable {
    private static final long serialVersionUID = 73739832653L;

    private String userId;

    private String email;

    private String firstName;

    private String lastName;

    private String mobile;

    private String street;

    private String unitNumber;

    private String locationId;

    private Long organisationId;

}
