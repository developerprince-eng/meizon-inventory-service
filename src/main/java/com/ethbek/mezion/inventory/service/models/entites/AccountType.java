package com.ethbek.mezion.inventory.service.models.entites;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="account_type")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class AccountType implements Serializable {
    private static final long serialVersionUID = 2166984451L;

    @Id
    @Column(name= "type_id",columnDefinition = "varchar(32) NOT NULL")
    private String typeId;

    @Column(name = "descriptor_type", columnDefinition = "varchar(255)")
    private String type;

}

