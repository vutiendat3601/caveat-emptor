package com.datvutech.data.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Immutable;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

// @JsonIgnoreProperties({ "item" })
@Immutable /* Making an entity immutable(never needs to execute UPDATE statements) */
@Table(name = "bids")
@Entity
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@RequiredArgsConstructor
@Getter
public class Bid implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    private BigDecimal amount;

    @NonNull
    @ManyToOne(optional = false/* specify NOT NULL */, fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    @Override
    public String toString() {
        String json = "{}";
        try {
            json = new ObjectMapper().registerModule(new JavaTimeModule())
                    .writerWithDefaultPrettyPrinter()
                    .writeValueAsString(this);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return json;
    }
}
