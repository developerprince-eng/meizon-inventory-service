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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="stock", schema = "public")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class Stock implements Serializable {
    private static final long serialVersionUID = 2166984951L;

    @Id
    @JsonIgnore
    @Column(name= "stock_id",columnDefinition = "varchar(32) NOT NULL")
    private String stockId;

    @Column(name = "delivery_date", columnDefinition = "TIMEZONE WITHOUT TIME ZONE")
    private Date deliveryDate;

    @Column(name= "delivered_volume",columnDefinition = "DOUBLE PRECISION")
    private Double deliveredVolume;

    @Column(name= "aggregated_volume",columnDefinition = "DOUBLE PRECISION")
    private Double aggregatedVolume;

    @Column(name = "receiver", columnDefinition = "VARCHAR(32)")
    private User receiver;

    @OneToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinColumn(name = "location")
    @OnDelete( action = OnDeleteAction.NO_ACTION)
    private Location location;

    @OneToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinColumn(name = "product")
    @OnDelete( action = OnDeleteAction.NO_ACTION)
    private Product product;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "stock_history_id")
    private List<StockHistory> stockHistory = new ArrayList<>();

    @OneToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinColumn(name = "supplier")
    @OnDelete( action = OnDeleteAction.NO_ACTION)
    private Supplier supplier;
}
