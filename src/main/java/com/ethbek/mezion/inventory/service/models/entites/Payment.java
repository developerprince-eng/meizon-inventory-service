package com.ethbek.mezion.inventory.service.models.entites;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="payment")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Payment implements Serializable {
    private static final long serialVersionUID = 2166984451L;

    @Id
    @Column(name= "payment_id",columnDefinition = "varchar(32) NOT NULL")
    private String paymentId;

    @Column(name = "payment_date", columnDefinition = "TIMEZONE WITHOUT TIME ZONE")
    private Date paymentDate;

    @Column(name= "amount",columnDefinition = "DOUBLE PRECISION")
    private Double amount;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "payment_history_id")
    private List<PaymentHistory> paymentHistories = new ArrayList<>();
}
