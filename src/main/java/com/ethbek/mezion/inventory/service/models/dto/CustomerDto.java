package com.ethbek.mezion.inventory.service.models.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;



@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerDto implements Serializable {
    private static final long serialVersionUID = 73739832653L;
    private String firstName;
    private String lastName;
    private String companyName;
    private String region;
    private String city;
    private AddressDto address;
    private List<ContactDto> contacts;
}
