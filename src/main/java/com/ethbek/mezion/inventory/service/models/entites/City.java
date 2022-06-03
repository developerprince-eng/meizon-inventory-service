package com.ethbek.mezion.inventory.service.models.entites;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="city")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class City implements Serializable {
    private static final long serialVersionUID = 2166984451L;

    @Id
    @JsonIgnore
    @Column(name= "city_id",columnDefinition = "varchar(32) NOT NULL")
    private String cityId;

    @Column(name= "name",columnDefinition = "varchar(196)")
    private String name;
}
