package com.datvutech.data.entity.view;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;
import org.hibernate.annotations.Synchronize;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/* Mapping an entity to a subselect, map an immutable and read-only entity (Views) */
@Subselect("SELECT " +
        "i.id item_id, i.`name` item_name, count(b.id) number_of_bids " +
        "FROM " +
        "items i LEFT JOIN bids b " +
        "ON i.id = b.item_id " +
        "GROUP BY " +
        "i.id, i.`name`")

@Synchronize({ "Item", "Bid" })
// @Table(name = "item_bid_summary")
@Entity
@Immutable
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class ItemBidSummary implements Serializable {
    @Id
    @Column(name = "item_id")
    private Long itemId;

    @Column(name = "item_name")
    private String name;

    @Column(name = "number_of_bids")
    private Long numberOfBids;

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