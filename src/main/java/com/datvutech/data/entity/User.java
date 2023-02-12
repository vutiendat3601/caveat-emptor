package com.datvutech.data.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Converts;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.datvutech.data.converter.ZipcodeConverter;
import com.datvutech.data.embeddable.Address;
import com.datvutech.data.inheritance.joined.BillingDetail;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

@Getter
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Table(name = "users")
@Entity
public class User implements Serializable {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Setter(AccessLevel.NONE)
        private Long id;

        // @Embedded Use when this Type don't Annotate with @Embeddable (third-party)
        @AttributeOverrides({
                        @AttributeOverride(name = "street", column = @Column(name = "home_street")),
                        @AttributeOverride(name = "city.country", column = @Column(name = "home_country")),
                        @AttributeOverride(name = "city.name", column = @Column(name = "home_city")),
                        @AttributeOverride(name = "city.zipcode", column = @Column(name = "home_zipcode"))
        })
        @Converts({
                        @Convert(converter = ZipcodeConverter.class, attributeName = "city.zipcode")
        })
        @NonNull
        private Address homeAddress;

        @AttributeOverrides({
                        @AttributeOverride(name = "street", column = @Column(name = "billing_street")),
                        @AttributeOverride(name = "city.country", column = @Column(name = "billing_country")),
                        @AttributeOverride(name = "city.name", column = @Column(name = "billing_city")),
                        @AttributeOverride(name = "city.zipcode", column = @Column(name = "billing_zipcode"))
        })
        @Converts({
                        @Convert(converter = ZipcodeConverter.class, attributeName = "city.zipcode")
        })
        @NonNull
        private Address billingAddress;

        /*
         * The USERS table now has the join/foreign key column DEFAULTBILLING_ID
         * representing this relationship.
         */
        @Setter
        @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
        @JoinColumn(name = "default_billing_id")
        private BillingDetail defaultBilling;

        @OneToMany(mappedBy = "user",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
        private Set<BillingDetail> billings = new HashSet<>();

        @Override
        public String toString() {
                return "User [id=" + id + ", homeAddress=" + homeAddress + ", billingAddress=" + billingAddress
                                + ", defaultBilling=" + defaultBilling + "]";
        }

}
