package com.ethbek.mezion.inventory.service.models.entites;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name="customer")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Customer implements Serializable {
    private static final long serialVersionUID = 2166984451L;

    @Id
    @Column(name= "customer_id",columnDefinition = "varchar(255) NOT NULL")
    private String customerId;

    @Column(name= "first_name",columnDefinition = "varchar(255)")
    private String firstName;

    @Column(name= "last_name",columnDefinition = "varchar(255)")
    private String lastName;

    @Column(name= "company_name",columnDefinition = "varchar(255)", unique = true)
    private String companyName;

    @OneToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinColumn(name = "city", referencedColumnName = "city_id")
    @OnDelete( action = OnDeleteAction.NO_ACTION )
    private City city;

    @OneToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinColumn(name = "address")
    @OnDelete( action = OnDeleteAction.CASCADE )
    private Address address;


    @OneToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinColumn(name = "region")
    @OnDelete( action = OnDeleteAction.NO_ACTION )
    private Region region;
}
