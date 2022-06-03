package com.ethbek.mezion.inventory.service.permission;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import scala.Serializable;
import springfox.documentation.annotations.ApiIgnore;

import java.util.*;

@ApiIgnore
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "authorities",
        "details",
        "authenticated",
        "employeeId",
        "emailAddress",
        "firstName",
        "lastName",
        "phoneNumber",
        "enabled",
        "roles",
        "credentials",
        "principal",
        "name"
})
@Getter
@Setter
public class AuthenticatedUser extends AbstractAuthenticationToken implements Serializable {
    private static final long serialVersionUID = 73739832653L;


    @ApiParam(hidden = true)
    @ApiModelProperty(hidden=true)
    @Parameter(hidden = true)
    @JsonProperty("authorities")
    private List<Authority> authorities = null;

    @ApiParam(hidden = true)
    @ApiModelProperty(hidden=true)
    @JsonProperty("details")
    private String details;

    @ApiParam(hidden = true)
    @ApiModelProperty(hidden=true)
    @Parameter(hidden = true)
    @JsonProperty("authenticated")
    private Boolean authenticated;

    @ApiParam(hidden = true)
    @ApiModelProperty(hidden=true)
    @Parameter(hidden = true)
    @JsonProperty("employeeId")
    private String employeeId;

    @ApiParam(hidden = true)
    @ApiModelProperty(hidden=true)
    @Parameter(hidden = true)
    @JsonProperty("emailAddress")
    private String emailAddress;

    @ApiParam(hidden = true)
    @ApiModelProperty(hidden=true)
    @JsonProperty("firstName")
    private String firstName;

    @ApiParam(hidden = true)
    @ApiModelProperty(hidden=true)
    @JsonProperty("lastName")
    private String lastName;

    @ApiParam(hidden = true)
    @ApiModelProperty(hidden=true)
    @Parameter(hidden = true)
    @JsonProperty("phoneNumber")
    private String phoneNumber;

    @ApiParam(hidden = true)
    @ApiModelProperty(hidden=true)
    @JsonProperty("enabled")
    private Boolean enabled;

    @ApiParam(hidden = true)
    @ApiModelProperty(hidden=true)
    @Parameter(hidden = true)
    @JsonProperty("roles")
    private List<Role> roles = null;

    @ApiParam(hidden = true)
    @ApiModelProperty(hidden=true)
    @Parameter(hidden = true)
    @JsonProperty("credentials")
    private List<Credential> credentials = null;

    @ApiParam(hidden = true)
    @ApiModelProperty(hidden=true)
    @Parameter(hidden = true)
    @JsonProperty("principal")
    private String principal;

    @ApiParam(hidden = true)
    @ApiModelProperty(hidden=true)
    @Parameter(hidden = true)
    @JsonProperty("name")
    private String name;

    @ApiParam(hidden = true)
    @ApiModelProperty(hidden=true)
    @Parameter(hidden = true)
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<>();

    public AuthenticatedUser() {
        super(new ArrayList<>());
    }

    @Override
    @JsonDeserialize(using = CustomAuthorityDeserializer.class)
    public Collection<GrantedAuthority> getAuthorities() {
        return this.getAuthorities();
    }

    @Override
    public Object getCredentials() {
        return roles;
    }

    @Override
    public Object getPrincipal() {
        return emailAddress;
    }
}