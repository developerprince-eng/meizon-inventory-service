package com.ethbek.mezion.inventory.service.models.entites;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="rate")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Rate implements Serializable {
    private static final long serialVersionUID = 2166984451L;

    @Id
    @JsonIgnore
    @Column(name= "rate_id",columnDefinition = "varchar(32) NOT NULL")
    private String rateId;

    @Column(name = "created_at",columnDefinition = "TIMEZONE WITHOUT TIME ZONE")
    private Date createdAt;

    @Column(name= "value",columnDefinition = "DOUBLE PRECISION")
    private  Double value;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "currency")
    @OnDelete( action = OnDeleteAction.NO_ACTION )
    private Currency currency;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name ="product",foreignKey = @ForeignKey(name = "product_rate_fk"))
    @OnDelete( action = OnDeleteAction.NO_ACTION )
    private Product product;
}
