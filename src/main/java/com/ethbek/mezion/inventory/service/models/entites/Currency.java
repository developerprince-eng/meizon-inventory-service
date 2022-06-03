package com.ethbek.mezion.inventory.service.models.entites;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="currency")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Currency implements Serializable {
    private static final long serialVersionUID = 2166984451L;

    @Id
    @JsonIgnore
    @Column(name= "currency_id",columnDefinition = "varchar(32) NOT NULL")
    private String currencyId;

    @Column(name = "name",columnDefinition = "varchar(8)")
    private String name;

}
