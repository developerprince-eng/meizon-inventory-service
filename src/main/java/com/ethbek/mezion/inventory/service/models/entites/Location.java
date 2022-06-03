package com.ethbek.mezion.inventory.service.models.entites;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name="location")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class Location implements Serializable {
    private static final long serialVersionUID = 2166984451L;

    @Id
    @Column(name = "location_id", columnDefinition = "VARCHAR(32)")
    private String locationId;

    @Column(name= "name",columnDefinition = "varchar(255) NOT NULL")
    private String name;

    @Column(name= "description",columnDefinition = "TEXT")
    private String description;


    @OneToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinColumn(name = "region")
    @OnDelete( action = OnDeleteAction.NO_ACTION )
    private Region region;

    @OneToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @OnDelete( action = OnDeleteAction.NO_ACTION )
    @JoinColumn(name = "location_type")
    private LocationType locationType;


    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "address")
    @OnDelete( action = OnDeleteAction.CASCADE )
    private Address address;

}
