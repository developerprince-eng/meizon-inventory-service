package com.ethbek.mezion.inventory.service.models.entites;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.io.Serializable;

@Table(name="user", schema = "public")
@Entity
@IdClass(UserIdKey.class)
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@ApiModel(value = "Location Request")
@Schema(name = "Location Request",description = "Location request to add/edit location")
public class User implements Serializable {
    private static final long serialVersionUID = 73739832653L;


    @Id
    @Column(name = "email", columnDefinition = "varchar(255)")
    @ApiModelProperty(notes = "email", example = "johndoe@mailinator.com")
    @Schema(description = "email", example = "johndoe@mailinator.com")
    private String email;

    @Id
    @Column(name = "location_id", columnDefinition = "varchar(32)")
    @ApiModelProperty(notes = "location", example = "MZ-100")
    @Schema(description = "location", example = "MZ-100")
    private String locationId;

    @Id
    @Column(name = "organisational_id", columnDefinition = "bigint")
    @ApiModelProperty(notes = "organisation", example = "1")
    @Schema(description = "organisation", example = "1")
    private Long organisationalId;

    @Column(name="employee_id", columnDefinition = "varchar(96)")
    @ApiModelProperty(notes = "employee id", example = "employee1")
    @Schema(description = "employee id", example = "employee1")
    private String employeeId;

    @Column(name = "first_name", columnDefinition = "varchar(255)")
    @ApiModelProperty(notes = "first name", example = "John")
    @Schema(description = "first name", example = "John")
    private String firstName;

    @Column(name = "lastName", columnDefinition = "varchar(255)")
    @ApiModelProperty(notes = "last name", example = "Dlamini")
    @Schema(description = "last name", example = "Dlamini")
    private String lastName;

    @Column(name = "mobile", columnDefinition = "varchar(32)")
    @ApiModelProperty(notes = "mobile", example = "+263777111222")
    @Schema(description = "mobile", example = "+263777111222")
    private String mobile;


    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "address")
    @OnDelete( action = OnDeleteAction.CASCADE )
    private Address address;

}
