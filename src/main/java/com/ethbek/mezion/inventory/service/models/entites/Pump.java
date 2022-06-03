package com.ethbek.mezion.inventory.service.models.entites;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="pump")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Pump implements Serializable {
    private static final long serialVersionUID = 2166984451L;

    @Id
    @Column(name= "pump_id",columnDefinition = "varchar(32) NOT NULL")
    private String pumpId;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(columnDefinition = "INTEGER")
    private Integer sides;

    @OneToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinColumn(name = "type")
    @OnDelete( action = OnDeleteAction.NO_ACTION )
    private PumpType type;

    @ManyToOne(optional = false)
    @JoinColumn(name = "tank_id", nullable = false, foreignKey = @ForeignKey(name = "tank_id_FK", foreignKeyDefinition = "FOREIGN KEY (tank_id) REFERENCES branch(tank_id)"))
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    private Tank tank;

}
