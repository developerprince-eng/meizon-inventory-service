package com.ethbek.mezion.inventory.service.models.entites;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Table(name="category")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@ApiModel(value = "Product Category")
@Schema(name = "Product Category",description = "Product Category")
public class Category implements Serializable {
    private static final long serialVersionUID = 73739832653L;

    @Id
    @JsonIgnore
    @Column(name= "category_id",columnDefinition = "varchar(32) NOT NULL")
    private String categoryId;

    @ApiModelProperty(notes = "Category Name", example = "FUEL")
    @Schema(description = "Category Name", example = "FUEL")
    @Column(name = "name",columnDefinition = "varchar(196)")
    private String name;

    @ApiModelProperty(notes = "Category Description", example = "FUEL")
    @Schema(description = "Category Description", example = "FUEL")
    @Column(name = "description",columnDefinition = "TEXT")
    private String description;
}
