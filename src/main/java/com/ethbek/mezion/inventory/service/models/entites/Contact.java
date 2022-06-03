package com.ethbek.mezion.inventory.service.models.entites;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="contact")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Contact implements Serializable {
    private static final long serialVersionUID = 2166984451L;

    @Id
    @JsonIgnore
    @Column(name = "contact_id", columnDefinition = "varchar(255) NOT NULL")
    private String contactId;

    @Column(columnDefinition = "varchar(255)")
    private String detail;

    @OneToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinColumn(name = "type")
    @OnDelete( action = OnDeleteAction.CASCADE )
    private ContactType type;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name ="customer",foreignKey = @ForeignKey(name = "post_comment_fk"))
    @OnDelete( action = OnDeleteAction.NO_ACTION )
    private Customer customer;
}
