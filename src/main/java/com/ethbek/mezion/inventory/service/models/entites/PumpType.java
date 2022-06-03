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

@Entity
@Table(name="pump_type")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@ApiModel(value = "Pump Type")
@Schema(name = "Pump Type",description = "Pump Type Information")
public class PumpType implements Serializable {
    private static final long serialVersionUID = 2166984451L;

    @Id
    @JsonIgnore
    @Column(name= "type_id",columnDefinition = "varchar(32) NOT NULL")
    private String typeId;

    @ApiModelProperty(notes = "type", example = "Analog")
    @Schema(description = "type", example = "Analog")
    @Column(name = "descriptor_type", columnDefinition = "varchar(255)")
    private String type;
}
