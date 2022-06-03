package com.ethbek.mezion.inventory.service.models.entites;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Table(name="product_type")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@ApiModel(value = "Product Type")
@Schema(name = "Product Type",description = "Fuel Product Type")
public class ProductType implements Serializable {
    private static final long serialVersionUID = 73739832653L;

    @Id
    @JsonIgnore
    @Column(name= "type_id",columnDefinition = "varchar(32) NOT NULL")
    private String typeId;

    @ApiModelProperty(notes = "Product Type", example = "Diesel 40")
    @Schema(description = "Product Type", example = "Diesel 40")
    @Column(name = "descriptor_type",columnDefinition = "varchar(16)")
    private String type;
}
