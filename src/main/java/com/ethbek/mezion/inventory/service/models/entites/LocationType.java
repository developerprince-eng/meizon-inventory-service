package com.ethbek.mezion.inventory.service.models.entites;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="location_type")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LocationType implements Serializable {
    private static final long serialVersionUID = 844518834L;

    @Id
    @JsonIgnore
    @Column(name= "type_id",columnDefinition = "varchar(32) NOT NULL")
    private String typeId;

    @Column(name = "descriptor_type" ,columnDefinition = "varchar(255)")
    private String type;
}
