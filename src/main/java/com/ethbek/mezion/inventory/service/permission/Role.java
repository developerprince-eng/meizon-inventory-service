
package com.ethbek.mezion.inventory.service.permission;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import springfox.documentation.annotations.ApiIgnore;

import java.io.Serializable;

@ApiIgnore
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "roleId",
    "branchId",
    "organisationalId"
})
@Getter
@Setter
public class Role implements Serializable {

    private static final long serialVersionUID = 73739832653L;

    @ApiModelProperty(hidden=true)
    @JsonProperty("roleId")
    private String roleId;

    @ApiModelProperty(hidden=true)
    @JsonProperty("branchId")
    private String branchId;

    @ApiModelProperty(hidden=true)
    @JsonProperty("organisationalId")
    private String organisationalId;
}
