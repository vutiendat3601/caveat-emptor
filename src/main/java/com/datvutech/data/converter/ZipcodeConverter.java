package com.datvutech.data.converter;

import javax.persistence.AttributeConverter;

import com.datvutech.data.usertype.Zipcode;
import com.datvutech.data.usertype.SwissZipcode;
import com.datvutech.data.usertype.UsaZipcode;

public class ZipcodeConverter implements AttributeConverter<Zipcode, String> {

    @Override
    public String convertToDatabaseColumn(Zipcode zipcode) {
        return zipcode.getValue();
    }

    @Override
    public Zipcode convertToEntityAttribute(String zipcode) {
        int zipcodeLength = zipcode.length();
        switch (zipcodeLength) {
            case UsaZipcode.LENGTH:
                return new UsaZipcode(zipcode);
            case SwissZipcode.LENGTH:
                return new SwissZipcode(zipcode);
            default:
                throw new IllegalArgumentException(
                        "Unsupported zipcode in database: " + zipcode);
        }
    }

}
