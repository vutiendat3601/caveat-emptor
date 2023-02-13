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
        @AttributeOverride(name = "name", column = @Column(name = "dimension_name")),
        @AttributeOverride(name = "symbol", column = @Column(name = "dimension_symbol"))
})
@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class Dimension extends Measurement {

    public static String CM = "centimet";
    public static String CM_SYMBOL = "cm";

    /**
     * param pattern: WithxWeightxDepth
     * example: 20x20x20
     */
    public static Dimension of(String name, String symbol, String dimension) {
        dimension = dimension.replaceAll("X", "x");
        String[] splits = dimension.split("x");
        if (splits.length < 3) {
            throw new IllegalArgumentException("Not supported");
        }
        return new Dimension(name, symbol,
                new BigDecimal(splits[0]),
                new BigDecimal(splits[1]),
                new BigDecimal(splits[2]));
    }

    public Dimension(String name, String symbol,
            BigDecimal dimensionDepth,
            BigDecimal dimensionHeight,
            BigDecimal dimensionWidth) {
        this.name = name;
        this.symbol = symbol;
        this.dimensionDepth = dimensionDepth;
        this.dimensionHeight = dimensionHeight;
        this.dimensionWidth = dimensionWidth;
    }

    @NotNull
    @Column(name = "dimension_depth")
    private BigDecimal dimensionDepth;

    @NotNull
    @Column(name = "dimension_height")
    private BigDecimal dimensionHeight;

    @NotNull
    @Column(name = "dimension_width")
    private BigDecimal dimensionWidth;
}
