package com.datvutech.data.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import com.datvutech.data.usertype.MonetaryAmount;

/* Implement custom converter for hibernate */
@Converter(autoApply = true)
public class MonetaryAmountConverter
        implements AttributeConverter<MonetaryAmount, String> {

    @Override
    public String convertToDatabaseColumn(MonetaryAmount attribute) {
        return attribute.toString();
    }

    @Override
    public MonetaryAmount convertToEntityAttribute(String dbData) {
        return MonetaryAmount.fromString(dbData);
    }

}
