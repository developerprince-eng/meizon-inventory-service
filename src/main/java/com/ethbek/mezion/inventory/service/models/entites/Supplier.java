package com.ethbek.mezion.inventory.service.models.entites;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
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
@Table(name="supplier", schema = "public")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class Supplier implements Serializable {
    private static final long serialVersionUID = 2166984451L;

    @Id
    @Column(name= "supplier_id",columnDefinition = "varchar(32) NOT NULL")
    private String supplierId;

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

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "contact_id")
    private List<Contact> contacts;
}
