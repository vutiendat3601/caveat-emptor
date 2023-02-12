package com.datvutech.data.inheritance.union;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS) /* One table per subclass, Use UNION SQL */
public abstract class BillingDetail {

    protected BillingDetail() {
    }

    @Setter(AccessLevel.PRIVATE)
    @Id
    /*
     * InheritanceType.TABLE_PER_CLASS strategy have to generate for subclass
     * identifiers
     */
    @GeneratedValue(strategy = GenerationType.TABLE)
    protected Long id;

    protected BillingDetail(String owner) {
        this.owner = owner;
    }

    @NotNull
    protected String owner;
}
