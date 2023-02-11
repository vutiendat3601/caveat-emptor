package com.datvutech.data.inheritance.joined;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.SecondaryTable;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Table(name = "credit_card")
@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
// @PrimaryKeyJoinColumn(name = "creditcard_id") /* For custom PK and FK name */
@DiscriminatorValue("cc")
// @SecondaryTable(name = "credit_card") // use for mix strategy
@OnDelete(action = OnDeleteAction.CASCADE)
public class CreditCard extends BillingDetail {

    public CreditCard(String owner, String cardNumber, int expMonth, int expYear) {
        super(owner);
        this.cardNumber = cardNumber;
        this.expMonth = expMonth;
        this.expYear = expYear;
    }

    @NotNull
    @Column(/* table = "credit_card", */ name = "card_number", nullable = false)
    protected String cardNumber;

    @NotNull
    @Column(/* table = "credit_card", */ name = "exp_month", nullable = false)
    protected int expMonth;

    @NotNull
    @Column(/* table = "credit_card", */ name = "exp_year", nullable = false)
    protected int expYear;
}
