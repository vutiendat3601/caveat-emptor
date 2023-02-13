package com.datvutech.association;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "items")
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class Item implements Serializable {
    public Item(String name) {
        this.name = name;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @Getter(AccessLevel.NONE)
    @OneToMany(mappedBy = "item", fetch = FetchType.LAZY, cascade = { CascadeType.ALL })
    private Set<Bid> bids = new HashSet<>();

    public void addBid(Bid bid) {
        bids.add(bid);
    }
}
