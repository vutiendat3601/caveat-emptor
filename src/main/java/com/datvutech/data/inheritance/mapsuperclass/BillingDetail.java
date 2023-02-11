package com.datvutech.data.inheritance.mapsuperclass;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

@MappedSuperclass /* All subclass is a table */
@Data
public abstract class BillingDetail {

    @Setter(AccessLevel.PRIVATE)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    protected BillingDetail(String owner) {
        this.owner = owner;
    }

    protected String owner;
}
