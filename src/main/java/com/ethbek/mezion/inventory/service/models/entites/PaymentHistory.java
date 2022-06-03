package com.ethbek.mezion.inventory.service.models.entites;

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
@Table(name="payment_history")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentHistory implements Serializable {
    private static final long serialVersionUID = 2166984451L;

    @Id
    @Column(name= "payment_history_id",columnDefinition = "varchar(32) NOT NULL")
    private String paymentHistoryId;

    @Column(name= "amount",columnDefinition = "DOUBLE PRECISION)")
    private Double amount;

    @Column(name = "payment_time" ,columnDefinition = "TIMEZONE WITHOUT TIME ZONE")
    private Date paymentTime;

    @OneToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinColumn(name = "currency", referencedColumnName = "currency_id")
    @OnDelete( action = OnDeleteAction.NO_ACTION )
    private Currency currency;

    @OneToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @OnDelete( action = OnDeleteAction.NO_ACTION )
    @JoinColumn(name = "payment_type", referencedColumnName = "type_id")
    private PaymentType paymentType;

    @OneToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @OnDelete( action = OnDeleteAction.NO_ACTION )
    @JoinColumn(name = "customer", referencedColumnName = "customer_id")
    private Customer customer;
}
