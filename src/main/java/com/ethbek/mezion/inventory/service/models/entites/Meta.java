package com.ethbek.mezion.inventory.service.models.entites;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="meta")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Meta implements Serializable {
    private static final long serialVersionUID = 2166984451L;

    @Id
    @Column(name= "meta_id",columnDefinition = "varchar(32) NOT NULL")
    private String metaId;

    @Column(columnDefinition = "varchar(255)")
    private String type;
}
