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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="account")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Account implements Serializable {
    private static final long serialVersionUID = 2166984451L;

    @Id
    @Column(name= "account_id",columnDefinition = "varchar(32) NOT NULL")
    private String accountId;

    @Column(name = "created_at",columnDefinition = "TIMEZONE WITHOUT TIME ZONE")
    private Date createdAt;

    @Column(name = "expiration_date",columnDefinition = "TIMEZONE WITHOUT TIME ZONE")
    private Date expirationDate;

    @Column(name = "status", columnDefinition = "account_status")
    private AccountStatus status;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "payment_id")
    private List<Payment> payments = new ArrayList<>();

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "account_history_id")
    private List<AccountHistory> accountHistories = new ArrayList<>();

    @OneToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinColumn(name = "location")
    @OnDelete( action = OnDeleteAction.NO_ACTION )
    private Location location;

    @OneToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinColumn(name = "customer")
    @OnDelete( action = OnDeleteAction.CASCADE )
    private Customer customer;

    @OneToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinColumn(name = "account_type")
    @OnDelete( action = OnDeleteAction.CASCADE )
    private AccountType accountType;
}
