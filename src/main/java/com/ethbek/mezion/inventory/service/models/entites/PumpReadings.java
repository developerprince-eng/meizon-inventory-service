package com.ethbek.mezion.inventory.service.models.entites;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="pump_readings")
@IdClass(PumpReadingsKey.class)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PumpReadings implements Serializable {
    private static final long serialVersionUID = 2166984451L;

    @Id
    @JsonIgnore
    @OneToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinColumn(name = "pump_id", referencedColumnName = "pump_id")
    @OnDelete( action = OnDeleteAction.NO_ACTION)
    private Pump pump;

    @Id
    @JsonIgnore
    @Column(name = "user_id", columnDefinition = "VARCHAR(32)")
    private String user;

    @Id
    @Column(name= "pump_reading_id",columnDefinition = "varchar(32)")
    private String pumpReadingId;

    @Column(name= "open_volume",columnDefinition = "INTEGER")
    private Integer openVolume;

    @Column(name= "close_volume",columnDefinition = "INTEGER")
    private Integer closeVolume;

    @Column(name = "created_date" ,columnDefinition = "TIMEZONE WITHOUT TIME ZONE")
    private Date createdDate;

    @Column(name = "pump_side", columnDefinition = "VARCHAR(32)", nullable = false)
    private String pumpSide;
}

