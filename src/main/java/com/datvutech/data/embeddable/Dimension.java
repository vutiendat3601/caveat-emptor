package com.datvutech.data.embeddable;

import java.math.BigDecimal;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

@Embeddable
@AttributeOverrides({
        @AttributeOverride(name = "name", column = @Column(name = "dimension_name")),
        @AttributeOverride(name = "symbol", column = @Column(name = "dimension_symbol"))
})
public class Dimension extends Measurement {
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
