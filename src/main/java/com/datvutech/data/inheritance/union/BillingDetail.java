package com.datvutech.data.inheritance.union;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

@Data
@Table(name = "billing_detail")
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS) /* Use UNION SQL */
public class BillingDetail {

    @Setter(AccessLevel.PRIVATE)
    @Id
    @GeneratedValue(generator = "ID_GENERATOR")
    protected Long id;

    protected BillingDetail(String owner) {
        this.owner = owner;
    }

    protected String owner;
}
