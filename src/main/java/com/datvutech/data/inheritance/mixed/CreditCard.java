package com.datvutech.data.inheritance.mixed;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SecondaryTable;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
/*
 * Create new Table for this, not map to single table with super class,
 * pkJoinColumns is also PK and FK
 */
@SecondaryTable(name = "credit_card", pkJoinColumns = @PrimaryKeyJoinColumn(name = "id"))
public class CreditCard extends BillingDetail {

    public CreditCard(String owner, String cardNumber, int expMonth, int expYear) {
        super(owner);
        this.cardNumber = cardNumber;
        this.expMonth = expMonth;
        this.expYear = expYear;
    }

    @NotNull
    @Column(table = "credit_card", name = "card_number", nullable = false)
    protected String cardNumber;

    @NotNull
    @Column(table = "credit_card", name = "exp_month", nullable = false)
    protected int expMonth;

    @NotNull
    @Column(table = "credit_card", name = "exp_year", nullable = false)
    protected int expYear;
}
