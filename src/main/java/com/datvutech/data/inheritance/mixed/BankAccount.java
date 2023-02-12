package com.datvutech.data.inheritance.mixed;

import java.time.ZonedDateTime;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@DiscriminatorValue("ba")
// @DiscriminatorFormula("case when CARDNUMBER is not null then 'cc' else 'ba'
// end") // use for legacy database
public class BankAccount extends BillingDetail {

    protected BankAccount(String owner, String bankName, String bankDescription, ZonedDateTime openedDate) {
        super(owner);
        this.bankName = bankName;
        this.bankDescription = bankDescription;
        this.openedDate = openedDate;
    }

    @NotNull
    @Column(name = "account_number", nullable = false)
    private String accountNumber;

    @NotNull
    @Column(name = "bank_name", nullable = false)
    private String bankName;

    @NotNull
    @Column(name = "bank_description", nullable = false)
    private String bankDescription;

    @NotNull
    @Past
    @Column(name = "opened_date", nullable = false)
    private ZonedDateTime openedDate;
}
