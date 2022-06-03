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

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name = "pump_side_type")
@ApiModel(value = "Pump Side Type")
@Schema(name = "Pump Side Type",description = "Pump Side Type Information")
public class PumpSideType implements Serializable {
    private static final long serialVersionUID = 73739832653L;

    @Id
    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "type_id")
    private Integer type_id;

    @ApiModelProperty(notes = "Pump Side", example = "A")
    @Schema(description = "Pump Side", example = "A")
    @Column(name = "side",nullable = false, columnDefinition = "VARCHAR(2)")
    private String side;
}
