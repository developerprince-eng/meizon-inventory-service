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
@Table(name="dip_sheet_daily_reading")
@IdClass(DipSheetReadingsKey.class)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DipSheetReadings implements Serializable {

    private static final long serialVersionUID = 2166984451L;

    @Id
    @JsonIgnore
    @OneToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinColumn(name = "tank_id", referencedColumnName = "tank_id")
    @OnDelete( action = OnDeleteAction.NO_ACTION)
    private Tank tank;

    @Id
    @JsonIgnore
    @Column(name = "user_id", columnDefinition = "VARCHAR(32)")
    private String user;

    @Id
    @Column(name= "daily_reading_id",columnDefinition = "varchar(32) NOT NULL")
    private String dailyReadingId;

    @Column(name= "open_volume",columnDefinition = "INTEGER")
    private Integer openVolume;

    @Column(name= "close_volume",columnDefinition = "INTEGER")
    private Integer closeVolume;

    @Column(name = "created_date" ,columnDefinition = "TIMEZONE WITHOUT TIME ZONE")
    private Date createdDate;
}
