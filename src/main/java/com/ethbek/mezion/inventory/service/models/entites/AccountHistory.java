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
@Table(name="account_history")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class AccountHistory implements Serializable {
    private static final long serialVersionUID = 2166984451L;

    @Id
    @Column(name= "account_history_id",columnDefinition = "varchar(32) NOT NULL")
    private String accountHistoryId;

    @Column(columnDefinition = "TIMEZONE WITHOUT TIME ZONE")
    private Date transactionDate;

    @OneToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinColumn(name = "volume")
    @OnDelete( action = OnDeleteAction.CASCADE )
    private Volume volume;

}
