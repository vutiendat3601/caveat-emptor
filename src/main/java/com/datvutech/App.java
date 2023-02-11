package com.datvutech;

import java.math.BigDecimal;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.datvutech.config.HibernatePart2;
import com.datvutech.data.entity.Bid;
import com.datvutech.data.entity.Item;

public class App {
    public static void main(String[] args) {
        SessionFactory sessionFactory = HibernatePart2.getInstance();
        Session session = sessionFactory.openSession();

        // createDummyItemBid(session);
        // ItemBidSummary ibSummary = session.find(ItemBidSummary.class, 1L);
        Item item1 = session.find(Item.class, 1L);
        // item1.setBuyNowPrice(MonetaryAmount.fromString("10000 USD"));
        // Bid bid1 = session.find(Bid.class, 1L);
        System.out.println(item1);
        // System.out.println(bid1);
        // BriefItem briefItem1 = session.find(BriefItem.class, 1L);
        // User user1 = session.find(User.class, 1L);
        session.close();
        System.out.println("Hello World!");
    }

    protected static void createDummyItemBid(Session session) {
        session.beginTransaction();
        Item item1 = new Item("iPhone 13 Pro Max", 0.240D);
        Item item2 = new Item("Samsung Galaxy Note 9", 0.201D);
        Item item3 = new Item("Google Pixel 7 Pro", 0.212D);
        session.save(item1);
        session.save(item2);
        session.save(item3);

        item1 = session.find(Item.class, 1L);
        item2 = session.find(Item.class, 2L);
        item3 = session.find(Item.class, 3L);
        Bid bid1 = new Bid(new BigDecimal("1000"), item1);
        Bid bid2 = new Bid(new BigDecimal("1200"), item2);
        Bid bid3 = new Bid(new BigDecimal("3200"), item1);
        Bid bid4 = new Bid(new BigDecimal("350"), item3);
        Bid bid5 = new Bid(new BigDecimal("2000"), item2);
        Bid bid6 = new Bid(new BigDecimal("4000"), item1);

        session.save(bid1);
        session.save(bid2);
        session.save(bid3);
        session.save(bid4);
        session.save(bid5);
        session.save(bid6);

        session.getTransaction().commit();
        System.out.println("CREATION FINISHED");
    }
}
