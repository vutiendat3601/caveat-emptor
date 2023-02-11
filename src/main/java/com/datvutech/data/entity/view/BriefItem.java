package com.datvutech.data.entity.view;

import java.io.Serializable;
import java.time.ZonedDateTime;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Id;

import org.hibernate.annotations.ColumnTransformer;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

import com.datvutech.data.converter.MonetaryAmountConverter;
import com.datvutech.data.usertype.MonetaryAmount;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Subselect("SELECT " +
        "i.id, " +
        "i.name, " +
        "i.initial_price, " +
        "i.buy_now_price, " +
        "i.imperial_weight, " +
        "i.last_modified " +
        "FROM " +
        "items i")
@Immutable
@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BriefItem implements Serializable{
    @Id
    private Long id;

    private String name;

    @Convert(converter = MonetaryAmountConverter.class)
    @Column(name = "initial_price")
    private MonetaryAmount initialPrice;
    
    @Convert(converter = MonetaryAmountConverter.class)
    @Column(name = "buy_now_price")
    private MonetaryAmount buyNowPrice;

    @ColumnTransformer(read = "imperial_weight / 2.20462", write = "? * 2.20462")
    @Column(name = "imperial_weight")
    private Double metricWeight;

    @Column(name = "last_modified")
    private ZonedDateTime lastModified;
}
