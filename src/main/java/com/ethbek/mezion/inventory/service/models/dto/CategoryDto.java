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
@ApiModel(value = "Category")
@Schema(name = "Category",description = "Category request to add/edit")
public class CategoryDto implements Serializable {
    private static final long serialVersionUID = 73739832653L;

    @ApiModelProperty(notes = "name", example = "FUEL")
    @Schema(description = "name", example = "FUEL")
    private String name;


    @ApiModelProperty(notes = "description", example = "Energy Sector")
    @Schema(description = "description", example = "Energy Sector")
    private String description;
}
