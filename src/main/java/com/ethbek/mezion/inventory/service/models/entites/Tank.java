package com.ethbek.mezion.inventory.service.models.entites;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="tank")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Tank implements Serializable {
    private static final long serialVersionUID = 2166984451L;

    @Id
    @Column(name= "tank_id",columnDefinition = "varchar(32) NOT NULL")
    private String tankId;

    @Column(name = "capacity",columnDefinition = "BIGINT")
    private Long capacity;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "product")
    @OnDelete( action = OnDeleteAction.NO_ACTION)
    private Product product;

    @Column(name = "referenced_height",columnDefinition = "VARCHAR(16)")
    private String referencedHeight;


    @ManyToOne(optional = false)
    @JoinColumn(name = "location_id", nullable = false, foreignKey = @ForeignKey(name = "location_id_FK", foreignKeyDefinition = "FOREIGN KEY (location_id) REFERENCES branch(location_id)"))
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    private Location location;
}
