package com.datvutech.data.embeddable;

import java.math.BigDecimal;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Embeddable
@AttributeOverrides({
        @AttributeOverride(name = "name", column = @Column(name = "weight_name")),
        @AttributeOverride(name = "symbol", column = @Column(name = "weight_symbol"))
})
@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class Weight extends Measurement {
    public static String KI_LO = "kilo";
    public static String KI_LO_SYMBOL = "kg";

    public Weight(String name, String symbol, BigDecimal weightValue) {
        this.name = name;
        this.symbol = symbol;
        this.weightValue = weightValue;
    }

    @NotNull
    @Column(name = "weight_value")
    private BigDecimal weightValue;
}
