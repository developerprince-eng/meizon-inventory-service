package com.ethbek.mezion.inventory.service.models.entites;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="volume")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Volume implements Serializable {
    private static final long serialVersionUID = 2166984451L;

    @Id
    @Column(name= "volume_id",columnDefinition = "varchar(32) NOT NULL")
    private String volumeId;

    @Column(name = "transacted_volume", columnDefinition = "BIGINT")
    private Long transactedVolume;

    @OneToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinColumn(name = "product")
    @OnDelete( action = OnDeleteAction.NO_ACTION )
    private Product product;

    @OneToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinColumn(name = "meta")
    @OnDelete( action = OnDeleteAction.CASCADE )
    private Meta meta;
}
