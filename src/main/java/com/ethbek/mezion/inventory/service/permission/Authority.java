
package com.ethbek.mezion.inventory.service.permission;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import springfox.documentation.annotations.ApiIgnore;

import java.util.HashMap;
import java.util.Map;

@ApiIgnore
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "authority"
})
@Getter
@Setter
public class Authority {

    @ApiModelProperty(hidden=true)
    @JsonProperty("authority")
    private String authority;

    @ApiModelProperty(hidden=true)
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<>();
}
