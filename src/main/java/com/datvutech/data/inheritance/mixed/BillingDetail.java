package com.datvutech.data.inheritance.mixed;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

@Data
@Table(name = "billing_detail")
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE) /* Cordinate subclass properties to be single table */
@DiscriminatorColumn(name = "bd_type")
public class BillingDetail {

    @Setter(AccessLevel.PRIVATE)
    @Id
    @GeneratedValue(generator = "ID_GENERATOR")
    protected Long id;

    protected BillingDetail(String owner) {
        this.owner = owner;
    }

    @NotNull
    @Column(nullable = false)
    protected String owner;
}
