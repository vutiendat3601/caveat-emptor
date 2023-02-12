package com.datvutech.data.inheritance.union;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "credit_card")
@Entity
public class CreditCard extends BillingDetail {

    protected CreditCard() {
        super();
    }

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
