package com.ethbek.mezion.inventory.service.models.entites;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Table(name="address")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@ApiModel(value = "Address")
@Schema(name = "Address",description = "Address Details for Entities in Meizon")
public class Address implements Serializable {
    private static final long serialVersionUID = 73739832653L;

    @Id
    @JsonIgnore
    @Column(name= "address_id",columnDefinition = "varchar(32) NOT NULL")
    private String addressId;

    @Column(name = "street",columnDefinition = "varchar(255)")
    @ApiModelProperty(notes = "Street", example = "Spooner Street")
    @Schema(description = "Street", example = "Spooner Street")
    private String street;

    @Column(name = "unit_number", columnDefinition = "varchar(255)")
    @ApiModelProperty(notes = "Unit Number", example = "31")
    @Schema(description = "Unit Number", example = "31")
    private String unitNumber;

}
