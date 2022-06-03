package com.ethbek.mezion.inventory.service.models.entites;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Table(name="dip_sheet")
@IdClass(DipSheetKey.class)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DipSheet {


    @Id
    @JsonIgnore
    @OneToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinColumn(name = "tank_id", referencedColumnName = "tank_id")
    @OnDelete( action = OnDeleteAction.NO_ACTION)
    private Tank tank;

    @Id
    @JsonIgnore
    @Column(name= "dip_sheet_id",columnDefinition = "varchar(255) NOT NULL")
    private String dipSheetId;

    @Column(name= "metricMeasurement",columnDefinition = "INTEGER")
    private Integer metricMeasurement;

    @Column(name= "volume",columnDefinition = "INTEGER")
    private Integer volume;
}
