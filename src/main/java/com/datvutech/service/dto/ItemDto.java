package com.datvutech.service.dto;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemDto {
    private String name;

    private BigDecimal initialPrice;

    private ZonedDateTime lastModified;

    private BigDecimal averageBidAmount;

    private long numberOfBids;

    private Double metricWeight;
}
