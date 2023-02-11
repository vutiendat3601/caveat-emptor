package com.datvutech.data.embeddable;

import javax.persistence.Embeddable;

import com.datvutech.data.usertype.Zipcode;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@AllArgsConstructor
@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class City {
    private String name;

    private String country;

    private Zipcode zipcode;

    @Override
    public String toString() {
        String json = "{}";
        try {
            json = new ObjectMapper().writerWithDefaultPrettyPrinter()
                    .writeValueAsString(this);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return json;
    }
}
