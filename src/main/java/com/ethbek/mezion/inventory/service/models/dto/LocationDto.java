package com.ethbek.mezion.inventory.service.models.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
@ApiModel(value = "Location Request")
@Schema(name = "Location Request",description = "Location request to add/edit location")
public class LocationDto implements Serializable {
    private static final long serialVersionUID = 73739832653L;

    @ApiModelProperty(notes = "Location Name", example = "MZ-001")
    @Schema(description = "Location Name", example = "MZ-001")
    private String name;

    @ApiModelProperty(notes = "Location Description", example = "It is situated in the the heart of Matebeleland")
    @Schema(description = "Location Description", example = "It is situated in the the heart of Matebeleland")
    private String description;

    @ApiModelProperty(notes = "Location Address", example = "Lady Stanely Rd 17763")
    @Schema(description = "Location Address", example = "Lady Stanely Rd 17763")
    private AddressDto address;

    @ApiModelProperty(notes = "Location Type", example = "RETAIL")
    @Schema(description = "Location Type", example = "RETAIL")
    private String type;

    @ApiModelProperty(notes = "Location Province", example = "BULAWAYO PROVINCE")
    @Schema(description = "Location Province", example = "BULAWAYO PROVINCE")
    private String province;

}
