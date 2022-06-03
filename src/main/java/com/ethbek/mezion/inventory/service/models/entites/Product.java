package com.ethbek.mezion.inventory.service.models.entites;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
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
@Table(name="product")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@ApiModel(value = "Products")
@Schema(name = "Products",description = "Fuel Products")
public class Product implements Serializable {
    private static final long serialVersionUID = 2166984451L;

    @Id
    @ApiModelProperty(notes = "Product SKU", example = "MZPR-3553")
    @Schema(description = "Product SKU", example = "MZPR-3553")
    @Column(name = "product_sku", columnDefinition = "varchar(16) NOT NULL")
    private String productSku;

    @Column(name = "name", columnDefinition = "varchar(96)")
    @ApiModelProperty(notes = "Product Name", example = "Diesel 40")
    @Schema(description = "Product Name", example = "Diesel 40")
    private String name;

    @Column(name = "description", columnDefinition = "TEXT")
    @ApiModelProperty(notes = "Product Description", example = "Diesel 40")
    @Schema(description = "Product Description", example = "Diesel 40")
    private String description;

    @Column(name = "created_at", columnDefinition = "TIMEZONE WITHOUT TIME ZONE")
    private Date createdAt;

    @OneToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinColumn(name = "type", referencedColumnName = "type_id")
    @OnDelete( action = OnDeleteAction.NO_ACTION )
    private ProductType type;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "category", referencedColumnName = "category_id")
    @OnDelete( action = OnDeleteAction.NO_ACTION )
    private Category category;

}
