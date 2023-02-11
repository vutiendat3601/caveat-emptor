package com.datvutech.data.inheritance.union;

import java.time.ZonedDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Past;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "bank_account")
@Data
@EqualsAndHashCode(callSuper = true)
public class BankAccount extends BillingDetail {

    protected BankAccount(String owner, String bankName, String bankDescription, ZonedDateTime openedDate) {
        super(owner);
        this.bankName = bankName;
        this.bankDescription = bankDescription;
        this.openedDate = openedDate;
    }

    @Column(name = "account_number", nullable = false)
    private String accountNumber;

    @Column(name = "bank_name", nullable = false)
    private String bankName;

    @Column(name = "bank_description", nullable = false)
    private String bankDescription;

    @Past
    @Column(name = "opened_date")
    private ZonedDateTime openedDate;
}
