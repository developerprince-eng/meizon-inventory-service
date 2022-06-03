package com.ethbek.mezion.inventory.service.models.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel(value = "Product")
@Schema(name = "Product",description = "Product request to add/edit location")
public class ProductDto implements Serializable {
    private static final long serialVersionUID = 73739832653L;

    @ApiModelProperty(notes = "product name", example = "PETROL 45")
    @Schema(description = "product name", example = "PETROL 45")
    private String name;

    @ApiModelProperty(notes = "product description", example = "Blend of uptp 45 percent ethanol")
    @Schema(description = "product description", example = "Blend of uptp 45 percent ethanol")
    private String description;

    @ApiModelProperty(notes = "product type", example = "PETROL")
    @Schema(description = "product type", example = "PETROL")
    private String type;

    @ApiModelProperty(notes = "product category", example = "FUEL")
    @Schema(description = "product category", example = "FUEL")
    private String category;
}
