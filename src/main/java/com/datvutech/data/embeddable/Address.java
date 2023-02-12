package com.datvutech.data.embeddable;

import javax.persistence.Embeddable;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
@Embeddable/* Embedable convert to properties of entity use it */
public class Address {
    private String street;

    // @AttributeOverrides({
    //         @AttributeOverride(name = "country", column = @Column(name = "country")),
    //         @AttributeOverride(name = "name", column = @Column(name = "city")),
    //         @AttributeOverride(name = "zipcode", column = @Column(name = "zipcode"))
    // })
    private City city;

    @Override
    public String toString() {
        String json = "{}";
        try {
            json = new ObjectMapper()
                    .writerWithDefaultPrettyPrinter()
                    .writeValueAsString(this);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return json;
    }
}
