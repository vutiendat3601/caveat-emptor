package com.datvutech.data.inheritance.singletable;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Table(name = "credit_card")
@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@DiscriminatorValue("cc")
public class CreditCard extends BillingDetail {

    public CreditCard(String owner, String cardNumber, int expMonth, int expYear) {
        super(owner);
        this.cardNumber = cardNumber;
        this.expMonth = expMonth;
        this.expYear = expYear;
    }

    @NotNull
    @Column(name = "card_number", nullable = false)
    protected String cardNumber;

    @NotNull
    @Column(name = "exp_month", nullable = false)
    protected int expMonth;

    @NotNull
    @Column(name = "exp_year", nullable = false)
    protected int expYear;
}
