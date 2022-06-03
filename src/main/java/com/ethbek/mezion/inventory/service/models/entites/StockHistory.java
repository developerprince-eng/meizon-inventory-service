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
import java.util.Date;

@Entity
@Table(name="stock_history", schema = "public")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class StockHistory implements Serializable {
    private static final long serialVersionUID = 2166984451L;

    @Id
    @Column(name= "stock_history_id",columnDefinition = "varchar(32) NOT NULL")
    private String stockHistoryId;

    @Column(name= "amount",columnDefinition = "DOUBLE PRECISION)")
    private Double amount;

    @Column(name = "stock_update_time" ,columnDefinition = "TIMEZONE WITHOUT TIME ZONE")
    private Date stockUpdateTime;

    @Column(name = "recorded_user", columnDefinition = "VARCHAR(32)")
    private String recordedUser;
}
