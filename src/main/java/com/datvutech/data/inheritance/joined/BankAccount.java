package com.datvutech.data.inheritance.joined;

import java.time.ZonedDateTime;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "bank_account")
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(callSuper = true)
@DiscriminatorValue("ba")
// @OnDelete(action = OnDeleteAction.CASCADE)
public class BankAccount extends BillingDetail {

    public BankAccount(String owner, String accountNumber, String bankName, String bankDescription,
            ZonedDateTime openedDate) {
        super(owner);
        this.accountNumber = accountNumber;
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
