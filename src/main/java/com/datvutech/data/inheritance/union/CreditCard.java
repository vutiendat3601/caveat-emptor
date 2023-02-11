package com.datvutech.data.inheritance.union;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

@AttributeOverride(name = "owner", column = @Column(name = "cc_owner", nullable = false))
@Table(name = "credit_card")
@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class CreditCard extends BillingDetail {

    public CreditCard(String owner, String cardNumber, int expMonth, int expYear) {
        super(owner);
        this.cardNumber = cardNumber;
        this.expMonth = expMonth;
        this.expYear = expYear;
    }

    @Column(name = "card_number")
    protected String cardNumber;

    @Column(name = "exp_month")
    protected int expMonth;

    @Column(name = "exp_year")
    protected int expYear;
}
