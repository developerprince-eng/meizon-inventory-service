package com.ethbek.mezion.inventory.service.models.entites;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name="region")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class Region implements Serializable {
    private static final long serialVersionUID = 2166984951L;

    @Id
    @JsonIgnore
    @Column(name= "region_id",columnDefinition = "varchar(16) NOT NULL")
    private String regionId;

    @Column(name= "province",columnDefinition = "varchar(255)")
    private String province;

    @Column(name= "description",columnDefinition = "TEXT")
    private String description;
}
