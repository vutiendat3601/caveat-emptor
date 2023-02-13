package com.datvutech.data.embeddable;

import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;

@MappedSuperclass /* Inheritance don't apply for @Embeddable */
public abstract class Measurement {

    @NotNull
    protected String name;

    @NotNull
    protected String symbol;
}
