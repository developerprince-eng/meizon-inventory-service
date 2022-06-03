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
@Table(name="pump_side")
@IdClass(PumpSideKey.class)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PumpSide implements Serializable {
    private static final long serialVersionUID = 2166984451L;

    @Id
    @OneToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinColumn(name = "pump_id", referencedColumnName = "pump_id")
    @OnDelete( action = OnDeleteAction.NO_ACTION)
    private Pump pump;

    @Id
    @Column(name= "pump_side_id",columnDefinition = "varchar(32)")
    private String pumpSideId;

}
