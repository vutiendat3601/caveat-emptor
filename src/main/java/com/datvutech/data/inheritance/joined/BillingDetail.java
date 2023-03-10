package com.datvutech.data.inheritance.joined;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.datvutech.data.entity.User;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Table(name = "billing_detail")
@Entity
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Inheritance(strategy = InheritanceType.JOINED) /* subclass PK is FK to super class */
// @DiscriminatorColumn(name = "bd_type")
// @OnDelete(action = OnDeleteAction.CASCADE)
public class BillingDetail {

    @Setter(AccessLevel.PRIVATE)
    @Id
    @GeneratedValue(generator = "ID_GENERATOR")
    protected Long id;

    BillingDetail(String owner) {
        this.owner = owner;
    }

    @NotNull
    @Column(nullable = false)
    protected String owner;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    protected User user;
}
